package de.rottstegge.codechat.webapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {

    private String salutation;
    private String firstName;
    private String lastName;
    private String phone1;
    private String phone2;
    private String eMail1;
    private String eMail2;
    private String website;

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Column(name = "email1")
    public String geteMail1() {
        return eMail1;
    }

    public void seteMail1(String eMail1) {
        this.eMail1 = eMail1;
    }

    @Column(name = "email2")
    public String geteMail2() {
        return eMail2;
    }

    public void seteMail2(String eMail2) {
        this.eMail2 = eMail2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}