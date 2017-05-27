/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
import entitiesBis.ArticleBis;
import exceptions.ErreurConnexionClient;
import exceptions.ExceptionArticle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Amaury
 */
@Local
public interface ArticleFacadeLocal {

    void create(Article article);

    void edit(Article article);

    void remove(Article article);

    Article find(Object id);

    List<Article> findAll();
    
    //List<ArticleBis> listerBis();

    List<String> lister();
    
    List<Article> findRange(int[] range);

    int count();
    
    String chercherArticle(String description, String lib);
    
    List<Article> findArticlesCommande(Integer idComm) throws exceptions.ExceptionArticle;
    
    Article creer(Integer id, String lib, String description, double prixHt, float tauxTva, int stock) throws ExceptionArticle;
    
    Article supprimer(Article a);
    
    Article editer(Article a);
}
