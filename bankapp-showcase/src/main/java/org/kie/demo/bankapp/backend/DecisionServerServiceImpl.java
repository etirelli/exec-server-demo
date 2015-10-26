package org.kie.demo.bankapp.backend;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.kie.demo.bankapp.model.Applicant;
import org.kie.demo.bankapp.model.Mortgage;
import org.kie.demo.bankapp.service.DecisionServerService;

@Service
@ApplicationScoped
public class DecisionServerServiceImpl implements DecisionServerService {

    @Override
    public Mortgage process( final Applicant applicant ) {
        return new Mortgage( applicant.getIncome(), "dddd", "ddd" );
    }
}
