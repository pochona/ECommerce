/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Commande;
import entities.Statut;
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
}
