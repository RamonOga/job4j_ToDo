package store;

import model.Item;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;

public class HbrUserStore extends HbrService implements UserStore {

    @Override
    public void add(User user) {
        tx(session -> session.save(user));
    }

    @Override
    public User findByName(String name) {
        return tx(
                session -> session.createQuery("from model.User", User.class)
                        .list()
                        .stream()
                        .filter(user -> user.getLogin().equals(name))
                        .findFirst()
                        .orElse(new User(-1))
        );
    }

    @Override
    public User findById(String id) {
        return this.tx((session) -> session.get(User.class, Integer.parseInt(id)));
    }

    @Override
    public List<User> findAll() {
        return this.tx((session) -> session.createQuery("from model.User", User.class).list());
    }

    private <T> T tx(final Function<Session, T> command) {
        Transaction transaction = null;
        try (Session session = super.sf.openSession()) {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            super.LOG.info("\nOperation completed successfully!\n");
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
        private static final UserStore INST = new HbrUserStore();
    }

    public static UserStore instOf() {
        return HbrUserStore.LAZY.INST;
    }

}
