
package cinema.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {

    private static final ApplicationContext context =
        new ClassPathXmlApplicationContext(
            "cinema/resources/applicationContext.xml"
        );

    public static ApplicationContext getContext() {
        return context;
    }
}
