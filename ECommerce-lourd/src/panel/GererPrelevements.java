/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import app.App;
import entitiesBis.CommandeBis;
import entitiesBis.StatutBis;
import fenetre.Fenetre;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import utilities.TabModel;

/**
 *
 * @author Laura
 */
public class GererPrelevements extends JPanel{
    
    Fenetre maFenetre;
    App app;
    
    public GererPrelevements(Fenetre maFen, App app){
        this.maFenetre = maFen;
        this.app = app;

        
        //création bouton
        JButton bouton = new JButton("Mon premier bouton");
        
         //Postionnement des elements dans une gridlayout
        //GridLayout disposition = new GridLayout(2, 1);
        //this.setLayout(disposition);
        this.add(bouton);   

        
        //Tableau
        String[] titreColonnes = {"Id commande","Date commande", "Id tournée","Statut"}; 
        List<CommandeBis> list = this.app.getServiceComptable().findCommandesByStatut("1");
        
        // Initialisation de la taille
        Object[][] donneeCommande = new Object [list.size()][4];
        int index = 0;
        
        for(CommandeBis commandeBis : list) {
                //on récupère le statut par l'id du client
                StatutBis statutBis = this.app.getServiceComptable().findDescrStatutById(Integer.toString(commandeBis.getIdStatutBis()));

                donneeCommande[index][0] = commandeBis.getIdBis();
                donneeCommande[index][1] = commandeBis.getDateCommandeBis();
                donneeCommande[index][2] = commandeBis.getIdTourneeBis();
                donneeCommande[index][3] = statutBis.getDescriptionBis();

                index++;
            }
        
        TabModel modelCommande = new TabModel(donneeCommande, titreColonnes);
        JTable JTCommande = new JTable(modelCommande);
        JScrollPane scrollPaneA = new JScrollPane(JTCommande);
        this.add(scrollPaneA);
        
    }
}
