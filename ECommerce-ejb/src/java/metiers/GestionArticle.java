/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import controllers.ArticleFacadeLocal;
import entities.Article;
import entitiesBis.ArticleBis;
import exceptions.ExceptionArticle;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Article findArticle(Integer id) throws ExceptionArticle {
        return articleFacade.find(id);
    }

    @Override
    public List<Article> getArticleCommande(Integer idCom) throws ExceptionArticle {
        return articleFacade.findArticlesCommande(idCom);
    }
    
    
    @Override
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
        String values[] = art.split(",");

        String lib = values[1];
        String description = values[2];
        double prixHt = Double.parseDouble(values[3]);
        float tauxTva = Float.parseFloat(values[4]);
        int stock = Integer.parseInt(values[5]);
        
        Article a = new Article();
        a.setLib(lib);
        a.setDescription(description);
        a.setPrixHt(prixHt);
        a.setTauxTva(tauxTva);
        a.setStock(stock);
        
        articleFacade.create(a);
        
        return a;
    }
    
    public Article supprimer(String i){
        Integer id = Integer.parseInt(i);
        Article art = articleFacade.find(id);
        return articleFacade.supprimer(art);
    }
    
    public Article editer(ArticleBis a){
        Article art = articleFacade.find(a.getIdBis());
        art.setLib(a.getLibBis());
        art.setDescription(a.getDescriptionBis());
        art.setPrixHt(a.getPrixHtBis());
        art.setTauxTva(a.getTauxTvaBis());
        art.setStock(a.getStockBis());
        return articleFacade.editer(art);
    }
    
    public ArticleBis retourArticle(Integer id){
        Article a = articleFacade.find(id);
        ArticleBis abis = new ArticleBis(a.getId(), a.getLib(), a.getDescription(), a.getPrixHt(), a.getTauxTva(), a.getStock());
        return abis;
    }
    
    public Integer findStock(Integer id){
        Article a = articleFacade.find(id);
        Integer stockArticle = a.getStock();
        return stockArticle;
    }
    
    public void editerStock(Integer idArticle, Integer nouveauStock){
        Article art = articleFacade.find(idArticle);
        art.setStock(nouveauStock);
        articleFacade.editer(art);
    }
    
}
