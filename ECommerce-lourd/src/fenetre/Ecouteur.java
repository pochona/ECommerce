/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import app.App;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panel.AfficheProduit;
import panel.CreerArticle;

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
    /*private JMenuItem itemSuivreCommande = new JMenuItem("Suivre commande");
    private JMenuItem itemGererPrlv = new JMenuItem("Gérer les prélèvements");
    private JMenuItem item2bis = new JMenuItem("Gérer les prélèvements");
    private JMenuItem itemModifProduit = new JMenuItem("Modifier un produit");
    private JMenuItem itemCreerProduit = new JMenuItem("Créer un produit");
    private JMenuItem itemSuppProduit = new JMenuItem("Supprimer un produit");
    private JMenuItem itemAfficheProduit = new JMenuItem("Afficher tous les produits");
    private JMenuItem itemGererStock = new JMenuItem("Gérer le stock");
    private JMenuItem itemDeclencherLivraison = new JMenuItem("Déclencher la livraison");
    private JMenuItem itemFermer = new JMenuItem("Fermer");
    private JMenuItem itemInfo = new JMenuItem("Information");*/
        String source = act.getActionCommand();
        if (source.equals("Suivre commande")){ // Information sur l'application
            //setContentPane(concepteur(this.fen));
        } else if (source.equals("Gérer les prélèvements")){ // Fermer
            //this.fenetre.setContentPane(new AfficheProduit(this.fenetre, this.app));
        } else if (source.equals("Modifier un produit")){ 
            //this.fenetre.setContentPane(new AfficheProduit(this.fenetre, this.app)); 
        } else if (source.equals("Créer un produit")){
            this.fenetre.setContentPane(new CreerArticle(this.fenetre, this.app));
        } else if (source.equals("Supprimer un produit")){ // Fermer
            System.exit(0);
        } else if (source.equals("Afficher tous les produits")){ 
            //this.fenetre.setContentPane(new CreerArticle(this.fenenetre, this.app));
        } else if (source.equals("Gérer le stock")){
            //this.fenetre.setContentPane(new AfficheProduit(this.fenetre, this.app));
        } else if (source.equals("Déclencher la livraison")){
            //this.fenetre.setContentPane(new CreerArticle(this.fenenetre, this.app));
        } else if (source.equals("Fermer")){
            System.exit(0);
        } else if (source.equals("Information")){
            //this.fenetre.setContentPane(new CreerArticle(this.fenenetre, this.app));
        }
        this.fenetre.validate(); // maj des conteneurs
    }
}
