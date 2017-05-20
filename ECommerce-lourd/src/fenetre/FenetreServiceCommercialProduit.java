/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Amélien
 */
public class FenetreServiceCommercialProduit {
    
    private JButton bouton1 = new JButton("Ajouter un produit");
    private JButton bouton2 = new JButton("Modifier un produit");
    private JButton bouton3 = new JButton("Supprimer un produit");
    
    private JButton retour = new JButton("Retour");
    
    public JPanel FenetreServiceCommercialProduit(){
        
        JPanel JP = new JPanel();
        
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

        return JP;
    }
    
    
}
