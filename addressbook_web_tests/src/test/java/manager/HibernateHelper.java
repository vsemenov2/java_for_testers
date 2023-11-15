package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactDate;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

   private SessionFactory sessionFactory;
    public HibernateHelper(ApplicationManager manager){
        super(manager);

        sessionFactory = new Configuration()
                //.addAnnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertTONull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records){
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());

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
        return convertGroupList(sessionFactory.fromSession(session -> {
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

static List<ContactDate> convertContactList(List<ContactRecord> records){
    return records.stream().map(HibernateHelper::convertContact).collect(Collectors.toList());

 }


    private static ContactDate convertContact(ContactRecord record) {
        return new ContactDate("" + record.id,
                record.firstname,
                record.middlename,
                record.lastname,
                record.nickname,
                record.company,
                record.title,
                record.address,
                record.home,
                record.mobile,
                record.work,
                record.phone2,
                record.email,
                record.email2,
                record.email3,
                record.address2);
    }

    private static ContactRecord convertContact(ContactDate data) {
        var id = data.id();
        if ("".equals(id)){
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id),
                data.firstname(),
                data.middlename(),
                data.lastname(),
                data.nickname(),
                data.company(),
                data.title(),
                data.address(),
                data.address2(),
                data.home(),
                data.mobile(),
                data.work(),
                data.secondary(),
                data.email(),
                data.email2(),
                data.email3());
    }


    public List<ContactDate> getContactList(){
        return  convertContactList(sessionFactory.fromSession(session -> {
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

    public List<ContactDate> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
    public List<GroupData> getGroupsInContact(ContactDate contactDate) {
        return sessionFactory.fromSession(session -> {
            return convertGroupList(session.get(ContactRecord.class, contactDate.id()).groups);
        });
    }

    public List<ContactDate> getContactsNotInGroup() {
        var allContacts = getContactList();
        allContacts.removeIf(contactDate -> {
            var groups = getGroupsInContact(contactDate);
            return (groups != null) && (!groups.isEmpty());
        });
        return allContacts;
    }
}
