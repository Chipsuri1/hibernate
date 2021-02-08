import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String... args) throws ParseException {

        ConsoleApplication consoleApplication = new ConsoleApplication();
        consoleApplication.start();

    }
}