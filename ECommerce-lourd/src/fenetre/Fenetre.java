/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;


import entitiesBis.ArticleBis;
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
import services.ServiceCommercialRemote;

/**
 *
 * @author Amelien
 */

public class Fenetre extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu comm = new JMenu("Service commerciale");
    private JMenu comp = new JMenu("Service comptable");
    private JMenu reapro = new JMenu("Service réapprovisionnement");
    private JMenu livraison = new JMenu("Service livraison");
    private JMenu autre = new JMenu("Autre");

    private JMenuItem item1 = new JMenuItem("Suivre commande");
    private JMenuItem item1bis = new JMenuItem("Suivre commande");
    private JMenuItem item2 = new JMenuItem("Gérer les prélèvements");
    private JMenuItem item2bis = new JMenuItem("Gérer les prélèvements");
    private JMenuItem item31 = new JMenuItem("Modifier un produit");
    private JMenuItem item32 = new JMenuItem("Créer un produit");
    private JMenuItem item33 = new JMenuItem("Supprimer un produit");
    private JMenuItem item34 = new JMenuItem("Afficher tous les produits");
    private JMenuItem item4 = new JMenuItem("Gérer le stock");
    private JMenuItem item5 = new JMenuItem("Déclencher la livraison");
    private JMenuItem item6 = new JMenuItem("Fermer");
    private JMenuItem item7 = new JMenuItem("Information");
    
    private JButton gererAffichage;
    private JTextArea errorText;
    private JList produitList;
    
    @EJB
    private ServiceCommercialRemote serviceCommercial;
    
    
    private Connection connexion;
    private Statement statement;
    private ResultSet result;
    private String url = "jdbc:mysql://localhost:3306/jee?zeroDateTimeBehavior=convertToNull";
    private String login = "root";
    private String mdp = "";

    private Ecouteur unEcouteur = new Ecouteur(this);

    private class Ecouteur implements ActionListener{
	  
        private JFrame fen;
	  
	public Ecouteur(JFrame f){
            fen = f;
	}
	  
	public void actionPerformed(ActionEvent arg0){
            Object source = arg0.getSource();
            if ( source == item7){ // Information sur l'application
                setContentPane(concepteur());
                validate(); // maj des conteneurs
            }
            if ( source == item6){ // Fermer
                System.exit(0);
            }
            
            if ( source == item32){ try {
                // Créer un produit
                setContentPane(creerArticle());
                } catch (NamingException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
                validate(); // maj des conteneurs
            }
            
            if ( source == item34){ try {  // afficher tous les produits 
                setContentPane(affichageProduits());
                validate();
                } catch (NamingException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Ici, en fonction de la source, affichage.
            // Ex : On clique sur "Gérer l'affichage des produits" , doit s'afficher : la liste des produits avec possibilité de insert, update et delete
        }
    }

    public Fenetre(){
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        this.comm.add(item1); //On initialise nos menus avec add - Suivre commande
        item1.addActionListener(unEcouteur);
        this.livraison.add(item1bis);
        item1bis.addActionListener(unEcouteur);
        this.comm.addSeparator(); //Ajout d'un séparateur (trait dans le menu)
        
        this.comm.add(item2); // suivre commande
        item2.addActionListener(unEcouteur);
        this.comp.add(item2bis);
        item2bis.addActionListener(unEcouteur);
        
        this.comm.addSeparator();
        this.comm.add(item31); // affichage pdts
        item31.addActionListener(unEcouteur);
        this.comm.add(item32);
        item32.addActionListener(unEcouteur);
        this.comm.add(item33); 
        item33.addActionListener(unEcouteur);
        this.comm.add(item34); 
        item34.addActionListener(unEcouteur);
        
        this.reapro.add(item4); // gérer le stock
        item4.addActionListener(unEcouteur);
        
        this.livraison.addSeparator();
        this.livraison.add(item5); // déclencher livraison
        item5.addActionListener(unEcouteur);
        
        this.autre.add(item7); // info
        item7.addActionListener(unEcouteur);
        this.autre.addSeparator();
        this.autre.add(item6); // fermer
        item6.addActionListener(unEcouteur);

        this.menuBar.add(comm);
        this.menuBar.add(comp);
        this.menuBar.add(reapro);
        this.menuBar.add(livraison);
        this.menuBar.add(autre);
        this.setJMenuBar(menuBar);
        this.setTitle("Projet Ecommerce");
        this.setVisible(true);
    }
  
    private JPanel concepteur(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("<html> Application realisée par : <br><br> - BLONDEAU Laura <br>- MERSCH Amélien <br> - POCHON Amaury <br><br><br> Dans le cadre du projet J2EE (M1 MIAGE) proposé par : <br><br> TORGUET Patrice et TEISSIER Cédric</html>");
        panel.add(label);
        return panel;
    }
    
    private JPanel affichageProduits() throws NamingException{
        
        JPanel panel = new JPanel();
        
        GridLayout tableau = new GridLayout(10,3);
        //panel.setLayout(new FlowLayout());
        panel.setLayout(tableau);
        
        // Connection 
        System.setProperty("java.naming.factory.initial",
        "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost",
        "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort",
        "3700");
        InitialContext context = new InitialContext();
        
        ServiceCommercialRemote souche1 = (ServiceCommercialRemote) context.lookup("services.ServiceCommercialRemote");
        System.out.println("Retour méthode List<String> lister()");
        List<String> list = souche1.lister();

        for(String a : list) {
            System.out.println(a);
            JLabel JL = new JLabel();
            JL.setText(a);
            panel.add(JL);
        }
        
        //produitList = new JList();
        //produitList.setVisibleRowCount(2);
        //JScrollPane scrollProduit = new JScrollPane(produitList);
        
        
        return panel;
    }
    
     private JPanel creerArticle() throws NamingException{
        
        JButton valider = new JButton ("Valider");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        String id,lib,des,px,tx,stk;
        
        JTextField articleID = new JTextField("999");
        articleID.setPreferredSize(new Dimension(150, 30));
        JTextField articleLib = new JTextField("Libellé");
        articleLib.setPreferredSize(new Dimension(150, 30));
        JTextField articleDes = new JTextField("Description");
        articleDes.setPreferredSize(new Dimension(250, 30));
        JTextField articlePrix = new JTextField("99.99");
        articlePrix.setPreferredSize(new Dimension(150, 30));
        JTextField articleTaux = new JTextField("0.2");
        articleTaux.setPreferredSize(new Dimension(150, 30));
        JTextField articleStock = new JTextField("0");
        articleStock.setPreferredSize(new Dimension(150, 30));
        
        JLabel JLtitre = new JLabel("CREATION D'UN NOUVEL ARTICLE");
        JLabel JLid = new JLabel("Reférence : ");
        JLabel JLlib = new JLabel("Libellé : ");
        JLabel JLdes = new JLabel("Description : ");
        JLabel JLprix = new JLabel("Prix Hors Taxe : ");
        JLabel JLtaux = new JLabel("Taux TVA : ");
        JLabel JLstock = new JLabel("Stock : ");
        
        panel.add(JLtitre);
        panel.add(JLid);
        panel.add(articleID);
        panel.add(JLlib);
        panel.add(articleLib);
        panel.add(JLdes);
        panel.add(articleDes);
        panel.add(JLprix);
        panel.add(articlePrix);
        panel.add(JLtaux);
        panel.add(articleTaux);
        panel.add(JLstock);
        panel.add(articleStock);
        panel.add(valider);
        
        id=articleID.getText();
        lib=articleLib.getText();
        des=articleDes.getText();
        px=articlePrix.getText();
        tx=articleTaux.getText();
        stk=articleStock.getText();
        
        System.setProperty("java.naming.factory.initial",
        "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost",
        "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort",
        "3700");
        InitialContext context = new InitialContext();
        
        ServiceCommercialRemote souche = (ServiceCommercialRemote) context.lookup("services.ServiceCommercialRemote");
        
        
        String art = id+","+lib+","+des+","+px+","+tx+","+stk;
        System.out.println(art+"PROBLEME");
        
        try {
        
        valider.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArticleBis a = souche.creer(art);
            }
        }
        );
        } catch(Exception e) {
            System.out.println("PROBLEME");
        }
        
        return panel;
    }
    
    public static void main(String[] args){
        Fenetre f = new Fenetre();
    }
}