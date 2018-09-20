package com.revolut.money.transfer;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.revolut.money.transfer.controller.AccountController;
import com.revolut.money.transfer.controller.TransferController;
import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDolar;
import com.revolut.money.transfer.service.AccountService;
import com.revolut.money.transfer.service.DolarAccountService;
/**
 * 
 * @author bsezgun
 * @category Main
 * @since   2018-09-19
 * @version v.1.0.1
 * @comment
 *  This is the main class and declared in the pom.xml. 
 *  <br>It starts a Jetty Server to get Rest requests from other services by {@link TransferController} and {@link AccountController} classes.
 *  <br>TransferController and AccountController classes publish the rest end points of this application. It creates demo accounts for test-purpose at startup.
 */
public class RevolutApp {

	public static void main(String[] args)  throws Exception {
		loadApplicationProperties();
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/revolut/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "com.revolut.money.transfer.controller"
        );
        
       createTestAccounts();
       
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }

	}
	
	public static Properties applicationProperties;
	
	private static void loadApplicationProperties() throws IOException {
		applicationProperties = new Properties();
		InputStream f=Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		applicationProperties.load(f);
		f.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public static void createTestAccounts() {
		   AccountService service=new DolarAccountService();
	       service.depositAccount(new BigDecimal(1), new BigDecimal(500d));
	       service.depositAccount(new BigDecimal(2), new BigDecimal(1500d));
	       service.depositAccount(new BigDecimal(3), new BigDecimal(2500d));
	       
	       List<? extends Account> accounts=service.getAllAccounts();
	       
	       Collections.sort((List<AccountDolar>)accounts);
	       accounts.forEach(account->System.out.println(account));
	       assertNotNull(accounts);
	       
	}

}
