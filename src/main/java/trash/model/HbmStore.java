package trash.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmStore implements AutoCloseable {
    private SessionFactory sf;

    private HbmStore() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    private static final class Lazy {
        private static final HbmStore INSTANCE = new HbmStore();
    }

    public static HbmStore instanceOf() {
        return Lazy.INSTANCE;
    }

    public void addNewHuman(Human human, String[] ids) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            for (String id : ids) {
                City city = session.find(City.class, Integer.parseInt(id));
                human.addCity(city);
            }
            session.save(human);

            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
    }

    public List<City> allCities() {
        List<City> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            rsl = session.createQuery("select c from City c", City.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        sf.close();
    }
}
