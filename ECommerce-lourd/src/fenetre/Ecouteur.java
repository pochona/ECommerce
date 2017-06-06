/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import app.App;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import panel.AfficheProduit;
import panel.CreerArticle;
import panel.DelencherLivraison;
import panel.DelencherLivraison2;
import panel.GererApprovisionnement;
import panel.GererCommande;
import panel.GererPrelevements;

/**
 *
 * @author Amaury_PC
 */
public class Ecouteur implements ActionListener {
	  
    private Fenetre fenetre;
    private App app;
    
    public Ecouteur(Fenetre f, App a){
        this.fenetre = f;
        this.app = a;
    }
// gestion avec le nom du bouton actuellement : à voir si on modifie en classe anonyme ou si on fait un truc plus élégant pour les detections d'actions
    public void actionPerformed(ActionEvent act){
        String source = act.getActionCommand();
        if (source.equals("Suivre commande")){ // Information sur l'application
            this.fenetre.setContentPane(new GererCommande(this.fenetre, this.app));
        } else if (source.equals("Gérer les prélèvements")){ // Fermer
            this.fenetre.setContentPane(new GererPrelevements(this.fenetre, this.app));
        } else if (source.equals("Créer un produit")){
            this.fenetre.setContentPane(new CreerArticle(this.fenetre, this.app));
        } else if (source.equals("Gérer tous les produits")){ 
            this.fenetre.setContentPane(new AfficheProduit(this.fenetre, this.app));
        } else if (source.equals("Gérer le stock")){
            this.fenetre.setContentPane(new GererApprovisionnement(this.fenetre, this.app));
        } else if (source.equals("Déclencher la livraison")){
            this.fenetre.setContentPane(new DelencherLivraison(this.fenetre, this.app));
        } else if (source.equals("Fermer")){
            System.exit(0);
        } else if (source.equals("Information")){
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JLabel label = new JLabel("<html> Application realisée par : <br><br> - Laura BLONDEAU<br>- Amélien MERSCH<br> - Amaury POCHON<br><br><br> Dans le cadre du projet J2EE du M1 MIAGE</html>");
            panel.add(label);
            this.fenetre.setContentPane(panel);
        } 
        this.fenetre.validate(); // maj des conteneurs
    }
}
