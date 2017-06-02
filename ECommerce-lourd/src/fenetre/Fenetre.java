/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;


import app.App;
import entitiesBis.ArticleBis;
import exceptions.ExceptionArticle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import panel.AfficheProduit;
import panel.CreerArticle;
import services.ServiceCommercialRemote;

/**
 *
 * @author Amelien
 */

public class Fenetre extends JFrame {
    App app;
    
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuComm = new JMenu("Service commercial");
    private JMenu menuCompt = new JMenu("Service comptable");
    private JMenu menuReapro = new JMenu("Service réapprovisionnement");
    private JMenu menuLivraison = new JMenu("Service livraison");
    private JMenu menuAutre = new JMenu("Autre");

    private JMenuItem itemSuivreCommande = new JMenuItem("Suivre commande");
    private JMenuItem itemGererPrlv = new JMenuItem("Gérer les prélèvements");
    private JMenuItem itemCreerProduit = new JMenuItem("Créer un produit");
    private JMenuItem itemAfficheProduit = new JMenuItem("Gérer tous les produits");
    private JMenuItem itemGererStock = new JMenuItem("Gérer le stock");
    private JMenuItem itemDeclencherLivraison = new JMenuItem("Déclencher la livraison");
    private JMenuItem itemFermer = new JMenuItem("Fermer");
    private JMenuItem itemInfo = new JMenuItem("Information");
    
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
    
    public void configurerFenetre(){
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Projet Ecommerce");
    }
  
    public void configurerMenu(){
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