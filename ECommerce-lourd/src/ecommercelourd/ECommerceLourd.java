/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommercelourd;

import entitiesBis.ArticleBis;
import exceptions.ExceptionArticle;
import exceptions.ExceptionClient;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import services.ServiceBanqueRemote;
import services.ServiceCommercialRemote;

/**
 *
 * @author Amaury
 */
public class ECommerceLourd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException, ExceptionClient, ExceptionArticle {
        /*Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        // glassfish default port value will be 3700,
       props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
       InitialContext ctx = new InitialContext(props);
       //test
       NamingEnumeration<NameClassPair> list = ctx.list("");
while (list.hasMore()) {
  System.out.println(list.next().getName());
}*/
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
    
     //test Amélien
        
        /*Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("java.naming.provider.url", "localhost");*/
        
        //InitialContext context = new InitialContext(props);
        /*
        System.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");

        System.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");

        System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        InitialContext context = new InitialContext();
        */
        //appserv-rt.jar
        /*InitialContext context = new InitialContext();
        
        ArticleFacadeRemote articleFacadeRemote = (ArticleFacadeRemote) context.lookup("ecommercelourd.ArticleFacadeRemote");
        
        List<String> list = articleFacadeRemote.lister();

        for(String s : list) {
        System.out.println(s);
        }*/
        
        /*NamingEnumeration<NameClassPair> list = initialContext.list("");
        while (list.hasMore()) {
            System.out.println(list.next().getName());
        }*/
        
        // code proff TP BANQUE
        // TODO code application logic here
        System.setProperty("java.naming.factory.initial",
        "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost",
        "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort",
        "3700");
        InitialContext context = new InitialContext();
        
        // Test du service banque pour aller chercher l'ID d'un client
        
        ServiceBanqueRemote souche = (ServiceBanqueRemote) context.lookup("services.ServiceBanqueRemote");
        long idCl =  99;
        System.out.println("Avant d'aller dans la DB idCl = "+idCl);
        try {
            idCl = souche.chercherClient("Mersch", "Amélien");
        } catch(Exception e) {
            System.out.println("création");
            //idCl = souche.creerClient("Patrice", "Torguet");
        }
        System.out.println("Aprés avoir été chercher le client, idCl = "+idCl);
        System.out.println("------------------------");
        
        // Test du service commercial pour afficher la list des articles
        
            // Avec la méthode List<String> lister()
        ServiceCommercialRemote souche2 = (ServiceCommercialRemote) context.lookup("services.ServiceCommercialRemote");
        System.out.println("Retour méthode List<String> lister()");
        List<String> list = souche2.lister();

        for(String a : list) {
            System.out.println(a);
        }
        /*
        ServiceCommercialRemote souche3 = (ServiceCommercialRemote) context.lookup("services.ServiceCommercialRemote");
        System.out.println("------------------------");
        System.out.println("Retour méthode List<ArticleBis> listerbis()");
        List<ArticleBis> listbis = souche3.listerBis();

        for(ArticleBis a : listbis) {
            System.out.println(a.toString());
        }*/
    }
}
