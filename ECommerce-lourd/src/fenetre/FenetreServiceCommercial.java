/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Amélien
 */

public class FenetreServiceCommercial {
    
    private JButton bouton1 = new JButton("Suivre commande");
    private JButton bouton2 = new JButton("Gérer les prélèvements");
    private JButton bouton3 = new JButton("Gérer l'affichage des produits");
    
    private JButton retour = new JButton("Retour");
    
    private JLabel nomService = new JLabel("SERVICE COMMERCIAL");
    
    JPanel JP = new JPanel();
    
    public JPanel FenetreServiceCommercial(){
        
        //JPanel JP = new JPanel();
        
        //JP.add(nomService);
        JP.setLayout(null);
        bouton1.setBounds(200, 20, 300, 80);
        JP.add(bouton1);
        bouton1.setBounds(200, 50, 300, 80);
        JP.add(bouton2);
        bouton2.setBounds(200, 150, 300, 80);
        JP.add(bouton3);
        bouton3.setBounds(200, 250, 300, 80);
        JP.add(retour);
        retour.setBounds(200, 350, 300, 80);
        
        bouton1.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                FenetreServiceCommercialProduit fscp = new FenetreServiceCommercialProduit();

                JP=fscp.FenetreServiceCommercialProduit();
            }
        }
        );
        
        JP.add(retour);
        /*test.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JP.init();
            }
        }
        );*/
        return JP;
    }
}
