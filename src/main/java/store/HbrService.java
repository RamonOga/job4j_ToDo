package store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public abstract class HbrService {

    protected final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    protected final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();

    protected final Logger LOG = LogManager.getLogger();

}
