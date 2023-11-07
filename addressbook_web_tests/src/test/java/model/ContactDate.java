package model;

public record ContactDate(String id, String firstname, String middlename, String lastname, String nickname, String company, String title, String address, String home, String mobile, String work) {

    public ContactDate() {
        this("", "", "", "", "", "", "", "", "", "", "");

    }

    public ContactDate withId(String id) {
        return new ContactDate(id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work);
    }
    public ContactDate withFirstname(String firstname) {
        return new ContactDate(this.id, firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work);
    }
    public ContactDate withMiddlename(String middlename) {
        return new ContactDate(this.id, this.firstname, middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work );
    }
    public ContactDate withLastname(String lastname) {
        return new ContactDate(this.id, this.firstname, this.middlename, lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work);
    }
    public ContactDate withNickname(String nickname) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, nickname, this.company, this.title, this.address, this.home, this.mobile, this.work);
    }
    public ContactDate withCompany(String company) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, company, this.title, this.address, this.home, this.mobile, this.work);
    }
    public ContactDate withTitle(String title) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, title, this.address, this.home, this.mobile, this.work);
    }
    public ContactDate withAddress(String address) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, address, this.home, this.mobile, this.work);
    }
    public ContactDate withHome(String home) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, home, this.mobile, this.work);
    }
    public ContactDate withMobile(String mobile) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, mobile, this.work);
    }
    public ContactDate withWork(String work) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, work);
    }
}