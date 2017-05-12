/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.ExceptionClient;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionClientLocal;

/**
 *
 * @author Amaury
 */

@Stateless
public class ServiceBanque implements ServiceBanqueRemote {

    
    @EJB
    private GestionClientLocal gestionClient;
    
    @Override
    public long chercherClient(String nom, String prenom) throws ExceptionClient {
        return gestionClient.chercherClient(nom, prenom);
    }
    
    
}
