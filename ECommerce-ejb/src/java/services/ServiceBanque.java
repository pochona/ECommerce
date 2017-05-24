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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionClientLocal;
import metiers.GestionCommandeLocal;
import utilities.Log;

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
    
    @Override
    public long chercherClient(String nom, String prenom) throws ExceptionClient {
        return gestionClient.chercherClient(nom, prenom);
    }

   /*@Override
    public int findIdComByClient(int idClient) throws ExceptionCommande {
        return gestionCommande.findIdComByClient(idClient);
    }*/
    
    @Override
    public CompteShared validerCoordonnees(String numCarte, String numCrypto) throws ExceptionBancaire {
        //Log.log(new String[]{numCarte, numCrypto});
        Compte c = compteFacade.validerCoordonnees(numCarte, numCrypto);
        return new CompteShared(c.getNumCarte(), c.getNumCarte(), c.getId(), c.getSolde());
    }
    
}
