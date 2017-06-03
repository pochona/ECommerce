/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Statut;
import entitiesBis.StatutBis;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Laura
 */

@Stateless
public class GestionStatut implements GestionStatutLocal {

    @Override
    public StatutBis findDescrStatutById(String idStatut) {
        
        int id = Integer.parseInt(idStatut); 
        TypedQuery<Statut> query = em.createNamedQuery("Statut.findById", Statut.class)
                                        .setParameter("id", id);


         
        Statut result = query.getSingleResult();
         
        StatutBis monStatutBis = new StatutBis(result.getId(), result.getLib(), result.getDescription());
        return monStatutBis;
         
    }

    
    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;


    protected EntityManager getEntityManager() {
        return em;
    }
    
}
