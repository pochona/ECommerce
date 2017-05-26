/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Statut;
import entitiesBis.StatutBis;
import java.util.ArrayList;
import java.util.List;
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
    public List<StatutBis> findDescrStatutById(String idStatut) {
        
        
        TypedQuery<Statut> query = em.createNamedQuery("Statut.findById", Statut.class)
                                        .setParameter("idStatut", idStatut);

         /*Statut result = query.getSingleResult();
         
         StatutBis monStatutBis = new StatutBis(result.getId(), result.getLib(), result.getDescription());
         return monStatutBis;*/
         
        List<Statut> results = query.getResultList();
        List<StatutBis> b = new ArrayList<StatutBis>();
        for (Statut monStatut : results) {
            StatutBis bis = null;
            bis = new StatutBis(monStatut.getId(), monStatut.getLib(), monStatut.getDescription());
            b.add(bis);
        }
        return b;
         
    }
        
        
        /*List<StatutBis> b = new ArrayList<StatutBis>();
        for (Statut monStatut : results) {
            StatutBis bis = null;
            try {
                bis = new StatutBis(monStatut.getId(), monStatut.getLib(), monStatut.getDescription());
            } catch (NullPointerException e) {
                bis = new StatutBis(monStatut.getId(), monStatut.getLib(), monStatut.getDescription());
            }
            b.add(bis);
        }
        return b;*/
    
    
    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;


    protected EntityManager getEntityManager() {
        return em;
    }
    
}
