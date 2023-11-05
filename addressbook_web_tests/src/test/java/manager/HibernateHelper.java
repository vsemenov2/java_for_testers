package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactDate;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

   private SessionFactory sessionFactory;
    public HibernateHelper(ApplicationManager manager){
        super(manager);

        sessionFactory = new Configuration()
                //.addAnnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records){
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }


    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)){
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return  session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return  session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

static List<ContactDate> convertlist(List<ContactRecord> records){
        List<ContactDate> result = new ArrayList<>();
        for (var record : records) {
            result.add(convertContact(record));
        }
        return result;
 }


    private static ContactDate convertContact(ContactRecord record) {
        return new ContactDate("" + record.id, record.firstname, record.middlename, record.lastname, record.address, "");
    }

    private static ContactRecord convertContact(ContactDate data) {
        var id = data.id();
        if ("".equals(id)){
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstname(), data.middlename(), data.lastname(), data.address());
    }


    public List<ContactDate> getContactList(){
        return  convertlist(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactDate contactDate) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contactDate));
            session.getTransaction().commit();
        });
    }
}
