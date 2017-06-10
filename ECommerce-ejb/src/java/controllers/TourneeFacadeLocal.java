/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Tournee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Amaury
 */
@Local
public interface TourneeFacadeLocal {

    void create(Tournee tournee);

    void edit(Tournee tournee);

    void remove(Tournee tournee);

    Tournee find(Object id);

    List<Tournee> findAll();

    List<Tournee> findRange(int[] range);

    int count();
    
    Integer getMaxId();
}
