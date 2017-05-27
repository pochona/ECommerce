/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import exceptions.ExceptionArticle;
import fenetre.Fenetre;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Amaury_PC
 */

public class CreerArticle extends JPanel {

    Fenetre maFenetre;
    App app;

    public CreerArticle(Fenetre maFenetre, App a) {

        this.maFenetre = maFenetre;
        this.app = a;
        
        JButton valider = new JButton("Valider");
        
        this.setLayout(new FlowLayout());

        // Déclaration des JTextField et de leurs dimensions
        JTextField articleLib = new JTextField("Libellé");
        articleLib.setPreferredSize(new Dimension(1080, 30));
        JTextField articleDes = new JTextField("Description");
        articleDes.setPreferredSize(new Dimension(1080, 30));
        JTextField articlePrix = new JTextField("99.99");
        articlePrix.setPreferredSize(new Dimension(100, 30));
        JTextField articleTaux = new JTextField("0.2");
        articleTaux.setPreferredSize(new Dimension(100, 30));
        JTextField articleStock = new JTextField("0");
        articleStock.setPreferredSize(new Dimension(100, 30));
        
        // Déclaration des JLabel
        JLabel JLlib = new JLabel("   Libellé       : ");
        JLabel JLdes = new JLabel("Description : ");
        JLabel JLprix = new JLabel("Prix Hors Taxe : ");
        JLabel JLtaux = new JLabel("Taux TVA : ");
        JLabel JLstock = new JLabel("Stock : ");

        // Ajout au panel des JTextField et des JLabel
        this.add(JLlib);
        this.add(articleLib);
        this.add(JLdes);
        this.add(articleDes);
        this.add(JLprix);
        this.add(articlePrix);
        this.add(JLtaux);
        this.add(articleTaux);
        this.add(JLstock);
        this.add(articleStock);
        this.add(valider);

        // Ecoute du bouton valider
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Déclaration
                    String lib, des, px, tx, stk;
                    double prixHt2;
                    float tauxTva2;
                    int stock2;
                    
                    // Numéro ID par défaut pour construire un article, mais il est en auto increment
                    Integer id = 1;
                    
                    // Récupération des valeurs saisies dans les JTextField
                    lib = articleLib.getText();
                    des = articleDes.getText();
                    px = articlePrix.getText();
                    tx = articleTaux.getText();
                    stk = articleStock.getText();
                    
                    // Contruction de la String qui sera envoyée pour la création de l'article
                    String art = id + "," + lib + "," + des + "," + px + "," + tx + "," + stk;
                    
                    // Test des conversions des types
                    prixHt2 = Double.parseDouble(px);
                    tauxTva2 = Float.parseFloat(tx);
                    stock2 = Integer.parseInt(stk);
                    
                    // Création d'un nouvel article
                    app.getServiceCommercial().creer(art);
                    
                    // Boite de dialogue indiquant la bonne création d'article
                    JOptionPane infomationCreation = new JOptionPane();
                    infomationCreation.showMessageDialog(null, "Le produit a bien été créé", "Validation de votre création", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException exc) {
                    // En cas d'erreurs sur les types mis dans les champs
                    JOptionPane JOP;
                    JOP = new JOptionPane();
                    JOP.showMessageDialog(null, "Veuillez vérifier les types renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (ExceptionArticle ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        );
    }
}