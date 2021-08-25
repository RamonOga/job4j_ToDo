package trash.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HumanRun {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            City city1 = City.of("Moscow");
            City city2 = City.of("Berlin");
            City city3 = City.of("London");
            City city4 = City.of("Paris");
            City city5 = City.of("New York");

            transaction = session.beginTransaction();

            session.save(city1);
            session.persist(city2);
            session.save(city3);
            session.save(city4);
            session.save(city5);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
