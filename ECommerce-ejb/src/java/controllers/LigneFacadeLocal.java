/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Ligne;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Amaury
 */
@Local
public interface LigneFacadeLocal {

    void create(Ligne ligne);

    void edit(Ligne ligne);

    void remove(Ligne ligne);

    Ligne find(Object id);

    List<Ligne> findAll();

    List<Ligne> findRange(int[] range);

    int count();
    
    List<Ligne> findByIdCommande(Integer idCommande) throws exceptions.ExceptionCommande;
    
}
