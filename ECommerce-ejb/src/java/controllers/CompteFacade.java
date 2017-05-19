/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Commande;
import entities.Compte;
import exceptions.ExceptionBancaire;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import utilities.Log;

/**
 *
 * @author Amaury
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> implements CompteFacadeLocal {

    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }

    @Override
    public Compte validerCoordonnees(String numCarte, String numCrypto) throws ExceptionBancaire {
        TypedQuery<Compte> query = em.createNamedQuery("Compte.findByCoordonnees", Compte.class)
                                        .setParameter("numCarte", numCarte)
                                        .setParameter("crypto", numCrypto);
        try {
            Compte c = query.getSingleResult();
            return c;
        } catch(NoResultException e) {
            throw new exceptions.ExceptionBancaire();
        }
    }
    
}
