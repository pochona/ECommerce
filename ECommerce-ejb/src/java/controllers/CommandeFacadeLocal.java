/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Commande;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Amaury
 */
@Local
public interface CommandeFacadeLocal {

    void create(Commande commande);

    void edit(Commande commande);

    void remove(Commande commande);

    Commande find(Object id);

    List<Commande> findAll();

    List<Commande> findRange(int[] range);

    int count();
    
    List<Commande> findCommandesClient(Integer idClient) throws exceptions.ExceptionCommande;
    
    /*int findIdComByClient(int idClient) throws ExceptionCommande;*/

}
