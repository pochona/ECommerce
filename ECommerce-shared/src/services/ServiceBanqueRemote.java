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

    public CompteShared validerCoordonnees(String numCarte, String numCrypto) throws ExceptionBancaire;
    
    public void debiterComptePourCommande(Integer idCommande, double montant);
    
    public void debiterCompte(Integer idCompte, double montant);
    
    public void crediterCompte(Integer idCompte, double montant);
    
    public CompteShared findCompteById(Integer idCompte);

}
