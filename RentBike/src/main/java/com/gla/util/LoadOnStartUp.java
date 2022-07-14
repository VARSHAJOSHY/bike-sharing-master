package com.gla.util;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

 

/**
 * Start up method
 * 
 * @author CE Automation - Etisalat
 * @version 1.0
 */

@SuppressWarnings("serial")
public class LoadOnStartUp extends HttpServlet {


    public static Properties props = null;

    public void init() throws ServletException {

       
        try {
      
           // HibernateUtilMySql.getSessionFactory();  
			System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
			System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
			System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
			System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
			
		
 
        } catch (Exception e) {
        }


    }

    @Override
    public void destroy() {
        
        //HibernateUtilMySql.shutdown();
  super.destroy();
    }
    
   
    
}