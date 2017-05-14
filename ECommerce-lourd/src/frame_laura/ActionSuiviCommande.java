/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame_laura;

import exceptions.ExceptionClient;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import services.ServiceBanqueRemote;

/**
 *
 * @author Laura
 */
public class ActionSuiviCommande extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Ouverture de la fenêtre suivi de commande");
        try {
            new Frame_suiviComm().setVisible(true);
        } catch (NamingException ex) {
            Logger.getLogger(ActionSuiviCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
