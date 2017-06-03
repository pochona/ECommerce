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
import java.awt.BorderLayout;
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
    private String idClient;
    
    private JPanel panelListClient = new JPanel();
    private JPanel panelInfo = new JPanel();
    private JPanel panelText = new JPanel();
    private JPanel panelListCommande = new JPanel(new BorderLayout());
  
    public GererCommande(Fenetre maFen, App app){
        this.maFenetre = maFen;
        this.app = app;

        init();
    }
    
    public void init(){
        ItemListener listener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED) {
                    String clientSelect = (String) e.getItem();
                    if(!clientSelect.equals("Selectionnez un client")){
                        String[] parts = clientSelect.split("-");
                        idClient = parts[0];
                        creerList();

                        //ConstruitTab(parts[0]);
                        System.out.println(parts[0]);
                    } else {
                        viderList();
                    }

                }   
            } 
        };
        
         //création label texte
        myFont = new Font(" TimesRoman ",Font.BOLD,20);
        JLabel label = new JLabel("Veuillez choisir un client");
        label.setFont(myFont);
       
        //creation liste deroulante
        listeClient = new JComboBox();
        listeClient.addItem("Selectionnez un client");
        // Chargement liste deroulante
        List<ClientBis> listClient = this.app.getServiceCommercial().listerClientBis();
        
        for(ClientBis clientBis : listClient) {
            listeClient.addItem(""+clientBis.getIdBis()+"-"+clientBis.getNomBis()+"-"+clientBis.getPrenomBis());
        } 
        
        // Ajout du listener a la liste
        listeClient.addItemListener(listener);

        this.setLayout(new BorderLayout());
        
        this.add(panelInfo, BorderLayout.NORTH);
        this.add(panelListCommande, BorderLayout.CENTER);
        
        this.panelInfo.add(this.panelText);
        this.panelInfo.add(this.panelListClient);
        
        this.panelText.add(label);   
        this.panelListClient.add(listeClient);
    }
    
    
    
    private void creerList(){
        this.panelListCommande.removeAll();
         //construct table
        String[] titreColonnes = {"Id commande","Date commande", "Id tournée","Statut"}; 

        List<CommandeBis> list = app.getServiceCommercial().findCommandesClient(this.idClient);

        // Initialisation de la taille
        Object[][] donneeCommande = new Object [list.size()][4];
        int index = 0;

        for(CommandeBis commandeBis : list) {
            //on récupère le statut par l'id du client
            StatutBis statutBis = app.getServiceCommercial().findDescrStatutById(Integer.toString(commandeBis.getIdStatutBis()));

            donneeCommande[index][0] = commandeBis.getIdBis();
            donneeCommande[index][1] = commandeBis.getDateCommandeBis();
            donneeCommande[index][2] = commandeBis.getIdTourneeBis();
            donneeCommande[index][3] = statutBis.getDescriptionBis();

            index++;
        }

        TabModel modelCommande = new TabModel(donneeCommande, titreColonnes);
        JTCommande = new JTable(modelCommande);
        JScrollPane scrollPaneA = new JScrollPane(JTCommande);
        this.panelListCommande.add(scrollPaneA, BorderLayout.CENTER);
        this.panelListCommande.revalidate();
        this.panelListCommande.repaint();
    }
    
    private void viderList(){
        this.panelListCommande.removeAll();
        this.panelListCommande.revalidate();
        this.panelListCommande.repaint();
    }
}
        
