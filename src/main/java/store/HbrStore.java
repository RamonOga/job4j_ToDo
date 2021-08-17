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
import java.util.function.Function;

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
    public List<Item> findAll() {
        return this.tx((a) -> a.createQuery("from model.Item", Item.class).list(), "findAll");
    }

    @Override
    public boolean add(Item item) {
        int id = item.getId();
        String message = "Add with argument '" + item + "'";
        return this.tx(a -> {
            a.save(item);
            return id != item.getId();
        }, message);
    }

    @Override
    public boolean delete(String id) {
        String message = "Delete with argument '" + id + "'";
        return this.tx((a)-> {
                    Item item = a.get(Item.class, Integer.parseInt(id));
                    a.delete(item);
                    return true;
                }, message);
    }

    @Override
    public Item findById(String id) {
        String message = "findById this argument by '" + id + "'";
            return this.tx((session) -> session.get(Item.class, Integer.parseInt(id)), message);

    }



    @Override
    public void done(String id) {
        String message = "Update with argument by '" + id + "'";
        this.tx(session -> {
            Item item = session.get(Item.class, Integer.parseInt(id));
        if (item == null) {
            throw new NoSuchElementException("Item with ID " + id + "not found!");
        }
        item.setDoneTrue();
        session.update(item);
        return item;
        }, message);
    }

    private <T> T tx(final Function<Session, T> command, String message) {
        Transaction transaction = null;
        try(Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            LOG.info("\nOperation " + message + " completed successfully!\n");
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
                LOG.info("\n.......Transaction Is Being Rolled Back.......\n");
            }
            LOG.error(e.getMessage());
            throw e;
        }
    }



    private static final class LAZY {
        private static final Store INST = new HbrStore();
    }

    public static Store instOf() {
        return LAZY.INST;
    }
}
