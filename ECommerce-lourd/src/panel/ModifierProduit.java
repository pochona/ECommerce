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
import java.awt.GridLayout;
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
        private JPanel JP = new JPanel();
        private Integer idArticle;
        private App app;
        private Fenetre fen;
        private AfficheProduit panelParent;
    
    public ModifierProduit(Fenetre fen, App app, Integer idArticle, AfficheProduit panelParent){
        this.fen = fen;
        this.app = app;
        this.idArticle = idArticle;
        this.panelParent = panelParent;
	this.setSize(500, 250);
        this.setTitle("Modification sur le produit "+idArticle);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(init());
        this.setVisible(true);
    }
    
    public JPanel init(){
        
        ArticleBis artBis = app.getServiceCommercial().retourArticle(idArticle);
        
        // Conversion en string de la TVA et du Prix pour les afficher dans les JTextField
        String tva = String.valueOf(artBis.getTauxTvaBis());
        String px = String.valueOf(artBis.getPrixHtBis());
        String stk = String.valueOf(artBis.getStockBis());
        
        // Déclaration des JTextField
        JTextField JTlib = new JTextField(artBis.getLibBis());
        JTlib.setPreferredSize(new Dimension(400, 30));
        JTextField JTdes = new JTextField(artBis.getDescriptionBis());
        JTdes.setPreferredSize(new Dimension(400, 30));
        JTextField JTpx = new JTextField(px);
        JTpx.setPreferredSize(new Dimension(150, 30));
        JTextField JTtva = new JTextField(tva);
        JTtva.setPreferredSize(new Dimension(150, 30));
        JTextField JTstk = new JTextField(stk);
        JTstk.setPreferredSize(new Dimension(150, 30));
                
        // Déclaration des JLabel
        JLabel JLlib = new JLabel("Libellé");
        JLabel JLdes = new JLabel("Description");
        JLabel JLpx = new JLabel("Prix Hors Taxe");
        JLabel JLtva = new JLabel("Taux TVA");
        JLabel JLstk = new JLabel("Stock actuel");
        
        // Ajout au panel des JTextField et des JLabel
        JP.add(JLlib);
        JP.add(JTlib);
        JP.add(JLdes);
        JP.add(JTdes);
        JP.add(JLpx);
        JP.add(JTpx);
        JP.add(JLtva);
        JP.add(JTtva);
        JP.add(JLstk);
        JP.add(JTstk);
        
        // Jbutton "valider" et action associée
        valider = new JButton("Valider");
        JP.add(valider);
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Déclaration
                    String lib, des, px, tx, stk;
                    Integer id;
                    double prixHt2;
                    float tauxTva2;
                    int stock2;
                    
                    // Récupération des champs saisis pour la modif
                    id = artBis.getIdBis(); // Ici, on récupère l'id de l'article mais qui n'est pas saisi
                    lib = JTlib.getText();
                    des = JTdes.getText();
                    px = JTpx.getText();
                    tx = JTtva.getText();
                    stk = JTstk.getText();
                    // Conversion
                    prixHt2 = Double.parseDouble(px);
                    tauxTva2 = Float.parseFloat(tx);
                    stock2 = Integer.parseInt(stk);
                    
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
                } catch (NumberFormatException exc) {
                    
                    // En cas d'erreur(s) sur les champs insérés, boite de dialogue d'erreur
                    JOptionPane.showMessageDialog(null, "Veuillez vérifier les types renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        );
        
        // Jbutton "fermer" et action associée
        fermer = new JButton("Annuler");
        JP.add(fermer);
        fermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelParent.activerBtnModif();
                panelParent.activerBtnSupprimer();
                dispose();
            }
        });
        this.add(JP);
        return JP;
    }
}
