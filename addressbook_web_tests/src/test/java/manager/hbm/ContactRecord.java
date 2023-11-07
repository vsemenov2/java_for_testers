package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;
    public String firstname;
    public String middlename;
    public String lastname;
    public String nickname;
    public String company;
    public String title;
    public String address;
    public String home;
    public String mobile;
    public String work;

//    public String addr_status = new String("");
//    public String addr_long = new String("");
//    public String fax = new String("");
//   public String email = new String("");
//    public String email2 = new String("");
//    public String email3 = new String("");
//    public String im = new String("");
//    public String im2 = new String("");
//    public String im3 = new String("");
//    public String homepage = new String("");
//    public Integer bday = new Integer("0");
//    public String bmonth = new String();
//    public String byear = new String();
//    public Integer aday = new Integer(0);
//    public String amonth = new String("");
//    public String ayear = new String("");
//    public String address2 = new String("");
//    public String phone2 = new String("");
//    public String notes = new String("");
//    public Date deprecated = new Date();
//    public Date created=  new Date();
//    public Date modified =  new Date();
//    public String photo =  null;




    public ContactRecord() {}

    public ContactRecord(int id, String firstname, String middlename, String lastname, String nickname, String company, String title, String address, String home, String mobile, String work ) {

        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.title = title;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
    }
}
