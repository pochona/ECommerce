/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;


import app.App;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Amelien
 */

public class Fenetre extends JFrame {
    App app;
    
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuComm = new JMenu("Service commercial");
    private final JMenu menuCompt = new JMenu("Service comptable");
    private final JMenu menuReapro = new JMenu("Service réapprovisionnement");
    private final JMenu menuLivraison = new JMenu("Service livraison");
    private final JMenu menuAutre = new JMenu("Autre");

    private final JMenuItem itemSuivreCommande = new JMenuItem("Suivre commande");
    private final JMenuItem itemGererPrlv = new JMenuItem("Gérer les prélèvements");
    private final JMenuItem itemCreerProduit = new JMenuItem("Créer un produit");
    private final JMenuItem itemAfficheProduit = new JMenuItem("Gérer tous les produits");
    private final JMenuItem itemGererStock = new JMenuItem("Gérer le stock");
    private final JMenuItem itemDeclencherLivraison = new JMenuItem("Déclencher la livraison");
    private final JMenuItem itemFermer = new JMenuItem("Fermer");
    private final JMenuItem itemInfo = new JMenuItem("Information");
    
    /*
    private JButton gererAffichage;
    private JTextArea errorText;
    private JList produitList;
*/
    private Ecouteur unEcouteur;

    public Fenetre(App app){
        this.app = app;
        this.unEcouteur = new Ecouteur(this, this.app);
        this.configurerFenetre();
        this.configurerMenu();
        this.setVisible(true);
    }
    
    private void configurerFenetre(){
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Projet Ecommerce");
    }
  
    private void configurerMenu(){
        this.menuComm.add(itemSuivreCommande); //On initialise nos menus avec add - Suivre commande
        itemSuivreCommande.addActionListener(unEcouteur);

        this.menuLivraison.add(itemSuivreCommande);
        this.menuLivraison.addSeparator();
        
        this.menuLivraison.add(itemDeclencherLivraison); // déclencher livraison
        itemDeclencherLivraison.addActionListener(unEcouteur);

        itemSuivreCommande.addActionListener(unEcouteur);
        
        this.menuCompt.add(itemGererPrlv);
        itemGererPrlv.addActionListener(unEcouteur);
        
        this.menuComm.add(itemCreerProduit);
        itemCreerProduit.addActionListener(unEcouteur);
         
        this.menuComm.add(itemAfficheProduit); 
        itemAfficheProduit.addActionListener(unEcouteur);
        
        this.menuReapro.add(itemGererStock); // gérer le stock
        itemGererStock.addActionListener(unEcouteur);
        
        this.menuAutre.add(itemInfo); // info
        itemInfo.addActionListener(unEcouteur);
        
        this.menuAutre.addSeparator();
        
        this.menuAutre.add(itemFermer); // fermer
        itemFermer.addActionListener(unEcouteur);

        
        this.menuBar.add(menuComm);
        this.menuBar.add(menuCompt);
        this.menuBar.add(menuReapro);
        this.menuBar.add(menuLivraison);
        this.menuBar.add(menuAutre);
        this.setJMenuBar(menuBar);
    }
    
    private JPanel concepteur(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("<html> Application realisée par : <br><br> - BLONDEAU Laura <br>- MERSCH Amélien <br> - POCHON Amaury <br><br><br> Dans le cadre du projet J2EE (M1 MIAGE) proposé par : <br><br> TORGUET Patrice et TEISSIER Cédric</html>");
        panel.add(label);
        return panel;
    }
}