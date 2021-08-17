package store;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

public class HbrUserStore extends HbrService implements UserStore {

    @Override
    public void add(User user) {
        tx(session -> session.save(user));
    }

    @Override
    public User findByName(String name) {
        return tx(session -> session.get(User.class, name));
    }

    private <T> T tx(final Function<Session, T> command) {
        Transaction transaction = null;
        try(Session session = super.sf.openSession()) {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            super.LOG.info("\nOperation completed successfully!\n");
            transaction.commit();
            return rsl;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                super.LOG.info("\n.......Transaction Is Being Rolled Back.......\n");
            }
            super.LOG.error(e.getMessage());
            throw e;
        }
    }
}
