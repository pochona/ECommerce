/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import entitiesBis.CommandeBis;
import entitiesBis.StatutBis;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionCommandeLocal;
import metiers.GestionStatutLocal;

/**
 *
 * @author Laura
 */

@Stateless
public class ServiceComptable implements ServiceComptableRemote {
    
    @EJB
    private GestionCommandeLocal gestionCommande;
    
    @EJB
    private GestionStatutLocal gestionStatut;

    @Override
    public List<CommandeBis> listerCommandeBis() {
        return gestionCommande.listerCommandeBis();
    }

    @Override
    public StatutBis findDescrStatutById(String idStatut) {
        return gestionStatut.findDescrStatutById(idStatut);
    }

    @Override
    public List<CommandeBis> findCommandesByStatut(String idC) {
        return gestionCommande.findCommandesByStatut(idC);
    }

    @Override
    public void modifieIdStatut(String idCom) {
        gestionCommande.modifieIdStatut(idCom);
    }

    @Override
    public double getPUArticle(String id) {
        return gestionCommande.getPUArticle(id);
    }

    @Override
    public int getQteLigne(String idCom) {
        return gestionCommande.getQteLigne(idCom);
    }

    @Override
    public double getSolde(String idCom) {
       return gestionCommande.getSolde(idCom); 
    }

    @Override
    public double getPrixTotaleCommande(Integer idCommande) {
        return gestionCommande.getPrixTotaleCommande(idCommande);
    }



    
}
