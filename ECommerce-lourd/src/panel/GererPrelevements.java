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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
        
         //création bouton
        JButton bouton = new JButton("Effectuer prélèvement");

        this.add(bouton);   
        
        
        // Ecoute du bouton valider
        bouton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int col = 0;
                int row = JTCommande.getSelectedRow();
                //recuperation de l'id de la commande selectionnée
                Object cellule = JTCommande.getValueAt(row,col);
                
                double prixTotCommande = app.getServiceComptable().getPrixTotaleCommande((Integer) cellule);
                /*
                //recupere le prix unitaire ht de l'article commandé
                //TODO changer le return de la methode en liste car plusieurs articles pour une commande sinon erreur
                double prixht = 0.0;
                prixht = app.getServiceComptable().getPUArticle(i);
                
                //recupere la qte commande 
                //TODO changer le return de la methode en liste car plusieurs articles pour une commande sinon erreur
                int qte = 0;
                //qte = app.getServiceComptable().getQteLigne(i);
                 */
                //recupere le solde
                double solde = 0.0;
               
                solde = app.getServiceComptable().getSolde(cellule.toString());
                System.out.println("solde "+solde);
                    
               
                
                if(prixTotCommande<=solde){
                    app.getServiceComptable().modifieIdStatut(cellule.toString());
                     // Boite de dialogue indiquant le succès du prelevement
                    JOptionPane.showMessageDialog(null, "Prélèvement effectué!", "Succés", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Solde insuffisant!", "Erreur", JOptionPane.ERROR_MESSAGE);}
            }
            
        });

    }
}
