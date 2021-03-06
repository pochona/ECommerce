/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controllers.CompteFacadeLocal;
import entities.Commande;
import entities.Compte;
import entitiesBis.CompteShared;
import exceptions.ExceptionBancaire;
import exceptions.ExceptionClient;
import exceptions.ExceptionCommande;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import metiers.GestionClientLocal;
import metiers.GestionCommandeLocal;

/**
 *
 * @author Amaury
 */

@Stateless
public class ServiceBanque implements ServiceBanqueRemote {

    
    @EJB
    private GestionClientLocal gestionClient;
    
    @EJB
    private CompteFacadeLocal compteFacade;
    
    @EJB
    private GestionCommandeLocal gestionCommande;
    
    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;


    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public CompteShared validerCoordonnees(String numCarte, String numCrypto) throws ExceptionBancaire {
        Compte c = compteFacade.validerCoordonnees(numCarte, numCrypto);
        return new CompteShared(c.getNumCarte(), c.getNumCarte(), c.getId(), c.getSolde());
    }
    
    @Override
    public void debiterComptePourCommande(Integer idCommande, double montant) {
        Commande c;
        try {
            c = gestionCommande.findCommande(idCommande);
            Compte cpt = compteFacade.find(c.getIdCompte());
            cpt.setSolde(cpt.getSolde() - montant);
        } catch (ExceptionCommande ex) {
            System.out.println("commande introuvable");
        }
    }    

    @Override
    public void debiterCompte(Integer idCompte, double montant) {
        Compte c = compteFacade.find(idCompte);
        double solde = c.getSolde();
        solde = solde - montant;
        c.setSolde(solde);
    }

    @Override
    public void crediterCompte(Integer idCompte, double montant) {
        Compte c = compteFacade.find(idCompte);
        double solde = c.getSolde();
        solde = solde + montant;
        c.setSolde(solde);
        
    }

    @Override
    public CompteShared findCompteById(Integer idCompte) {
        Compte c = compteFacade.find(idCompte);
        
        CompteShared cShared = new CompteShared(c.getNumCarte(), c.getNumCarte(), c.getId(), c.getSolde());
        return cShared;
    }
}
