/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import exceptions.ExceptionCreationClient;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Amaury
 */
@Local
public interface GestionClientLocal {

    long chercherClient(String nom, String prenom) throws exceptions.ExceptionClient;

    List<Long> listeNumComptes(long idClient) throws exceptions.ExceptionClient;

    /*long creerCompte(long idClient) throws exceptions.ExceptionClient;*/
    
    long validerConnexion(String email, String mdp) throws exceptions.ErreurConnexionClient;
    
    long creerClient(String nom, String prenom, String email, String mdp, String ville, String adresse, String tel) throws ExceptionCreationClient;
}
