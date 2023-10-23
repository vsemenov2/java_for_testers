package model;

public record ContactDate(String id, String firstname, String middlename, String lastname, String address) {

    public ContactDate() {
        this("", "", "", "", "");

    }

    public ContactDate withId(String id) {
        return new ContactDate(id, this.firstname, this.middlename, this.lastname, this.address );
    }
    public ContactDate withFirstname(String firstname) {
        return new ContactDate(this.id, firstname, this.middlename, this.lastname, this.address );
    }
    public ContactDate withMiddlename(String middlename) {
        return new ContactDate(this.id, this.firstname, middlename, this.lastname, this.address );
    }
    public ContactDate withLastname(String lastname) {
        return new ContactDate(this.id, this.firstname, this.middlename, lastname, this.address );
    }
    public ContactDate withAddress(String address) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, address);
    }
}