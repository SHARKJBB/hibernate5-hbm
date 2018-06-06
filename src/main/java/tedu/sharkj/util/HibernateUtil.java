package tedu.sharkj.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
    private final static SessionFactory FACTORY = createSessionFactory();

    private static SessionFactory createSessionFactory() {
        /*
      		//创建SessionFactory
		//加载核心配置文件
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
			.applySettings(cfg.getProperties()).buildServiceRegistry();
		//基于线程安全的创建
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
          */

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(
                        ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        return factory;
    }

    public static Session openSession() {
        return FACTORY.openSession();
    }

    public static void close(Session session) {
        if (session != null) {
            session.close();
        }
    }

}
