/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
import entities.Client;
import entities.Commande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Amaury
 */
@Stateless
public class CommandeFacade extends AbstractFacade<Commande> implements CommandeFacadeLocal {

    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }

    @Override
    public List<Commande> findCommandesClient(Integer idClient) throws exceptions.ExceptionCommande{
        TypedQuery<Commande> query = em.createNamedQuery("Commande.findByIdClient", Commande.class)
                                        .setParameter("idClient", idClient);
        List<Commande> results = query.getResultList();
        return results;
    }
    
}
