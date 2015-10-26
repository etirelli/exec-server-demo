package org.kie.demo.bankapp.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class Mortgage {

    private String amount;
    private String amortization;
    private String term;

    public Mortgage() {

    }

    public Mortgage( final String term,
                     final String amortization,
                     final String amount ) {
        this.term = term;
        this.amortization = amortization;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Mortgage{" +
                "amount='" + amount + '\'' +
                ", amortization='" + amortization + '\'' +
                ", term='" + term + '\'' +
                '}';
    }
}
