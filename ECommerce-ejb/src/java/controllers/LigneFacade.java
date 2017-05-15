/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Ligne;
import exceptions.ExceptionCommande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Amaury
 */
@Stateless
public class LigneFacade extends AbstractFacade<Ligne> implements LigneFacadeLocal {

    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneFacade() {
        super(Ligne.class);
    }

    @Override
    public List<Ligne> findByIdCommande(Integer idCommande) throws ExceptionCommande {
        TypedQuery<Ligne> query = em.createNamedQuery("Ligne.findByIdCommande", Ligne.class)
                                        .setParameter("idCommande", idCommande);
        List<Ligne> results = query.getResultList();
        return results;
    }
    
}
