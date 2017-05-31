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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import utilities.Log;

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
    
    
    public List<ArticleBis> listerBis() {
        List<Article> a = articleFacade.findAll();
        List<ArticleBis> b = new ArrayList<ArticleBis>();
        for(Article monArt : a){
            ArticleBis bis = new ArticleBis(monArt.getId(), monArt.getLib(), monArt.getDescription(), monArt.getPrixHt(), monArt.getTauxTva(), monArt.getStock());
            b.add(bis);
        }
        return b;
    }
    
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
    
    public Article supprimer(String i){
        Integer id = Integer.parseInt(i);
        Article art = articleFacade.find(id);
        return articleFacade.supprimer(art);
    }
    
    public Article editer(ArticleBis a){
        Article art = articleFacade.find(a.getIdBis());
        System.out.println("ID qui arrive dans Gestion Article : "+a.getIdBis());
        System.out.println("Id de l'article art : "+art.getId());
        System.out.println("des qui arrive dans Gestion Article : "+a.getDescriptionBis());
        System.out.println("des de l'article art : "+art.getDescription());
        return articleFacade.editer(art);
    }
    
    public ArticleBis retourArticle(Integer id){
        Article a = articleFacade.find(id);
        ArticleBis abis = new ArticleBis(a.getId(), a.getLib(), a.getDescription(), a.getPrixHt(), a.getTauxTva(), a.getStock());
        return abis;
    }
}
