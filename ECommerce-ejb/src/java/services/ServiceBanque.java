/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import exceptions.ExceptionClient;
import exceptions.ExceptionCommande;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    private GestionCommandeLocal gestionCommande;
    
    @Override
    public long chercherClient(String nom, String prenom) throws ExceptionClient {
        return gestionClient.chercherClient(nom, prenom);
    }

    @Override
    public int findIdComByClient(int idClient) throws ExceptionCommande {
        return gestionCommande.findIdComByClient(idClient);
    }
}
