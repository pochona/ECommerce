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
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import utilities.TabModel;

/**
 *
 * @author Laura
 */
public class GererCommande extends JPanel {
    Fenetre maFenetre;
    App app;
    private TabModel tabModelCom;
    private JTable JTCommande;
    
    public GererCommande(Fenetre maFen, App app){
        this.maFenetre = maFen;
        this.app = app;
        
        GridLayout tableau = new GridLayout(2, 1);
        this.setLayout(tableau);

        String[] titreColonnes = {"Id commande","Date commande","Id client", "Id tournée","Id statut"}; 

        //List<CommandeBis> list = this.app.getServiceCommercial().listerCommandeBis();
        List<CommandeBis> list = this.app.getServiceCommercial().findCommandesClient("3");

        // Initialisation de la taille
        Object[][] donneeCommande = new Object [list.size()][5];
        int index = 0;
        
        for(CommandeBis commandeBis : list) {
            donneeCommande[index][0] = commandeBis.getIdBis();
            donneeCommande[index][1] = commandeBis.getDateCommandeBis();
            donneeCommande[index][2] = commandeBis.getIdClientBis();
            donneeCommande[index][3] = commandeBis.getIdTourneeBis();
            donneeCommande[index][4] = commandeBis.getIdStatutBis();

            index++;
        }
        
        
        TabModel modelArticle = new TabModel(donneeCommande, titreColonnes);
        JTCommande = new JTable(modelArticle);

        JScrollPane scrollPaneA = new JScrollPane(JTCommande);

        this.add(scrollPaneA);

    }
}
