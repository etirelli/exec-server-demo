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

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ValidationState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.demo.bankapp.client.util.StyleHelper;

@Dependent
@Templated
public class MortgageFormView extends Composite
        implements MortgageFormPresenter.View {

    @Inject
    @DataField
    TextBox name;

    @Inject
    @DataField
    TextBox age;

    @Inject
    @DataField
    TextBox income;

    @Inject
    @DataField
    TextBox amount;

    @Inject
    @DataField
    TextBox amortization;

    @Inject
    @DataField
    TextBox term;

    @Inject
    @DataField
    TextBox score;

    @Inject
    @DataField
    TextBox approved;

    @DataField("approved-form")
    Element approvedForm = DOM.createDiv();

    @Inject
    @DataField
    TextBox interest;

    @Inject
    @DataField
    Button apply;

    private MortgageFormPresenter presenter;

    @Override
    public void init( final MortgageFormPresenter presenter ) {
        this.presenter = presenter;
    }

    @Override
    public String getName() {
        return name.getText();
    }

    @Override
    public String getAge() {
        return age.getText();
    }

    @Override
    public String getIncome() {
        return income.getText();
    }

    @Override
    public String getAmount() {
        return amount.getText();
    }

    @Override
    public String getAmortization() {
        return amortization.getText();
    }

    @Override
    public String getTerm() {
        return term.getText();
    }

    @Override
    public String getScore() {
        return score.getText();
    }

    @Override
    public String getApproved() {
        return approved.getText();
    }

    @Override
    public String getInterest() {
        return interest.getText();
    }

    public void setName( final String name ) {
        this.name.setText( name );
    }

    public void setAge( final String age ) {
        this.age.setText( age );
    }

    public void setIncome( final String income ) {
        this.income.setText( income );
    }

    public void setAmount( final String amount ) {
        this.amount.setText( amount );
    }

    public void setAmortization( final String amortization ) {
        this.amortization.setText( amortization );
    }

    public void setTerm( final String term ) {
        this.term.setText( term );
    }

    public void setScore( final String score ) {
        this.score.setText( score );
    }

    public void setApproved( final String approved ) {
        this.approved.setText( approved );
    }

    public void setInterest( final String interest ) {
        this.interest.setText( interest );
    }

    @Override
    public void setAsApproved() {
        StyleHelper.addUniqueEnumStyleName( approvedForm, ValidationState.class, ValidationState.SUCCESS );
        setApproved( "No" );
        this.approved.getElement().getStyle().setBackgroundColor( "#d5ff80" );
    }

    @Override
    public void setAsNotApproved() {
        StyleHelper.addUniqueEnumStyleName( approvedForm, ValidationState.class, ValidationState.ERROR );
        setApproved( "Yes" );
        this.approved.getElement().getStyle().setBackgroundColor( "#ff6666" );
    }

    @EventHandler("apply")
    public void onApply( final ClickEvent event ) {
        presenter.apply();
    }
}
