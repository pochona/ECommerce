/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import fenetre.Fenetre;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Amélien
 */

public class ModifierStock extends JFrame{

    private JButton annuler, valider;
    private JPanel panel;
    private Integer idArticle;
    private App app;
    private Fenetre fen;
    private GererApprovisionnement panelParent;
    private JTextField jTstkNouveau, jTstkActuel;
    private Integer stkActuel;

    public ModifierStock(Fenetre fen, App app, Integer idArticle, GererApprovisionnement panelParent){
        this.fen = fen;
        this.app = app;
        this.idArticle = idArticle;
        this.panelParent = panelParent;
        this.setSize(500, 250);
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Modification sur le produit " + idArticle);
        this.setLocationRelativeTo(null);
        this.panel = new JPanel();
        this.initPanel();
        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    public void initPanel(){

        Integer stockArticle = app.getServiceApprovisionnement().findStock(idArticle);
        // Conversion en string du stock pour l'afficher dans le JTextField
        String stockActuel = String.valueOf(stockArticle);
        stkActuel = Integer.valueOf(stockActuel);
        // Déclaration du JTextField
        this.jTstkActuel = new JTextField(stockActuel);
        this.jTstkActuel.setPreferredSize(new Dimension(150, 30));
        this.jTstkActuel.setEditable(false);
        this.jTstkNouveau = new JTextField("");
        this.jTstkNouveau.setPreferredSize(new Dimension(150, 30));

        // Déclaration des JLabel
        JLabel jLstkActuel = new JLabel("Ancien stock");
        JLabel jLstkNouveau = new JLabel("Nouveau stock");

        // Ajout au panel des JTextField et des JLabel
        panel.add(jLstkActuel);
        panel.add(jTstkActuel);
        panel.add(jLstkNouveau);
        panel.add(jTstkNouveau);

        // Jbutton "valider" et action associée
        valider = new JButton("Valider");
        panel.add(valider);
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionValider();
            }
        });

        // Jbutton "fermer" et action associée
        annuler = new JButton("Fermer");
        panel.add(annuler);
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelParent.activerBtnModif();
                dispose();
            }
        });
    }
    
    public void actionValider(){
        try {
            String stockString = this.jTstkNouveau.getText();
            // Conversion
            int stockInt = Integer.parseInt(stockString);

            // Création d'un nouvel articleBis pour l'éditer si stock entré > 0
            if (stockInt > stkActuel){
                app.getServiceApprovisionnement().editerStock(idArticle, stockInt);
                
                // On ferme la JFrame après la création
                panelParent.actualiser();
                panelParent.activerBtnModif();
                dispose();

                // Boite de dialogue indiquant le succès de la modification
                JOptionPane.showMessageDialog(null, "Modification du stock du produit "+idArticle, "Succés", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "Un réapprovisionnement implique avoir plus de stock que l'actuel ! ", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException exc) {
            // En cas d'erreur(s) sur les champs insérés, boite de dialogue d'erreur
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les types renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
