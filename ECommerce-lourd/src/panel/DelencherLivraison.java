/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import entitiesBis.ArticleBis;
import entitiesBis.CommandeBis;
import fenetre.Fenetre;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
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
public class DelencherLivraison extends JPanel {

    Fenetre maFenetre;
    App app;
    // JTable des commandes
    private JTable JTCommande;
    // Liste d'id des commandes selectionnées 
    private ArrayList<Integer> commandesSelect;
    // TabModel d
    private TabModel tabSelected;
    private JButton boutonLivraison;
    
    private JPanel panelBtn;
    
    private JScrollPane scrollPaneA;
    
    
    public DelencherLivraison(Fenetre maFenetre, App app) {
        this.maFenetre = maFenetre;
        this.app = app;
        
        this.creerLayout();
        
        this.listerCommande();
    }
    
    public void creerLayout(){
        // Layout principal avec le tableau et les btn
        GridLayout tableau = new GridLayout(2,1);
        this.setLayout(tableau);
        
        // Ajout du scrollPane
        this.scrollPaneA = new JScrollPane();

        this.add(scrollPaneA);
        
        
        this.boutonLivraison = new JButton("Valider livraison");        
        // Ajout des boutons
        this.panelBtn = new JPanel();
        //btnPanel.setLayout(new ());
        
        panelBtn.add(boutonLivraison);
        
        this.add(panelBtn);
    }
    
    public void listerCommande(){
        String[] titreColonnes = {"ID", "Date", "Disponibilité articles", "Selectionner"};

        List<CommandeBis> list = this.app.getServiceCommercial().listerCommandeBis();

        // Initialisation de la taille
        Object[][] donneeCommande = new Object[list.size()][6];
        int index = 0;

        for (CommandeBis maCommande : list) {
            System.out.println(maCommande.getIdBis());
            donneeCommande[index][0] = maCommande.getIdBis();
            donneeCommande[index][1] = maCommande.getDateCommandeBis();
            donneeCommande[index][2] = "Article Dispo";
            donneeCommande[index][3] = "[ ]";
            index++;
        }

        TabModel tabModelCommande = new TabModel(donneeCommande, titreColonnes);
        JTCommande = new JTable(tabModelCommande);
        
        this.scrollPaneA.add(JTCommande);
        this.scrollPaneA.repaint();
       

        JTCommande.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            private DelencherLivraison panel;

            public void valueChanged(ListSelectionEvent arg0) {
                //panel.JTarticle.clearSelection();
                //panel.setTabSelected(modelArticle);
            }

            private ListSelectionListener init(DelencherLivraison panel) {
                panel = panel;
                return this;
            }
        }.init(this));        
    }


    public void setTabSelected(TabModel tm) {
        this.tabSelected = tm;
    }
    
    public TabModel getTabSelected() {
        return this.tabSelected;
    }
}
