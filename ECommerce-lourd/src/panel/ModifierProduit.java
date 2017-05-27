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
        private JLabel JLlib, JLdes, JLpx, JLtva, JLstk;
        private JTextField JTlib, JTdes, JTpx, JTtva, JTstk;
        private JPanel JP = new JPanel();
        private ArticleBis a;
        private App app;
        private Fenetre fen;
    
    
    
    public ModifierProduit(Fenetre fen, App app, ArticleBis art){
        this.a = art;
        this.app = app;
        this.fen = fen;
	this.setSize(500, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Modification sur un produit");
        this.setLocationRelativeTo(null);
        this.getContentPane().add(init());
        this.setVisible(true);
    }
    
    
    
    public JPanel init(){
        // Conversion en string de la TVA et du Prix pour les afficher dans les JTextField
        String tva = String.valueOf(a.getTauxTvaBis());
        String px = String.valueOf(a.getPrixHtBis());
        String stk = String.valueOf(a.getStockBis());
        // Attribion des JTextField aux JLabel associés
        JP.add(JLlib = new JLabel("Libellé"));
        JP.add(JTlib = new JTextField(a.getLibBis()));
        JTlib.setPreferredSize(new Dimension(400, 30));
        JP.add(JLdes = new JLabel("Description"));
        JP.add(JTdes = new JTextField(a.getDescriptionBis()));
        JTdes.setPreferredSize(new Dimension(400, 30));
        JP.add(JLpx = new JLabel("Prix Hors Taxe"));
        JP.add(JTpx = new JTextField(px));
        JTpx.setPreferredSize(new Dimension(150, 30));
        JP.add(JLtva = new JLabel("Taux TVA"));
        JP.add(JTtva = new JTextField(tva));
        JTtva.setPreferredSize(new Dimension(150, 30));
        JP.add(JLstk = new JLabel("Stock actuel"));
        JP.add(JTstk = new JTextField(stk));
        JTstk.setPreferredSize(new Dimension(150, 30));
        
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
                    id = a.getIdBis(); // Ici, on récupère l'id de l'article mais qui n'est pas saisi
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
                    
                    // Boite de dialogue indiquant le succès de la modification
                    JOptionPane JOP1;
                    JOP1 = new JOptionPane();
                    JOP1.showMessageDialog(null, "Modification prise en compte", "Succés", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException exc) {
                    
                    // En cas d'erreur(s) sur les champs insérés, boite de dialogue d'erreur
                    JOptionPane JOP2;
                    JOP2 = new JOptionPane();
                    JOP2.showMessageDialog(null, "Veuillez vérifier les types renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        );
        
        // Jbutton "fermer" et action associée
        fermer = new JButton("Fermer");
        JP.add(fermer);
        fermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // PB :
                // Que l'on clique sur le bouton fermer classique (la croix) de la frame
                // Ou que l'on clique sur le JButton "fermer"
                // On quitte toute l'appli
                System.exit(0); 
            }
        });
        this.add(JP);
        return JP;
    }
}
