/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controllers.ArticleFacadeLocal;
import entities.Article;
import entitiesBis.ArticleBis;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionArticleLocal;

/**
 *
 * @author Amélien
 */

@Stateless
public class ServiceCommercial implements ServiceCommercialRemote{
    
    @EJB
    private GestionArticleLocal gestionArticle;
    
    @EJB
    private ArticleFacadeLocal articleFacade;
    
    /*
    @Override
    public List<ArticleBis> listerBis() {
        return gestionArticle.listerBis();
    }*/
    
    @Override
    public List<String> lister(){
        return gestionArticle.lister();
    }
    
    @Override
    public ArticleBis creer(Integer id, String lib, String description, double prixHt, float tauxTva, int stock){
        Article a = articleFacade.creer(id, lib, description, prixHt, tauxTva, stock);
        return new ArticleBis(a.getId(), a.getLib(), a.getDescription(), a.getPrixHt(), a.getTauxTva(), a.getStock());
    }
}
