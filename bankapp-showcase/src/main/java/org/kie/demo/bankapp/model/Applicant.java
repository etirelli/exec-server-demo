package org.kie.demo.bankapp.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class Applicant {

    private String name;
    private String age;
    private String income;
    private String creditScore;

    public Applicant() {

    }

    public Applicant( final String name,
                      final String age,
                      final String income,
                      final String creditScore ) {
        this.name = name;
        this.age = age;
        this.income = income;
        this.creditScore = creditScore;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getIncome() {
        return income;
    }

    public String getCreditScore() { return creditScore; }

    @Override
    public String toString() {
        return "Applicant{" +
               "name='" + name + '\'' +
               ", age='" + age + '\'' +
               ", income='" + income + '\'' +
               ", creditScore='" + creditScore + '\'' +
               '}';
    }
}
