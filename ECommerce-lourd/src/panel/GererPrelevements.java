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
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    
    private JPanel panelList = new JPanel();
    private JPanel panelBtn = new JPanel();
    
    private JTable jTCommande;
    private TabModel modelCommande;
    
    private double prixTotalCommande;
    
    private static final String[] TITRECOLONNES = {"Id commande","Date commande", "Id tournée","Statut"}; 
    
    public GererPrelevements(Fenetre maFen, App app){
        this.maFenetre = maFen;
        this.app = app;
        this.init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("Gérer les prélèvements");
        label.setFont(new Font(" TimesRoman ", Font.BOLD, 20));
        this.add(label, BorderLayout.NORTH);
        this.add(panelList, BorderLayout.CENTER);
        this.add(panelBtn, BorderLayout.SOUTH);
        
        
        this.creerBouton();
        this.creerList();
    }
    
    
    public void creerBouton(){
        //création bouton
        JButton bouton = new JButton("Effectuer prélèvement");

        this.panelBtn.add(bouton);   
        
        
        // Ecoute du bouton valider
        bouton.addActionListener((ActionEvent ae) -> {
            int col = 0;
            int row = jTCommande.getSelectedRow();
            //recuperation de l'id de la commande selectionnée
            Integer idCommande = (Integer) jTCommande.getValueAt(row,col);
            
            declencherPrelevement(idCommande);
        });
    }
    
    
    public void creerList(){
        //Tableau
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
        
        this.modelCommande = new TabModel(donneeCommande, TITRECOLONNES);
        this.jTCommande = new JTable(modelCommande);
        this.panelList.add(new JScrollPane(jTCommande));
        
        
    }
    
    public void declencherPrelevement(Integer idCommande){           
        if(this.verifierSolde(idCommande)){
            app.getServiceComptable().modifieIdStatut(idCommande, 2);
            this.debiterCompte(idCommande);
            this.actualiserList();
             // Boite de dialogue indiquant le succès du prelevement
            JOptionPane.showMessageDialog(null, "Prélèvement effectué !", "Succés", JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "Solde insuffisant !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean verifierSolde(Integer idCommande){
        this.prixTotalCommande = app.getServiceComptable().getPrixTotaleCommande(idCommande);
        System.out.println("prix tot "+prixTotalCommande);
        //recupere le solde
        double solde = app.getServiceComptable().getSolde(idCommande.toString());
        System.out.println("solde "+solde);
        
        return prixTotalCommande<=solde;
    }
    
    public void debiterCompte(Integer idCommande){
        this.app.getServiceBanque().debiterComptePourCommande(idCommande, this.prixTotalCommande);
    }
    
    public void actualiserList(){
        this.panelList.removeAll();
        
        this.creerList();
        
        this.panelList.revalidate();
        this.panelList.repaint();
    }
}
