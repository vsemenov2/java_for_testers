package model;

public record ContactDate(String firstname, String middlename, String lastname, String address) {

    public ContactDate() {
        this("", "", "", "");

    }

    public ContactDate withFirstname(String firstname) {
        return new ContactDate(firstname, this.middlename, this.lastname, this.address );
    }
    public ContactDate withMiddlename(String middlename) {
        return new ContactDate(this.firstname, middlename, this.lastname, this.address );
    }
    public ContactDate withLastname(String lastname) {
        return new ContactDate(this.firstname, this.middlename, lastname, this.address );
    }
    public ContactDate withAddress(String address) {
        return new ContactDate(this.firstname, this.middlename, this.lastname, address);
    }
}