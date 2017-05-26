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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

        this.scrollPane = new JScrollPane(JTarticle);

        this.add(scrollPane);

        // Ajout des boutons
        JPanel btnPanel = new JPanel();
        //btnPanel.setLayout(new ());

        
        btnPanel.add(boutonModifier);
        
        boutonModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int col = 0;
                int row = JTarticle.getSelectedRow(); // On envoie la ligne 
                Object cellule = JTarticle.getValueAt(row,col);
                System.out.println("Id selected : " + cellule);
                //ActionProduit ap = new ActionProduit(cellule);
            }
        });
        btnPanel.add(boutonSupprimer);
        
        boutonSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int col = 0;
                int row = JTarticle.getSelectedRow(); // On envoie la ligne 
                Object cellule = JTarticle.getValueAt(row,col);
                String id = cellule.toString();
                System.out.println("Id selected : " + cellule.toString());
                JOptionPane validation = new JOptionPane();			
                int option = validation.showConfirmDialog(null, "Voulez-vous vraiment supprimer le produit ?", "Suppression d'un produit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(option == JOptionPane.OK_OPTION){
                    app.getServiceCommercial().supprimer(id);
                    JOptionPane infomationSuppression = new JOptionPane();
                    infomationSuppression.showMessageDialog(null, "Le produit a bien été supprimé", "Validation de votre suppresion", JOptionPane.INFORMATION_MESSAGE);
                    //actualiser();
                }
            }
        });
        this.add(btnPanel);
    }
    
    public void actualiser(){
	
        maFenetre.removeAll();
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

        this.scrollPane = new JScrollPane(JTarticle);

        this.add(scrollPane);

        // Ajout des boutons
        JPanel btnPanel = new JPanel();
        //btnPanel.setLayout(new ());

        
        btnPanel.add(boutonModifier);
        
        boutonModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int col = 0;
                int row = JTarticle.getSelectedRow(); // On envoie la ligne 
                Object cellule = JTarticle.getValueAt(row,col);
                System.out.println("Id selected : " + cellule);
                //ActionProduit ap = new ActionProduit(cellule);
            }
        });
        btnPanel.add(boutonSupprimer);
        
        boutonSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int col = 0;
                int row = JTarticle.getSelectedRow(); // On envoie la ligne 
                Object cellule = JTarticle.getValueAt(row,col);
                String id = cellule.toString();
                System.out.println("Id selected : " + cellule.toString());
                JOptionPane validation = new JOptionPane();			
                int option = validation.showConfirmDialog(null, "Voulez-vous vraiment supprimer le produit ?", "Suppression d'un produit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(option == JOptionPane.OK_OPTION){
                    app.getServiceCommercial().supprimer(id);
                    JOptionPane infomationSuppression = new JOptionPane();
                    infomationSuppression.showMessageDialog(null, "Le produit a bien été supprimé", "Validation de votre suppresion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        this.add(btnPanel);
        
        this.maFenetre.revalidate();
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
