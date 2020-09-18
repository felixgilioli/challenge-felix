package br.com.senior.server.checkin;

import io.github.gasparbarancelli.NativeQueryOperator;
import io.github.gasparbarancelli.NativeQueryParam;

public class CheckinFilter {

    @NativeQueryParam(value = "personName", operator = NativeQueryOperator.CONTAINING)
    private String personName;

    private PersonSituation personSituation;
    private String personDocument;
    private String personPhoneNumber;

    public PersonSituation getPersonSituation() {
        return personSituation;
    }

    public void setPersonSituation(PersonSituation personSituation) {
        this.personSituation = personSituation;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonDocument() {
        return personDocument;
    }

    public void setPersonDocument(String personDocument) {
        this.personDocument = personDocument;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }
}
