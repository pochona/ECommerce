/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entitiesBis.CompteShared;
import exceptions.ExceptionBancaire;
import exceptions.ExceptionCommande;
import java.util.List;
import javax.ejb.Remote;



/**
 *
 * @author Amaury
 */
@Remote
public interface ServiceBanqueRemote {
    
    long chercherClient(String nom, String prenom) throws exceptions.ExceptionClient;
   
    int findIdComByClient(int idClient) throws ExceptionCommande;
    
    public CompteShared validerCoordonnees(String numCarte, String numCrypto) throws ExceptionBancaire;
}
