/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
import java.util.ArrayList;
import java.util.List;
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
    }
    
    @Override
    public List<String> lister() {
        List<Article> list = this.findAll(); // Appel de la méthode du CRUD
	List<String> listSt = new ArrayList<String>();
	for(Article a : list) {
		listSt.add(a.toString());
	}
        return listSt;
    }

    @Override
    public Article supprimer(Article a){
        this.remove(a);
        return a;
    }
    
    @Override
    public Article editer(Article a){
        this.edit(a);
        return a;
    }
}
