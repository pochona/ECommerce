/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import entitiesBis.CommandeBis;
import fenetre.Fenetre;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import utilities.TabModel;

/**
 *
 * @author Amaury_PC
 */
public class DelencherLivraison extends JPanel {

    private Fenetre maFenetre;
    private App app;

    private JTextField jtfield;
    
    private JButton boutonLivraison;
    
    private JPanel panelList;
    private JPanel panelRecap;
    private JPanel panelButton;
    private JPanel panelText;
    
    private JScrollPane scrollPaneCommande;
    
    private JTable jTCommande;
    
    private TabModel tabModelCommande;
    
    private Map<Integer, Integer> cmdSelected = new HashMap<Integer, Integer>();
    
    private static final String[] TITRE = {"ID", "Date", "Disponibilité articles"};
    
    
    public DelencherLivraison(Fenetre maFenetre, App app) {
        this.maFenetre = maFenetre;
        this.app = app;
        
        this.listerCommande();
        
        this.creerList();
        
        this.initPanel();
    }
    
    private void listerCommande(){

        List<CommandeBis> list = this.app.getServiceCommercial().findCommandesByStatut("2");

        // Initialisation de la taille
        Object[][] donneeCommande = new Object[list.size()][4];
        int index = 0;

        for (CommandeBis maCommande : list) {
            donneeCommande[index][0] = maCommande.getIdBis();
            donneeCommande[index][1] = maCommande.getDateCommandeBis();
            donneeCommande[index][2] = "Article Dispo";
            index++;
        }
        
        this.tabModelCommande = new TabModel(donneeCommande, TITRE);
        this.jTCommande = new JTable(tabModelCommande);

        jTCommande.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent arg0) {
                int col = 0;
                int row = getjTCommande().getSelectedRow(); // On envoie la ligne 
                try{
                    getjTCommande().clearSelection();
                    Integer val = (Integer) getTabModelCommande().getValueAt(row, col);
                    clickValue(val);
                } catch (java.lang.ArrayIndexOutOfBoundsException e){
                    // rien
                }
            }

        });
        
    }
    
    private void creerList(){        
        this.scrollPaneCommande = new JScrollPane(jTCommande);
    }
    
    private void clickValue(Integer i){
        if(cmdSelected.containsKey(i)){
            cmdSelected.remove(i);
        } else {
            cmdSelected.put(i, i);
        }
        
        this.actualiserTxtField();
    }
    
    private void actualiserTxtField(){
        String val = "Actuellement sélectionné : ";
        for(Map.Entry<Integer, Integer> entry : cmdSelected.entrySet()){
            val = val + entry.getKey() + ", ";
        }
        jtfield.setText(val);
    }
    
    private void initPanel(){
        this.setLayout(new BorderLayout());
        panelList = new JPanel(new BorderLayout());
        panelRecap = new JPanel(new GridLayout(2, 1));
        panelList.add(scrollPaneCommande, BorderLayout.CENTER);
        
        jtfield = new JTextField();
        jtfield.setText("Actuellement sélectionné : " );
        jtfield.setPreferredSize(new Dimension(1080, 30));
        panelText = new JPanel();
        panelText.add(jtfield);
        
        panelRecap.add(panelText);
        panelButton = new JPanel();
        this.boutonLivraison = new JButton("Valider livraison");
         // Ecoute du Jbutton "boutonModifier"
        boutonLivraison.addActionListener(new ActionListener() {
            AfficheProduit afficheProd;
            public void actionPerformed(ActionEvent e) {
                declencherLivraison();
            }
  
        });
        
        panelButton.add(boutonLivraison);
        panelRecap.add(panelButton);
        
        this.add(panelList, BorderLayout.CENTER);
        this.add(panelRecap, BorderLayout.SOUTH);
    }
    
    private void declencherLivraison(){
        app.getServiceCommercial().declencherLivraison(cmdSelected);
        cmdSelected.clear();
        actualiserTxtField();
        panelList.removeAll();
        listerCommande();
        creerList();
        panelList.add(scrollPaneCommande);
        panelList.repaint();
        panelList.revalidate();        
    }
    
    private JTable getjTCommande(){
        return this.jTCommande;
    }
   
    private TabModel getTabModelCommande(){
        return this.tabModelCommande ;
   }
}
