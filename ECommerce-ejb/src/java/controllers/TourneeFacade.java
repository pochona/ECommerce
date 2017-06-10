/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Tournee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Amaury
 */
@Stateless
public class TourneeFacade extends AbstractFacade<Tournee> implements TourneeFacadeLocal {

    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TourneeFacade() {
        super(Tournee.class);
    }

    @Override
    public Integer getMaxId() {
         Query q = em.createQuery(
               "select t from Tournee t where t.id = (select max(t2.id) from Tournee t2)");
            Tournee t = (Tournee) q.getSingleResult();
            return t.getId();
    }
    
}
