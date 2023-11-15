package model;

public record ContactDate(String id,
                          String firstname,
                          String middlename,
                          String lastname, String nickname,
                          String company,
                          String title, String address,
                          String home, String mobile,
                          String work, String secondary, String email, String email2, String email3, String address2) {

    public ContactDate() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

    }

    public ContactDate withId(String id) {
        return new ContactDate(id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withFirstname(String firstname) {
        return new ContactDate(this.id, firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withMiddlename(String middlename) {
        return new ContactDate(this.id, this.firstname, middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withLastname(String lastname) {
        return new ContactDate(this.id, this.firstname, this.middlename, lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withNickname(String nickname) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withCompany(String company) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withTitle(String title) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withAddress(String address) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withAddress2(String address2) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, address2);
    }
    public ContactDate withHome(String home) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withMobile(String mobile) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, mobile, this.work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withWork(String work) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, work, this.secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withSecondary(String secondary) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, secondary, this.email, this.email2, this.email3, this.address2);
    }
    public ContactDate withEmail(String email) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, email, this.email2, this.email3, this.address2);
    }
    public ContactDate withEmail2(String email2) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, email2, this.email3, this.address2);
    }
    public ContactDate withEmail3(String email3) {
        return new ContactDate(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.company, this.title, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, email3, this.address2);
    }

}