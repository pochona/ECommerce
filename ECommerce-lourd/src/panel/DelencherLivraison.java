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
    private TabModel tabModelCommande;
    private TabModel tabModelSelect;
    private JButton boutonLivraison;
    
    private JPanel panelBtn;
    private JPanel panelList;
    private JPanel panelListAll;
    private JPanel panelListSelect;
    private Object[][] donneeCommande = new Object[0][0];
    private JScrollPane scrollPaneCommande;
    private JScrollPane scrollPaneSelect;
    
    private String[] titre = {"ID", "Date", "Disponibilité articles", "Selectionner"};
    
    public DelencherLivraison(Fenetre maFenetre, App app) {
        this.maFenetre = maFenetre;
        this.app = app;
        
        this.creerLayout();
        this.creerList();
        this.listerCommande();
        this.actualiserList();
    }
    
    private void creerLayout(){

        this.setLayout(new GridLayout(2,1));

        this.boutonLivraison = new JButton("Valider livraison");        
        // Ajout des boutons
        this.panelBtn = new JPanel();
        this.panelList = new JPanel();
        this.panelList.setLayout(new GridLayout(1, 2));
        
        this.panelListAll = new JPanel();
        this.panelListSelect = new JPanel();
        this.panelList.add(panelListAll);
        this.panelList.add(panelListSelect);
        
        panelBtn.add(boutonLivraison);
        this.add(panelList);
        this.add(panelBtn);
    }
    
    private void creerList(){
        this.tabModelCommande = new TabModel(this.donneeCommande, titre);
        this.tabModelSelect = new TabModel(new Object[0][0], titre);
        JTCommande = new JTable(tabModelCommande);

        this.scrollPaneCommande = new JScrollPane(JTCommande);
        this.scrollPaneSelect = new JScrollPane(new JTable());
       
        this.panelListAll.add(scrollPaneCommande);
        this.panelListSelect.add(scrollPaneSelect);
    }
    
    private void actualiserList(){
        this.panelListAll.removeAll();
        //this.scrollPaneCommande.repaint();
        this.panelListAll.add(scrollPaneCommande);
        this.panelListAll.revalidate();
        this.panelListAll.repaint();
        //this.panelListSelect.add(this.scrollPaneSelect);
       
    }
    
    private void listerCommande(){

        List<CommandeBis> list = this.app.getServiceCommercial().listerCommandeBis();

        // Initialisation de la taille
        this.donneeCommande = new Object[list.size()][6];
        int index = 0;

        for (CommandeBis maCommande : list) {
            donneeCommande[index][0] = maCommande.getIdBis();
            donneeCommande[index][1] = maCommande.getDateCommandeBis();
            donneeCommande[index][2] = "Article Dispo";
            donneeCommande[index][3] = "[ ]";
            index++;
        }
      
    }
}
