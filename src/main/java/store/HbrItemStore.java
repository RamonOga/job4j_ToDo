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

public class HbrItemStore extends HbrService implements ItemStore {


    private HbrItemStore() {
    }

    @Override
    public List<Item> findAll() {
        return this.tx((session) -> session.createQuery("from model.Item", Item.class).list(), "findAll");
    }

    @Override
    public boolean add(Item item) {
        int id = item.getId();
        String message = "Add with argument '" + item + "'";
        return this.tx(session -> {
            session.save(item);
            return id != item.getId();
        }, message);
    }

    @Override
    public boolean delete(String id) {
        String message = "Delete with argument '" + id + "'";
        return this.tx((session)-> {
                    Item item = session.get(Item.class, Integer.parseInt(id));
                    session.delete(item);
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
        try(Session session = super.sf.openSession()) {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            super.LOG.info("\nOperation " + message + " completed successfully!\n");
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
                super.LOG.info("\n.......Transaction Is Being Rolled Back.......\n");
            }
            super.LOG.error(e.getMessage());
            throw e;
        }
    }



    private static final class LAZY {
        private static final ItemStore INST = new HbrItemStore();
    }

    public static ItemStore instOf() {
        return LAZY.INST;
    }
}
