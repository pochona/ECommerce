/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import fenetre.Fenetre;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import services.ServiceCommercialRemote;

/**
 *
 * @author Amaury_PC
 */
public class App {
    
    Fenetre fenetre;
    
    @EJB
    private ServiceCommercialRemote serviceCommercial;
    
    public App(){
        this.creerLienNaming();
        
        Fenetre f = new Fenetre(this);
    }
    
    public void creerLienNaming(){
         // Connection 
        System.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        InitialContext context;
        try {
            context = new InitialContext();
            this.serviceCommercial = (ServiceCommercialRemote) context.lookup("services.ServiceCommercialRemote");
        } catch (NamingException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ServiceCommercialRemote getServiceCommercial(){
        return this.serviceCommercial;
    }
}
