/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame_laura;

import java.awt.BorderLayout;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import services.ServiceBanqueRemote;

/**
 *
 * @author Laura
 */
public class Frame_suiviComm extends JFrame implements ListSelectionListener{
    
JButton bouton = new JButton("Valider");
JList liste = new JList();
JLabel etiquette = new JLabel(" ");


    public Frame_suiviComm() throws NamingException {
        
        initFrame();
    }
    
    public void initFrame() throws NamingException {
        
        System.setProperty("java.naming.factory.initial",
        "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost",
        "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort",
        "3700");
        InitialContext context = new InitialContext();
        
        ServiceBanqueRemote souche = (ServiceBanqueRemote) context.lookup("services.ServiceBanqueRemote");
        long idCl=99;
        System.out.println("Début");
        try {
            //idCl = souche.chercherClient("Blondeau", "Laura");
            idCl=souche.findIdComByClient(1);
        } catch(Exception e) {
            System.out.println("EXCEPTION");
        }
        System.out.println("Aprés avoir été chercher le client, idCl = "+idCl);
    
    
        String description = "Pour cuisiner";
        String lib = "Casserole";
        String libReturn ="";
        String choix[] = {libReturn, " item3", " item4", " item5"};
        
        liste = new JList(choix);
        liste.addListSelectionListener(this);
        add(etiquette, BorderLayout.WEST);
        add(liste, BorderLayout.WEST);
        add(bouton,BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }
    
    public void afficheArticle() {
    
        
    }

     public void valueChanged(ListSelectionEvent evt) { 
        etiquette.setText((String)liste.getSelectedValue());
    }
    
}
