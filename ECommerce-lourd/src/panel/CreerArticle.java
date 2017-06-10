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
    JTextField articleLib, articleDes, articlePrix, articleTaux, articleStock;
    JButton valider = new JButton("Valider");

    public CreerArticle(Fenetre maFenetre, App a) {

        this.maFenetre = maFenetre;
        this.app = a;
        
        this.init();
        
    }
    
    private void init(){
        // Ecoute du bouton valider
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionButton();
            }
        });
        
        this.setLayout(new FlowLayout());

        // Déclaration des JTextField et de leurs dimensions
        this.articleLib = new JTextField("Libellé");
        this.articleDes = new JTextField("Description");
        this.articlePrix = new JTextField("99.99");
        this.articleTaux = new JTextField("0.2");
        this.articleStock = new JTextField("0");
        
        
        articleLib.setPreferredSize(new Dimension(1080, 30));
        articleDes.setPreferredSize(new Dimension(1080, 30));
        articlePrix.setPreferredSize(new Dimension(100, 30));
        articleTaux.setPreferredSize(new Dimension(100, 30));
        articleStock.setPreferredSize(new Dimension(100, 30));
        
        // Ajout au panel des JTextField et des JLabel
        this.add(new JLabel("Libellé :"));
        this.add(articleLib);
        this.add(new JLabel("Description :"));
        this.add(articleDes);
        this.add(new JLabel("Prix Hors Taxe :"));
        this.add(articlePrix);
        this.add(new JLabel("Taux TVA :"));
        this.add(articleTaux);
        this.add(new JLabel("Stock :"));
        this.add(articleStock);
        
        this.add(valider);
    }
    
    private void actionButton(){
        try {
            // Numéro ID par défaut pour construire un article, mais il est en auto increment
            Integer id = 1;

            // Récupération des valeurs saisies dans les JTextField
            String lib = articleLib.getText();
            String des = articleDes.getText();
            String px = articlePrix.getText();
            String tx = articleTaux.getText();
            String stk = articleStock.getText();
            if(lib.equals("") || des.equals("") || px.equals("") || tx.equals("") || stk.equals("")){
                // a gérer avec une meilleur exception si necessaire
                throw new NumberFormatException();
            }
            // Contruction de la String qui sera envoyée pour la création de l'article
            String art = id + "," + lib + "," + des + "," + px + "," + tx + "," + stk;

            // Test des conversions des types
            double prixHt2 = Double.parseDouble(px);
            float tauxTva2 = Float.parseFloat(tx);
            int stock2 = Integer.parseInt(stk);
            if (prixHt2<00.00){
                JOptionPane.showMessageDialog(null, "Un article n'est pas gratuit ! Le prix doit est supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if ((tauxTva2<0.00) || (tauxTva2>1.00)) {
                JOptionPane.showMessageDialog(null, "La TVA doit être comprise entre 0.00 et 1.00","Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (stock2<0) {
                JOptionPane.showMessageDialog(null, "Le stock initial doit être égale ou supérieur à 0","Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                // Création d'un nouvel article
                app.getServiceCommercial().creer(art);

                // Boite de dialogue indiquant la bonne création d'article
                JOptionPane.showMessageDialog(null, "Le produit a bien été créé", "Validation de votre création", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException exc) {
            // En cas d'erreurs sur les types mis dans les champs
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les types renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (ExceptionArticle ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}