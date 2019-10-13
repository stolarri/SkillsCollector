package pl.st.skills.listeners;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import pl.st.skills.model.entity.Skill;
import pl.st.skills.model.entity.Source;
import pl.st.skills.model.entity.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebListener
public class HibernateInitializer implements ServletContextListener {
    private Logger logger = Logger.getLogger(HibernateInitializer.class.getName());

    public final static String SESSION_FACTORY = "session_factory";


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            Configuration configuration = new Configuration();

            Properties hbnProperties = new Properties();
            hbnProperties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            hbnProperties.put(Environment.URL, "jdbc:mysql://localhost:3306/skills_collector?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
            // Nazwę użytkownika dostosuj do swojej instalacji MySQL
            hbnProperties.put(Environment.USER, "uber_janusz");
            // Hasło użytkownika dostosuj do swojej instalacji MySQL
            hbnProperties.put(Environment.PASS, "root");
            hbnProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            hbnProperties.put(Environment.SHOW_SQL, "true");
            hbnProperties.put(Environment.FORMAT_SQL, "true");
            hbnProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            // W przypadku gdy silnik Hibernate ma tworzyć schemat bazy danych, to poniżej
            // użyj opcji create-drop albo update
            hbnProperties.put(Environment.HBM2DDL_AUTO, "create-drop");
            configuration.setProperties(hbnProperties);

            // Odkomentuj poniższe instrukcje po utworzeniu klas encji (kolejne zadania)

            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Source.class);
            configuration.addAnnotatedClass(Skill.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sce.getServletContext().setAttribute(SESSION_FACTORY, sessionFactory);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Błąd konfiguracji Hibernate!", e);
        }

    }
}
