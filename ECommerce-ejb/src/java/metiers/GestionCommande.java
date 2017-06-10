/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import controllers.ArticleFacadeLocal;
import controllers.CommandeFacadeLocal;
import controllers.LigneFacadeLocal;
import controllers.StatutFacadeLocal;
import controllers.TourneeFacadeLocal;
import entities.Article;
import entities.Commande;
import entities.Ligne;
import entities.Statut;
import entities.Tournee;
import entitiesBis.CommandeBis;
import exceptions.ExceptionCommande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utilities.Log;

/**
 *
 * @author Laura
 */
@Stateless
public class GestionCommande implements GestionCommandeLocal {

    @EJB
    private CommandeFacadeLocal commandeFacade;

    @EJB
    private StatutFacadeLocal statutFacade;

    @EJB
    private LigneFacadeLocal ligneFacade;

    @EJB
    private TourneeFacadeLocal tourneeFacade;
    
    @EJB
    private ArticleFacadeLocal articleFacade;
    
    
    @Override
    public List<Commande> recupererCommandes() throws ExceptionCommande {
        return commandeFacade.findAll();
    }

    @Override
    public Commande findCommande(Integer id) throws ExceptionCommande {
        return commandeFacade.find(id);
    }

    @Override
    public List<Commande> findCommandesClient(Integer idClient) throws ExceptionCommande {
        return commandeFacade.findCommandesClient(idClient);
    }

    @Override
    public Statut findStatut(Integer id) throws ExceptionCommande {
        return statutFacade.find(id);
    }

    @Override
    public List<Ligne> getLigneCommande(Integer idCommande) throws ExceptionCommande {
        return ligneFacade.findByIdCommande(idCommande);
    }

    @Override
    public Commande creerCommande(Integer idClient, Integer idCompte) throws ExceptionCommande {
        Commande c = new Commande();
        c.setIdClient(idClient);
        c.setIdCompte(idCompte);
        c.setIdStatut(1);
        c.setDateCommande(new Date());
        commandeFacade.create(c);
        return c;
    }

    @Override
    public Ligne creerLigne(Integer idArticle, Integer idCommande, Integer qte) throws ExceptionCommande {
        Ligne l = new Ligne();
        l.setIdArticle(idArticle);
        l.setIdCommande(idCommande);
        l.setQte(qte);

        ligneFacade.create(l);
        return l;
    }

    @Override
    public List<CommandeBis> listerCommandeBis() {
        List <Commande> c = commandeFacade.findAll();
        List<CommandeBis> b = new ArrayList<CommandeBis>();
        for (Commande maCom : c) {
            CommandeBis bis = null;
            try {
                bis = new CommandeBis(maCom.getId(), maCom.getDateCommande(), maCom.getIdClient(), maCom.getIdTournee(), maCom.getIdStatut());
            } catch (NullPointerException e) {
                bis = new CommandeBis(maCom.getId(), maCom.getDateCommande(), maCom.getIdClient(), maCom.getIdStatut());
            }
            b.add(bis);
        }
        return b;
    }

    @Override
    public List<CommandeBis> findCommandesClient(String idC) {
        int idClient = Integer.parseInt(idC); 
        
        TypedQuery<Commande> query = em.createNamedQuery("Commande.findByIdClient", Commande.class)
                                        .setParameter("idClient", idClient);
        List<Commande> results = query.getResultList();
        
        List<CommandeBis> b = new ArrayList<CommandeBis>();
        for (Commande maCom : results) {
            CommandeBis bis = null;
            try {
                bis = new CommandeBis(maCom.getId(), maCom.getDateCommande(), maCom.getIdClient(), maCom.getIdTournee(), maCom.getIdStatut());
            } catch (NullPointerException e) {
                bis = new CommandeBis(maCom.getId(), maCom.getDateCommande(), maCom.getIdClient(), maCom.getIdStatut());
            }
            b.add(bis);
        }
        return b;
    }
    
    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;


    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<CommandeBis> listerCommandeBisALivrer() {
        List<CommandeBis> list = new ArrayList<CommandeBis>();
        // A coder @AP
        return list;
    }

