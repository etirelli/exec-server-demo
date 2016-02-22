package org.kie.demo.bankapp.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class Mortgage {

    private String amount;
    private String amortization;
    private String term;
    private String interest;
    private String approved;

    public Mortgage() {

    }

    public Mortgage( final String term,
                     final String amortization,
                     final String amount,
                     final String interest,
                     final String approved ) {
        this.term = term;
        this.amortization = amortization;
        this.amount = amount;
        this.interest = interest;
        this.approved = approved;
    }

    public String getAmount() {
        return amount;
    }

    public String getAmortization() {
        return amortization;
    }

    public String getTerm() {
        return term;
    }

    public String getInterest() {
        return interest;
    }

    public String getApproved() {
        return approved;
    }

    @Override
    public String toString() {
        return "Mortgage{" +
               "amount='" + amount + '\'' +
               ", amortization='" + amortization + '\'' +
               ", term='" + term + '\'' +
               ", interest='" + interest + '\'' +
               ", approved='" + approved + '\'' +
               '}';
    }
}
