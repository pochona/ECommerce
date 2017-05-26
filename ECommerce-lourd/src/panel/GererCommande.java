/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import entitiesBis.ClientBis;
import entitiesBis.CommandeBis;
import entitiesBis.StatutBis;
import fenetre.Fenetre;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import utilities.TabModel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 *
 * @author Laura
 */
public class GererCommande extends JPanel {
    
    Fenetre maFenetre;
    App app;
    private JTable JTCommande;
    private JComboBox listeClient;
    private Font myFont;

  
    public GererCommande(Fenetre maFen, App app){
        this.maFenetre = maFen;
        this.app = app;
      
       
        //création label texte
        myFont = new Font(" TimesRoman ",Font.BOLD,20);
        JLabel label = new JLabel("Veuillez choisir un client");
        label.setFont(myFont);
        
     
        //creation liste deroulante
        listeClient = new JComboBox();
        listeClient.addItemListener(listener);
        
        List<ClientBis> listClient = this.app.getServiceCommercial().listerClientBis();
        
        for(ClientBis clientBis : listClient) {
            listeClient.addItem(""+clientBis.getIdBis()+"-"+clientBis.getNomBis()+"-"+clientBis.getPrenomBis());
        }

         //Postionnement des elements dans une gridlayout
        GridLayout tableau = new GridLayout(3, 1);
        this.add(label);      
        this.add(listeClient);
        this.setLayout(tableau);

    }
    
        ItemListener listener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                
           
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    String clientSelect = (String) e.getItem();
                    String[] parts = clientSelect.split("-");   
                    System.out.println(parts[0]);
                    ConstruitTab(parts[0]); 
                    
                }   
            } 
        };
        
        public void ConstruitTab(String idC){
            
        //création tableau commandes
        repaint();
        //String idStatut="1";
        
        String[] titreColonnes = {"Id commande","Date commande", "Id tournée","Id statut"}; 
        
        List<CommandeBis> list = this.app.getServiceCommercial().findCommandesClient(idC);
        
        //List<StatutBis> listStatutBis = this.app.getServiceCommercial().findDescrStatutById("1");



        // Initialisation de la taille
        Object[][] donneeCommande = new Object [list.size()][4];
        int index = 0;
        
            for(CommandeBis commandeBis : list) {
                donneeCommande[index][0] = commandeBis.getIdBis();
                donneeCommande[index][1] = commandeBis.getDateCommandeBis();
                donneeCommande[index][2] = commandeBis.getIdTourneeBis();
                donneeCommande[index][3] = commandeBis.getIdStatutBis();
                //donneeCommande[index][3] = statutDescr.getDescriptionBis();
              

                index++;
            }
        
        TabModel modelCommande = new TabModel(donneeCommande, titreColonnes);
        JTCommande = new JTable(modelCommande);
        JScrollPane scrollPaneA = new JScrollPane(JTCommande);
        this.add(scrollPaneA);
        modelCommande.fireTableDataChanged(); 
        System.out.println("construit!");
        
        modelCommande.fireTableDataChanged() ;
        JTCommande.revalidate();
        
        }
}
