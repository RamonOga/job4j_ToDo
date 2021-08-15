package store;

import logger.LogCreator;
import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;

public class HbrStore implements Store {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();

    private final static Logger LOG = LogCreator.getLogger();

    private HbrStore() {
    }

    @Override
    public boolean add(Item item) {
        Transaction transaction = null;
        boolean rsl = false;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            LOG.info("\nSuccessfully Created '" + item + "' Records In The Database!\n");
            rsl = true;
        } catch (Exception e) {
            LOG.info("\n.......Transaction Is Being Rolled Back.......\n");
            LOG.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }

        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        Transaction transaction = null;
        boolean rsl = false;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Item item = session.get(Item.class, Integer.parseInt(id));
            session.delete(item);
            transaction.commit();
            LOG.info("\nItem With Id?= " + id + " Is Successfully Deleted From The Database!\n");
            rsl = true;
        } catch (Exception e) {
            LOG.info("\n.......Transaction Is Being Rolled Back.......\n");
            LOG.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }


        }
        return rsl;
    }

    @Override
    public void done(String id) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            Item item = session.get(Item.class, Integer.parseInt(id));
            if (item == null) {
                throw new NoSuchElementException("Item with ID " + id + "not found!");
            }
            LOG.info("\nItem With Id?= " + id + " Is Successfully Done!\n");
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            LOG.info("\n.......Transaction Is Being Rolled Back.......\n");
            LOG.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            item = session.get(Item.class, Integer.parseInt(id));
            transaction.commit();
        } catch (Exception e) {
            LOG.info("\n.......Transaction Is Being Rolled Back.......\n");
            LOG.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return item;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = null;
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            rsl = session.createQuery("from model.Item", Item.class).list();
            transaction.commit();
        } catch (Exception e) {
            LOG.info("\n.......Transaction Is Being Rolled Back.......\n");
            LOG.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return rsl;
    }

    private static final class LAZY {
        private static final Store INST = new HbrStore();
    }

    public static Store instOf() {
        return LAZY.INST;
    }
}
