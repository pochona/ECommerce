/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.lourd;

import exceptions.ExceptionClient;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import services.ServiceBanqueRemote;

/**
 *
 * @author Amaury
 */
public class ECommerceLourd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException, ExceptionClient {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        // glassfish default port value will be 3700,
       props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
       InitialContext ctx = new InitialContext(props);
       NamingEnumeration<NameClassPair> list = ctx.list("");
while (list.hasMore()) {
  System.out.println(list.next().getName());
}
        //ServiceBanqueRemote souche = (ServiceBanqueRemote) ctx.lookup("services.ServiceBanqueRemote");
        /*
        System.setProperty("java.naming.factory.initial",
        "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost",
        "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort",
        "3700");
        InitialContext context = new InitialContext();
        
        ServiceBanqueRemote souche = (ServiceBanqueRemote) context.lookup("services.ServiceBanqueRemote");
*/        
       /* long idCl = 0;
        try {
            idCl = souche.chercherClient("pochon", "amaury");
        } catch(Exception e) {
            System.out.println("erreur, client inconnu");
        }
        System.out.println("Id Client : " + idCl);*/
    }
    
}