    @Override
    public List<CommandeBis> findCommandesByStatut(String idStat) {
         int idStatut = Integer.parseInt(idStat); 
        
        TypedQuery<Commande> query = em.createNamedQuery("Commande.findByIdStatut", Commande.class)
                                        .setParameter("idStatut", idStatut);
        List<Commande> results = query.getResultList();
        
        
        List<CommandeBis> b = new ArrayList<CommandeBis>();
        for (Commande maCom : results) {
            CommandeBis bis = null;
            try {
                bis = new CommandeBis(maCom.getId(), maCom.getDateCommande(), maCom.getIdClient(), maCom.getIdTournee(), maCom.getIdStatut());
            } catch (NullPointerException e) {
                bis = new CommandeBis(maCom.getId(), maCom.getDateCommande(), maCom.getIdClient(), maCom.getIdStatut());
            }
            b.add(bis);
        }
        return b;
    }

    @Override
    public void modifieIdStatut(Integer idCom, Integer idStatut) {
        TypedQuery<Commande> query = em.createNamedQuery("Commande.findById", Commande.class)
                                        .setParameter("id", idCom);
        Commande result = query.getSingleResult();
        result.setIdStatut(idStatut);
    }
    /*
    @Override
    public double getPUArticle(String idCom) {
        
        int id = Integer.parseInt(idCom); 
        
        Query q = em.createQuery(
               "SELECT a.prixHt FROM Article a, Ligne l, Commande c WHERE l.idCommande=c.id AND a.id=l.idArticle AND c.id=:id");
            q.setParameter("id", id);

            double prixHT =0.0;
            return prixHT = (double) q.getSingleResult();
    }

    @Override
    public int getQteLigne(String idCom) {
        
        int id = Integer.parseInt(idCom); 
        
        Query q = em.createQuery(
               "SELECT l.qte FROM Ligne l, Commande c WHERE l.idCommande=c.id AND c.id=:id");
            q.setParameter("id", id);

            int prixHT =0;
            return prixHT = (int) q.getSingleResult();    
    }
*/
    @Override
    public double getSolde(String idCom) {
        int id = Integer.parseInt(idCom); 
        double solde =0.0;
         try{     
            Query q = em.createQuery(
               "SELECT cpt.solde FROM Compte cpt, Commande cmd WHERE cpt.id=cmd.idCompte AND cmd.id=:id");
            q.setParameter("id", id);
            solde = (double) q.getSingleResult();
             
         }catch(NoResultException e){
            System.out.println("Pas de compte");
         }

            return solde;   
    }

    @Override
    public double getPrixTotaleCommande(Integer idCommande) {
        double prixTotUnitaire = 0;
        double prixTot = 0;
        double montantTot = 0;
        try {
            // Recuperation des lignes de la commande
            List<Ligne> maList = getLigneCommande(idCommande);
            // Parcours des lignes 
            for (Ligne maLigne : maList) {
                // Recuperation des articles pour chaque ligne
                Article monArt = articleFacade.find(maLigne.getIdArticle());
                prixTotUnitaire = (Math.round(monArt.getPrixHt()*(1+monArt.getTauxTva()) * 100.0) / 100.0);
                prixTot = prixTotUnitaire * maLigne.getQte();
                montantTot = montantTot + prixTot;
            }
            montantTot = Math.round(montantTot*100.0)/100.0;
        } catch(NoResultException e){
            System.out.println("Pas de ligne");
        } catch (ExceptionCommande ex) {
            System.out.println("Problème de recuperation");
        }
        return montantTot;
    }
    
    @Override
    public Tournee creerLivraison(){
        Tournee t = new Tournee();
        t.setDateTournee(new Date());
        // faux ID pour contrer un problème d'auto incremente
        t.setId(null);

        tourneeFacade.create(t);
        Integer id = tourneeFacade.getMaxId();
        Tournee t2 = tourneeFacade.find(id);

        return t2;
    }

}
