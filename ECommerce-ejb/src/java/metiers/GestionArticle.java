/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import controllers.ArticleFacadeLocal;
import entities.Article;
import entitiesBis.ArticleBis;
import exceptions.ErreurConnexionClient;
import exceptions.ExceptionArticle;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Laura
 */

@Stateless
public class GestionArticle implements GestionArticleLocal{
    
    @EJB
    private ArticleFacadeLocal articleFacade;

    @Override
    public long creerArticle(String description, String lib) throws ExceptionArticle {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String chercherArticle(String description, String lib) throws ExceptionArticle {
        return articleFacade.chercherArticle(description, lib);
    }

    @Override
    public List<Article> recupererArticle() throws ExceptionArticle {
        return articleFacade.findAll();
    }

    public Article findArticle(Integer id) throws ExceptionArticle {
        return articleFacade.find(id);
    }

    public List<Article> getArticleCommande(Integer idCom) throws ExceptionArticle {
        return articleFacade.findArticlesCommande(idCom);
    }
    
    /*
    public List<ArticleBis> listerBis() {
        return articleFacade.listerBis();
    }*/
    
    public List<String> lister() {
        return articleFacade.lister();
    }
    
    public Article creer(String art) throws ExceptionArticle{
        Integer id;
        String lib, des;
        double prixHt;
        float tauxTva;
        int stock;

        String values[] = art.split(",");
        
        id = Integer.parseInt(values[0]);
        lib = values[1];
        des = values[2];
        prixHt = Double.parseDouble(values[3]);
        tauxTva = Float.parseFloat(values[4]);
        stock = Integer.parseInt(values[5]);
        
        return articleFacade.creer(id, lib, des, prixHt, tauxTva, stock);
    }
    
}


    
    
    


