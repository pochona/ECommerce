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
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Amaury_PC
 */
public class AfficheProduit extends JPanel {
 
    Fenetre maFenetre;
    App app;
    
    public AfficheProduit(Fenetre maFenetre, App app){
        this.maFenetre = maFenetre;
        this.app = app;

        GridLayout tableau = new GridLayout(10,3);
        //panel.setLayout(new FlowLayout());
        this.setLayout(tableau);

        String[] titreColonnes = {"ID","Libellé", "Description","Prix Hors taxe", "TVA", "Stock"}; 

        List<ArticleBis> list = this.app.getServiceCommercial().listerBis();

        // Initialisation de la taille
        Object[][] donneeArticle = new Object [list.size()][6];
        int index = 0;
        
        for(ArticleBis articleBis : list) {
            donneeArticle[index][0] = articleBis.getIdBis();
            donneeArticle[index][1] = articleBis.getLibBis();
            donneeArticle[index][2] = articleBis.getDescriptionBis();
            donneeArticle[index][3] = articleBis.getPrixHtBis();
            donneeArticle[index][4] = articleBis.getTauxTvaBis();
            donneeArticle[index][5] = articleBis.getStockBis();
            index++;
        }
        //TabModel modelArticle = new TabModel(donneeArticle, titreColonnes);
        JTable JT = new JTable(donneeArticle, titreColonnes);
        this.add(JT);
        // S'il y a plus d'articles que la fenêtre ne peut afficher
        //getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
    }
}
