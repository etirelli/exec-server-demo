package org.kie.demo.bankapp.backend;

import javax.enterprise.context.ApplicationScoped;

import org.drools.core.rule.EntryPointId;
import org.drools.core.runtime.impl.ExecutionResultImpl;
import org.drools.demo.ds.banking.model.Customer;
import org.drools.demo.ds.banking.model.MortgageApplication;
import org.jboss.errai.bus.server.annotations.Service;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.demo.bankapp.model.Applicant;
import org.kie.demo.bankapp.model.Mortgage;
import org.kie.demo.bankapp.service.DecisionServerService;
import org.kie.server.api.marshalling.Marshaller;
import org.kie.server.api.marshalling.MarshallerFactory;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@ApplicationScoped
public class DecisionServerServiceImpl implements DecisionServerService {

    private static final String SERVER_URL = "http://localhost:8180/kie-server/services/rest/server";
    private static final String USER_NAME  = "kieserver";
    private static final String USER_PWD   = "kieserver1!";

    private static final String CONTAINER_ID = "mortgage";
    private static final String KIE_SESSION  = "defaultKieSession";
    private static final String MA_OUT_ID = "ma";

    @Override
    public Mortgage process(final Applicant applicant, final Mortgage mortgage) {
        HashSet<Class<?>> classes = new HashSet<Class<?>>();
        classes.add( Customer.class );
        classes.add( MortgageApplication.class );

        KieServicesConfiguration ksconf = KieServicesFactory.newRestConfiguration( SERVER_URL, USER_NAME, USER_PWD );
        ksconf.setMarshallingFormat( MarshallingFormat.JSON );
        ksconf.addJaxbClasses( classes );
        Marshaller marshaller = MarshallerFactory.getMarshaller( classes, ksconf.getMarshallingFormat(), getClass().getClassLoader() );

        KieServicesClient client = KieServicesFactory.newKieServicesClient( ksconf );
        KieCommands cf = KieServices.Factory.get().getCommands();
        RuleServicesClient ruleClient = client.getServicesClient( RuleServicesClient.class );

        List<Command<?>> commands = new ArrayList<Command<?>>();
        BatchExecutionCommand batchExecution = cf.newBatchExecution( commands, KIE_SESSION );

        Customer c = new Customer();
        c.setAge( applicant.getAge() != null ? Integer.parseInt( applicant.getAge() ) : null );
        c.setName( applicant.getName() );
        c.setCreditScore( applicant.getCreditScore() != null ? Integer.parseInt( applicant.getCreditScore() ) : null );
        c.setIncome( applicant.getIncome() != null ? Integer.parseInt( applicant.getIncome() ) : null );

        MortgageApplication ma = new MortgageApplication(  );
        ma.setAmortization( mortgage.getAmortization() != null ? Integer.parseInt( mortgage.getAmortization() ) : null );
        ma.setInterestRate( mortgage.getInterest() != null ? Double.parseDouble( mortgage.getInterest() ) : null );
        ma.setAmount( mortgage.getAmount() != null ? Integer.parseInt( mortgage.getAmount() ) : null );

        commands.add( cf.newInsert( c ) );
        commands.add( cf.newInsert( ma, MA_OUT_ID ) );
        commands.add( cf.newFireAllRules() );
        commands.add( cf.newDeleteObject( c, EntryPointId.DEFAULT.getEntryPointId() ) );
        commands.add( cf.newDeleteObject( ma, EntryPointId.DEFAULT.getEntryPointId() ) );

        ServiceResponse<String> reply = ruleClient.executeCommands( CONTAINER_ID, batchExecution );
        String approved = "No";
        if( reply.getType() == ServiceResponse.ResponseType.SUCCESS ) {
            ExecutionResults results = marshaller.unmarshall( reply.getResult(), ExecutionResultImpl.class );
            MortgageApplication result = (MortgageApplication) results.getValue( MA_OUT_ID );
            if( result.isApproved() ) {
                approved = "Yes";
            }
        }

        return new Mortgage( mortgage.getTerm(), mortgage.getAmortization(), mortgage.getAmount(), mortgage.getInterest(),
                             approved );
    }
}
