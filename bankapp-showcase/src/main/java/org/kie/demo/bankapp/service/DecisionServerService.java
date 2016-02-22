package org.kie.demo.bankapp.service;

import org.jboss.errai.bus.server.annotations.Remote;
import org.kie.demo.bankapp.model.Applicant;
import org.kie.demo.bankapp.model.Mortgage;

@Remote
public interface DecisionServerService {

    Mortgage process( final Applicant applicant, final Mortgage mortgage );

}
