package com.revolut.money.transfer;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.revolut.money.transfer.controller.TransferController;
import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDolar;
import com.revolut.money.transfer.service.AccountTypes;
import com.revolut.money.transfer.service.DolarAccountService;
/**
 * 
 * @author bsezgun
 * @comment
 *  This is the main class and declare in pom.xml as the mainClass. 
 *  <br>It starts a Jetty Server to get Rest requests from other services by {@link TransferController} class.
 *  <br>TransferController class is the rest end point of this application.
 * 	@since
 *  18.09.2018
 *  @version
 *  v.1.0.1
 */
public class RevolutApp {

	public static void main(String[] args)  throws Exception {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "com.dovydasvenckus.jersey.resources"
        );
        
        Map<String,String> initMap=new HashMap<String, String>();
        initMap.put("com.sun.jersey.api.json.POJOMappingFeature","true");
        initMap.put("jersey.config.server.provider.classnames", TransferController.class.getCanonicalName());
        
        jerseyServlet.setInitParameters(initMap);
        
        
        createTestAccounts();
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }

	}
	
	
	@SuppressWarnings("unchecked")
	public static void createTestAccounts() {
		   AccountTypes service=new DolarAccountService();
	       service.depositAccount(new BigDecimal(1), new BigDecimal(500d));
	       service.depositAccount(new BigDecimal(2), new BigDecimal(1500d));
	       service.depositAccount(new BigDecimal(3), new BigDecimal(2500d));
	       
	       
	       List<? extends Account> accounts=service.getAllAccounts();
	       
	       Collections.sort((List<AccountDolar>)accounts);
	       accounts.forEach(account->System.out.println(account));
	       assertNotNull(accounts);
	       
	}

}
