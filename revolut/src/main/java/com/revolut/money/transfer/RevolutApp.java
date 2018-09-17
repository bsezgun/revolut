package com.revolut.money.transfer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.revolut.money.transfer.controller.RevolutEntryPoint;
import com.revolut.money.transfer.controller.UserController;

public class RevolutApp {

	public static void main(String[] args)  throws Exception {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        
       
        Map<String,String> initMap=new HashMap<String, String>();
        
        initMap.put("jersey.config.server.provider.classnames", RevolutEntryPoint.class.getCanonicalName());
        initMap.put("jersey.config.server.provider.classnames", UserController.class.getCanonicalName());
        
        jerseyServlet.setInitParameters(initMap);
        /*
        jerseyServlet.setInitParameter(
           "jersey.config.server.provider.classnames",
           RevolutEntryPoint.class.getCanonicalName());

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
               UserController.class.getCanonicalName());
        */
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }

	}

}
