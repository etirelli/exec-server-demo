/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.kie.demo.bankapp.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.demo.bankapp.model.Applicant;
import org.kie.demo.bankapp.model.Mortgage;
import org.kie.demo.bankapp.service.DecisionServerService;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberView;

@ApplicationScoped
@WorkbenchScreen(identifier = "MortgageForm")
public class MortgageFormPresenter {

    public interface View extends UberView<MortgageFormPresenter> {

        String getName();

        String getAge();

        String getIncome();

        String getAmount();

        String getAmortization();

        String getTerm();

        String getScore();

        String getApproved();

        String getInterest();

        String getService();

        void setName( final String name );

        void setAge( final String age );

        void setIncome( final String income );

        void setAmount( final String amount );

        void setAmortization( final String amortization );

        void setTerm( final String term );

        void setScore( final String score );

        void setApproved( final String approved );

        void setInterest( final String interest );

        void setService( final String service );

        void setAsApproved();

        void setAsNotApproved();
    }

    @Inject
    private Caller<DecisionServerService> decisionServerService;

    @Inject
    private View view;

    @PostConstruct
    public void init() {
        view.init( this );
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Mortgage Application";
    }

    @WorkbenchPartView
    public IsWidget getView() {
        return view;
    }

    int count = 0;

    public void apply() {
        count++;
        decisionServerService.call( new RemoteCallback<Mortgage>() {
            @Override
            public void callback( final Mortgage result ) {
                if ( "Yes".equalsIgnoreCase( result.getApproved() ) ) {
                    view.setAsApproved();
                } else {
                    view.setAsNotApproved();
                }
            }
        } ).process( view.getService(),
                     new Applicant( view.getName(), view.getAge(), view.getIncome(), view.getScore() ),
                     new Mortgage( view.getTerm(), view.getAmortization(), view.getAmount(), view.getInterest(), view.getApproved() ));
    }
}
