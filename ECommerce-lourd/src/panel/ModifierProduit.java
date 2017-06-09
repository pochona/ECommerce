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

public class ModifierProduit extends JFrame{
    
        private JButton fermer, valider;
        private Integer idArticle;
        private App app;
        private Fenetre fen;
        private JPanel panel;
        private AfficheProduit panelParent;
        private JTextField jTlib, jTdes, jTpx, jTtva, jTstk;
        private ArticleBis artBis;
        
    
    public ModifierProduit(Fenetre fen, App app, Integer idArticle, AfficheProduit panelParent){
        this.fen = fen;
        this.app = app;
        this.idArticle = idArticle;
        this.panelParent = panelParent;
	this.setSize(500, 250);
        this.setTitle("Modification sur le produit "+idArticle);
        this.setLocationRelativeTo(null);
        this.panel = new JPanel();
        this.initPanel();
        this.getContentPane().add(this.panel);
        this.setVisible(true);
    }
    
    private void initPanel(){
        
        this.artBis = app.getServiceCommercial().retourArticle(idArticle);
        
        // Conversion en string de la TVA et du Prix pour les afficher dans les JTextField
        String tva = String.valueOf(artBis.getTauxTvaBis());
        String px = String.valueOf(artBis.getPrixHtBis());
        String stk = String.valueOf(artBis.getStockBis());
        
        // Déclaration des JTextField
        jTlib = new JTextField(artBis.getLibBis());
        jTlib.setPreferredSize(new Dimension(400, 30));
        jTdes = new JTextField(artBis.getDescriptionBis());
        jTdes.setPreferredSize(new Dimension(400, 30));
        jTpx = new JTextField(px);
        jTpx.setPreferredSize(new Dimension(150, 30));
        jTtva = new JTextField(tva);
        jTtva.setPreferredSize(new Dimension(150, 30));
        jTstk = new JTextField(stk);
        jTstk.setPreferredSize(new Dimension(150, 30));
                
        // Déclaration des JLabel
        JLabel jLlib = new JLabel("Libellé");
        JLabel jLdes = new JLabel("Description");
        JLabel jLpx = new JLabel("Prix Hors Taxe");
        JLabel jLtva = new JLabel("Taux TVA");
        JLabel jLstk = new JLabel("Stock actuel");
        
        // Ajout au panel des JTextField et des JLabel
        panel.add(jLlib);
        panel.add(jTlib);
        panel.add(jLdes);
        panel.add(jTdes);
        panel.add(jLpx);
        panel.add(jTpx);
        panel.add(jLtva);
        panel.add(jTtva);
        panel.add(jLstk);
        panel.add(jTstk);
        
        // Jbutton "valider" et action associée
        valider = new JButton("Valider");
        panel.add(valider);
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionValider();
            }
        });
        
        // Jbutton "fermer" et action associée
        fermer = new JButton("Annuler");
        panel.add(fermer);
        fermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionAnnuler();
            }
        });
        this.add(panel);
    }
    
    private void actionAnnuler(){
        panelParent.activerBtnModif();
        panelParent.activerBtnSupprimer();
        dispose();
    }
    
    private void actionValider(){
         try {
            // Récupération des champs saisis pour la modif
            Integer id = artBis.getIdBis(); // Ici, on récupère l'id de l'article mais qui n'est pas saisi
            String lib = jTlib.getText();
            String des = jTdes.getText();
            String px = jTpx.getText();
            String tx = jTtva.getText();
            String stk = jTstk.getText();
            // Conversion
            double prixHt2 = Double.parseDouble(px);
            float tauxTva2 = Float.parseFloat(tx);
            int stock2 = Integer.parseInt(stk);
            if (prixHt2<00.00){
                JOptionPane.showMessageDialog(null, "Un article n'est pas gratuit ! Le prix doit est supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if ((tauxTva2<0.00) || (tauxTva2>1.00)) {
                JOptionPane.showMessageDialog(null, "La TVA doit être comprise entre 0.00 et 1.00","Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (stock2<0) {
                JOptionPane.showMessageDialog(null, "Le stock doit être égale ou supérieur à 0","Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                // Création d'un nouvel articleBis pour l'éditer
                ArticleBis abis = new ArticleBis(id, lib, des, prixHt2, tauxTva2, stock2);
                app.getServiceCommercial().editer(abis);

                // On ferme la JFrame après la création
                panelParent.actualiser();
                panelParent.activerBtnModif();
                panelParent.activerBtnSupprimer();
                dispose(); 
                // Boite de dialogue indiquant le succès de la modification
                JOptionPane.showMessageDialog(null, "Modification prise en compte", "Succés", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException exc) {

            // En cas d'erreur(s) sur les champs insérés, boite de dialogue d'erreur
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les types renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
