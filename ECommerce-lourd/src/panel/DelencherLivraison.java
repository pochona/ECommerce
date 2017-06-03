/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import entitiesBis.CommandeBis;
import fenetre.Fenetre;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
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
    public JTable JTCommande;
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
        //this.actualiserList();
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
        this.scrollPaneSelect = new JScrollPane(new JTable(tabModelSelect));
       
        //this.panelListAll.add(scrollPaneCommande);
        //this.panelListSelect.add(scrollPaneSelect);
    }
    
    private void actualiserList(){
        System.out.println("panel actu");
        JTCommande = new JTable(tabModelCommande);
        this.scrollPaneCommande.removeAll();
        this.scrollPaneCommande = new JScrollPane(JTCommande);
        this.panelListAll.removeAll();
        this.scrollPaneCommande.revalidate();
        this.scrollPaneCommande.repaint();
        this.panelListAll.add(scrollPaneCommande);
        this.panelListAll.revalidate();
        this.panelListAll.repaint();
        
        this.panelListSelect.removeAll();
        this.panelListSelect.add(scrollPaneSelect);
        this.panelListSelect.revalidate();
        this.panelListSelect.repaint();
        //this.panelListSelect.add(this.scrollPaneSelect);
       
    }
    
    private void listerCommande(){

        List<CommandeBis> list = this.app.getServiceCommercial().findCommandesByStatut("2");

        // Initialisation de la taille
        this.donneeCommande = new Object[list.size()][6];
        int index = 0;

        for (CommandeBis maCommande : list) {
            this.donneeCommande[index][0] = maCommande.getIdBis();
            this.donneeCommande[index][1] = maCommande.getDateCommandeBis();
            this.donneeCommande[index][2] = "Article Dispo";
            this.donneeCommande[index][3] = "[ ]";
            index++;
        }
        
        this.tabModelCommande = new TabModel(this.donneeCommande, titre);
        this.JTCommande = new JTable(tabModelCommande);
        
        JTCommande.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            private DelencherLivraison decLivraison;

            public void valueChanged(ListSelectionEvent arg0) {
               // decLivraison.JTCommande.clearSelection();
                int col = 3;
                int row = decLivraison.JTCommande.getSelectedRow(); // On envoie la ligne 
                decLivraison.JTCommande.setValueAt("[abcx]", row, col);
                decLivraison.JTCommande.revalidate();
                decLivraison.JTCommande.repaint();
                System.out.println(decLivraison.JTCommande.getValueAt(row, col));
                //System.out.println(tabModelCommande.getRowCount());
                //tabModelCommande.removeRow(JTCommande.getSelectedRow());

                //decLivraison.actualiserList();
            }

            private ListSelectionListener init(DelencherLivraison var) {
                decLivraison = var;
                return this;
            }
        }.init(this));
        
        this.scrollPaneCommande = new JScrollPane(JTCommande);
        this.panelListAll.add(scrollPaneCommande);
        this.panelListSelect.add(scrollPaneSelect);
    }
}
