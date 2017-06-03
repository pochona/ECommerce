/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
import entities.Commande;
import entitiesBis.ArticleBis;
import exceptions.ErreurConnexionClient;
import exceptions.ExceptionArticle;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 *
 * @author Amaury
 */
@Stateless
public class ArticleFacade extends AbstractFacade<Article> implements ArticleFacadeLocal /*, ArticleFacadeRemote */{

    @PersistenceContext(unitName = "ECommerce-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }

    @Override
    public String chercherArticle(String description, String lib) {
        Query q = em.createQuery(
               "select a from Article a where a.description = :description and a.lib = :lib");
            q.setParameter("description", description);
            q.setParameter("lib", lib);
            Article a = (Article) q.getSingleResult();
            return a.getLib();
    }
    
    @Override
    public List<Article> findArticlesCommande(Integer idComm) throws exceptions.ExceptionArticle{
        TypedQuery<Article> query = em.createNamedQuery("Article.findByCommande", Article.class);
                                        //.setParameter("id", idComm);
        List<Article> results = query.getResultList();
        return results;
       /*
       Query q = em.createQuery(
               "select a from Article a join Ligne l on l.ID_ARTICLE = a.ID where l.ID_COMMANDE = :idComm");
        q.setParameter("idComm", 1);
        List<Article> a = q.getResultList();
        return a;*/
    }
    /*
    @Override
    public List<ArticleBis> listerBis() {
        
        List<Article> list = this.findAll();
        List<ArticleBis> listBis = new ArrayList<ArticleBis>();
        for(Article a : list) {    
            ArticleBis aBis = new ArticleBis(a.getId(), a.getLib(), a.getDescription(), a.getPrixHt(), a.getTauxTva(), a.getStock());
            listBis.add(aBis);
        
        //List<ArticleBis> listBis = new ArrayList<Article>(list);
        //List<ArticleBis> listBis = (List<Article>) list.clone();
        }
        return listBis;
    }*/
    
    @Override
    public List<String> lister() {
        List<Article> list = this.findAll(); // Appel de la méthode du CRUD
	List<String> listSt = new ArrayList<String>();
	for(Article a : list) {
		listSt.add(a.toString());
	}
        return listSt;
    }

    public Article supprimer(Article a){
        this.remove(a);
        return a;
    }
    
    public Article editer(Article a){
        this.edit(a);
        return a;
    }
}
