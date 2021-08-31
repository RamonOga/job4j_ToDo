package store;

import model.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Function;

public class HbrCategoryStore extends HbrService implements CategoryStore {


    @Override
    public void add(Category category) {
        tx(
                session -> session.save(category),
                "add"
        );
    }

    @Override
    public List<Category> findAll() {
       return this.tx(
               session -> session.createQuery("from model.Category", Category.class).list(),
               "findAll"
       );
    }

    @Override
    public Category findById(String id) {
        return tx(
                session -> session.get(Category.class, Integer.parseInt(id)),
                "findById"
        );
    }

    private <T> T tx(final Function<Session, T> command, String message) {
        Transaction transaction = null;
        try (Session session = super.sf.openSession()) {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            super.LOG.info("\nOperation " + message + " in HbrCategoryStore completed successfully!\n");
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
        private static final CategoryStore INST = new HbrCategoryStore();
    }
    public static CategoryStore instOf() {
        return LAZY.INST;
    }
}


