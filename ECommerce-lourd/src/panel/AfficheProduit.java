/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import entitiesBis.ArticleBis;
import fenetre.Fenetre;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import utilities.TabModel;

/**
 *
 * @author Amaury_PC
 */

public class AfficheProduit extends JPanel {

    Fenetre maFenetre;
    App app;
    private JTable jTarticle;
    private JButton boutonModifier = new JButton("Modifier");
    private JButton boutonSupprimer = new JButton("Supprimer");
    private JScrollPane scrollPane;
    private TabModel modelArticle;
    private String[] titreColonnes = {"ID", "Libellé", "Description", "Prix Hors taxe", "TVA", "Stock"};
    private ModifierProduit modifierProduit;
    
    private JPanel panelList = new JPanel(new BorderLayout());
    private JPanel panelBtn = new JPanel(new FlowLayout());
    
    
    public AfficheProduit(Fenetre maFenetre, App app) {
        this.maFenetre = maFenetre;
        this.app = app;

        this.init();
        
        this.createList();
    }
    
    private void init(){
        this.setLayout(new GridLayout(2, 1));
        this.add(panelList, BorderLayout.CENTER);
        this.add(panelBtn);

        // Ajout du JButton "boutonModifier"
        this.panelBtn.add(boutonModifier);
        
        // Ecoute du Jbutton "boutonModifier"
        boutonModifier.addActionListener(new ActionListener() {
            AfficheProduit afficheProd;
            public void actionPerformed(ActionEvent e) {
                try {
                    // On se place à la colonne 0 pour être directement sur l'ID
                    int col = 0;
                    int row = jTarticle.getSelectedRow();

                    // On récupére l'ID de la ligne sélectionnée
                    Object cellule = jTarticle.getValueAt(row,col);

                    // test perso pour vérifier si on récupère bien l'artibleBis
                    String i = cellule.toString();
                    Integer idArticle = Integer.parseInt(i);

                    // Ouverture d'une nouvelle Frame pour modifier le produit
                    afficheProd.modifierProduit = new ModifierProduit(maFenetre, app, idArticle, afficheProd);

                    // On désactive les JButton modifier et supprimer
                    desactiverBtnModif();
                    desactiverBtnSupprimer();
                } catch (ArrayIndexOutOfBoundsException exc) {
                    // Si on selectionne aucune ligne du tableau pour modifier un produit, on gère l'erreur
                }
            }
            
            private ActionListener init(AfficheProduit p){
                this.afficheProd = p;
                return this;
            }
        }.init(this));
        
        
        // Ajout du JButton "boutonSupprimer"
         this.panelBtn.add(boutonSupprimer);
        
        // Ecoute du Jbutton "boutonSupprimer"
        boutonSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // On se place à la colonne 0 pour être directement sur l'ID
                    int col = 0;
                    int row = jTarticle.getSelectedRow();

                    // On récupére l'ID de la ligne sélectionnée
                    Object cellule = jTarticle.getValueAt(row,col);
                    String id = cellule.toString();
                    supprimer(id);
                } catch (ArrayIndexOutOfBoundsException exc) {
                    // Si on selectionne aucune ligne du tableau pour supprimer un produit, on gère l'erreur
                }
            }
        });
    }
    
    private void supprimer(String id){
        // Boite de dialogue pour demander la confirmation de la suppression			
        int option = JOptionPane.showConfirmDialog(
                null, 
                "Voulez-vous vraiment supprimer le produit "+id+" ?", 
                "Suppression d'un produit", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE
        );

        // Si la réponse est Oui
        if(option == JOptionPane.OK_OPTION){
            app.getServiceCommercial().supprimer(id);
            JOptionPane.showMessageDialog(null, "Le produit a bien été supprimé", "Validation de votre suppresion", JOptionPane.INFORMATION_MESSAGE);
            jTarticle.clearSelection();
            actualiser();
        }
    }
    
    private void createList(){
        
        // On récupère la liste des article
        List<ArticleBis> list = this.app.getServiceCommercial().listerBis();

        // Initialisation de la taille
        Object[][] donneeArticle = new Object[list.size()][6];
        int index = 0;

        // Création de la JTable
        for (ArticleBis articleBis : list) {
            donneeArticle[index][0] = articleBis.getIdBis();
            donneeArticle[index][1] = articleBis.getLibBis();
            donneeArticle[index][2] = articleBis.getDescriptionBis();
            donneeArticle[index][3] = articleBis.getPrixHtBis();
            donneeArticle[index][4] = articleBis.getTauxTvaBis();
            donneeArticle[index][5] = articleBis.getStockBis();
            index++;
        }

        this.modelArticle = new TabModel(donneeArticle, titreColonnes);
        jTarticle = new JTable(modelArticle);
        
        // Scroll possible s'il y a beaucoup d'article d'affiché
        this.scrollPane = new JScrollPane(jTarticle); 
        this.panelList.add(scrollPane);
    }
    
    public void activerBtnModif(){
        boutonModifier.setEnabled(true);
    }
    
    public void desactiverBtnModif(){
        boutonModifier.setEnabled(false);
    }
    
    public void activerBtnSupprimer(){
        boutonSupprimer.setEnabled(true);
    }
    
    public void desactiverBtnSupprimer(){
        boutonSupprimer.setEnabled(false);
    }
    
    
    public void actualiser(){
        // On vide le JPanel
        this.panelList.removeAll();
        
        //On re-charge la JListe
        this.createList();
        
        // On valide et rafraichit le panleList
        this.panelList.revalidate();
        this.panelList.repaint();
    }

}
