/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Commande;
import entities.Ligne;
import entities.Statut;
import exceptions.ExceptionCommande;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Laura
 */

@Local
public interface GestionCommandeLocal {

    List<Commande> recupererCommandes() throws exceptions.ExceptionCommande;
    
    Commande findCommande(Integer id) throws exceptions.ExceptionCommande;
    
    List<Commande> findCommandesClient(Integer idClient) throws exceptions.ExceptionCommande;
    
    Statut findStatut(Integer id) throws exceptions.ExceptionCommande;
    
    int findIdComByClient(int idClient) throws exceptions.ExceptionCommande;
    
    List<Ligne> getLigneCommande(Integer idCommande) throws exceptions.ExceptionCommande;
    
    Commande creerCommande(Integer idClient, Integer idCompte) throws exceptions.ExceptionCommande;
    
    Ligne creerLigne(Integer idArticle, Integer idCommande, Integer qte) throws ExceptionCommande;
}
