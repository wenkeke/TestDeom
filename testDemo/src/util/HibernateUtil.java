package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final ThreadLocal<Session> sessionTL = new ThreadLocal<Session>();
	private static Configuration configuration;
	private final static SessionFactory sessionFactory;

	static {
		try {
			configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session currentSession() {
		Session session = sessionTL.get();
		if (session == null) {
			session = sessionFactory.openSession();
			sessionTL.set(session);
		}
		return session;
	}

	public static void closeSession() {
		Session session = sessionTL.get();
		if (null != session) {
			sessionTL.set(null);
			session.close();
		}
	}

}
