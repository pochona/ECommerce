/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Article;
import entitiesBis.ArticleBis;
import exceptions.ExceptionArticle;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Laura
 */

@Local
public interface GestionArticleLocal {
    
    long creerArticle(String description, String lib) throws exceptions.ExceptionArticle;

    String chercherArticle(String description, String lib) throws exceptions.ExceptionArticle;
    
    List<Article> recupererArticle() throws exceptions.ExceptionArticle;
    
    //List<ArticleBis> listerBis();
    
    List<String> lister();
    
    Article findArticle(Integer id) throws exceptions.ExceptionArticle;
    
    List<Article> getArticleCommande(Integer idCom) throws exceptions.ExceptionArticle;
}
