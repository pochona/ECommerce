/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import entitiesBis.ArticleBis;
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
    private JPanel JP = new JPanel();
    private Integer idArticle;
    private App app;
    private Fenetre fen;
    private GererApprovisionnement panelParent;

    public ModifierStock(Fenetre fen, App app, Integer idArticle, GererApprovisionnement panelParent){
        this.fen = fen;
        this.app = app;
        this.idArticle = idArticle;
        this.panelParent = panelParent;
        this.setSize(500, 250);
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Modification sur le produit " + idArticle);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(init());
        this.setVisible(true);
    }

    public JPanel init(){

        Integer stockArticle = app.getServiceApprovisionnement().findStock(idArticle);
        // Conversion en string du stock pour l'afficher dans le JTextField
        String stkActuel = String.valueOf(stockArticle);

        // Déclaration du JTextField
        JTextField JTstkActuel = new JTextField(stkActuel);
        JTstkActuel.setPreferredSize(new Dimension(150, 30));
        JTextField JTstkNouveau = new JTextField("");
        JTstkNouveau.setPreferredSize(new Dimension(150, 30));

        // Déclaration des JLabel
        JLabel JLstkActuel = new JLabel("Ancien stock");
        JLabel JLstkNouveau = new JLabel("Nouveau stock");

        // Ajout au panel des JTextField et des JLabel
        JP.add(JLstkActuel);
        JP.add(JLstkNouveau);

        // Jbutton "valider" et action associée
        valider = new JButton("Valider");
        JP.add(valider);
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Déclaration
                    String stockString;
                    int stockInt;

                    stockString = JTstkNouveau.getText();
                    // Conversion
                    stockInt = Integer.parseInt(stockString);

                    // Création d'un nouvel articleBis pour l'éditer
                    app.getServiceApprovisionnement().editerStock(idArticle, stockInt);

                    // On ferme la JFrame après la création
                    panelParent.actualiser();
                    panelParent.activerBtnModif();
                    dispose();

                    // Boite de dialogue indiquant le succès de la modification
                    JOptionPane.showMessageDialog(null, "Modification prise en compte", "Succés", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException exc) {

                    // En cas d'erreur(s) sur les champs insérés, boite de dialogue d'erreur
                    JOptionPane.showMessageDialog(null, "Veuillez vérifier les types renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        );

        // Jbutton "fermer" et action associée
        annuler = new JButton("Fermer");
        JP.add(annuler);
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(JP);
        return JP;
    }
}
