/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import entitiesBis.ArticleBis;
import fenetre.Fenetre;
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
    private JTable JTarticle;
    private TabModel tabSelected;
    private JButton boutonModifier = new JButton("Modifier");
    private JButton boutonSupprimer = new JButton("Supprimer");
    private JScrollPane scrollPane;
    private TabModel modelArticle;
    
    
    public AfficheProduit(Fenetre maFenetre, App app) {
        this.maFenetre = maFenetre;
        this.app = app;

        GridLayout tableau = new GridLayout(2, 1);
        this.setLayout(tableau);
    
        String[] titreColonnes = {"ID", "Libellé", "Description", "Prix Hors taxe", "TVA", "Stock"};

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
        JTarticle = new JTable(modelArticle);
        
        // Scroll possible s'il y a beaucoup d'article d'affiché
        this.scrollPane = new JScrollPane(JTarticle); 
        this.add(scrollPane);
    
        // JPanel pour les boutons
        JPanel btnPanel = new JPanel();
        
        // Ajout du JButton "boutonModifier"
        btnPanel.add(boutonModifier);
        
        // Ecoute du Jbutton "boutonModifier"
        boutonModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // On se place à la colonne 0 pour être directement sur l'ID
                int col = 0;
                int row = JTarticle.getSelectedRow();
                
                // On récupére l'ID de la ligne sélectionnée
                Object cellule = JTarticle.getValueAt(row,col);
                
                // test perso pour vérifier si on récupère bien l'artibleBis
                String i = cellule.toString();
                Integer id = Integer.parseInt(i);
                ArticleBis artBis = app.getServiceCommercial().retourArticle(id);
                
                // Ouverture d'une nouvelle Frame pour modifier le produit
                ModifierProduit mp = new ModifierProduit(maFenetre, app, artBis);
                actualiser();
            }
        });
        
        // Ajout du JButton "boutonSupprimer"
        btnPanel.add(boutonSupprimer);
        
        // Ecoute du Jbutton "boutonSupprimer"
        boutonSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // On se place à la colonne 0 pour être directement sur l'ID
                int col = 0;
                int row = JTarticle.getSelectedRow();
                
                // On récupére l'ID de la ligne sélectionnée
                Object cellule = JTarticle.getValueAt(row,col);
                String id = cellule.toString();
                
                // Boite de dialogue pour demander la confirmation de la suppression
                JOptionPane validation = new JOptionPane();			
                int option = validation.showConfirmDialog(null, "Voulez-vous vraiment supprimer le produit ?", "Suppression d'un produit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                // Si la réponse est Oui
                if(option == JOptionPane.OK_OPTION){
                    app.getServiceCommercial().supprimer(id);
                    JOptionPane infomationSuppression = new JOptionPane();
                    infomationSuppression.showMessageDialog(null, "Le produit a bien été supprimé", "Validation de votre suppresion", JOptionPane.INFORMATION_MESSAGE);
                    actualiser();
                }
            }
        });
        this.add(btnPanel);
        
    }
    
    public void actualiser(){
	
        this.removeAll();
        this.scrollPane = null;
        this.tabSelected = null;
        this.JTarticle = null;
        this.modelArticle = null;

        GridLayout tableau = new GridLayout(2, 1);
        this.setLayout(tableau);
    
        String[] titreColonnes = {"ID", "Libellé", "Description", "Prix Hors taxe", "TVA", "Stock"};

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
        JTarticle = new JTable(modelArticle);
        
        // Scroll possible s'il y a beaucoup d'article d'affiché
        this.scrollPane = new JScrollPane(JTarticle); 
        this.add(scrollPane);
    
        // JPanel pour les boutons
        JPanel btnPanel = new JPanel();
        
        // Ajout du JButton "boutonModifier"
        btnPanel.add(boutonModifier);
        
        // Ecoute du Jbutton "boutonModifier"
        boutonModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // On se place à la colonne 0 pour être directement sur l'ID
                int col = 0;
                int row = JTarticle.getSelectedRow();
                
                // On récupére l'ID de la ligne sélectionnée
                Object cellule = JTarticle.getValueAt(row,col);
                
                // test perso pour vérifier si on récupère bien l'artibleBis
                String i = cellule.toString();
                Integer id = Integer.parseInt(i);
                ArticleBis artBis = app.getServiceCommercial().retourArticle(id);
                
                // Ouverture d'une nouvelle Frame pour modifier le produit
                ModifierProduit mp = new ModifierProduit(maFenetre, app, artBis); 
            }
        });
        
        // Ajout du JButton "boutonSupprimer"
        btnPanel.add(boutonSupprimer);
        
        // Ecoute du Jbutton "boutonSupprimer"
        boutonSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // On se place à la colonne 0 pour être directement sur l'ID
                int col = 0;
                int row = JTarticle.getSelectedRow();
                
                // On récupére l'ID de la ligne sélectionnée
                Object cellule = JTarticle.getValueAt(row,col);
                String id = cellule.toString();
                
                // Boite de dialogue pour demander la confirmation de la suppression
                JOptionPane validation = new JOptionPane();			
                int option = validation.showConfirmDialog(null, "Voulez-vous vraiment supprimer le produit ?", "Suppression d'un produit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                // Si la réponse est Oui
                if(option == JOptionPane.OK_OPTION){
                    app.getServiceCommercial().supprimer(id);
                    JOptionPane infomationSuppression = new JOptionPane();
                    infomationSuppression.showMessageDialog(null, "Le produit a bien été supprimé", "Validation de votre suppresion", JOptionPane.INFORMATION_MESSAGE);
                    // Mise à jour non visible directement
                }
            }
        });
        this.add(btnPanel);
        this.revalidate();
    }

    public JTable getJTarticle() {
        return JTarticle;
    }

    public void setTabSelected(TabModel tm) {
        this.tabSelected = tm;
    }
    
    public TabModel getTabSelected() {
        return this.tabSelected;
    }
    
    public int getLine(){
        return this.getJTarticle().getSelectedRow();
    }
}
