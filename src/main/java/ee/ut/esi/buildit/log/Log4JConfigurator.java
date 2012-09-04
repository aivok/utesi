package ee.ut.esi.buildit.log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.xml.DOMConfigurator;


public class Log4JConfigurator implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DOMConfigurator.configureAndWatch("log4j.xml", 5000);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
