/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Article;
import entitiesBis.ArticleBis;
import entitiesBis.CommandeBis;
import exceptions.ExceptionArticle;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionArticleLocal;
import metiers.GestionCommandeLocal;
import java.lang.String;

/**
 *
 * @author Amélien
 */

@Stateless
public class ServiceCommercial implements ServiceCommercialRemote{
    
    @EJB
    private GestionArticleLocal gestionArticle;
    
    @EJB
    private GestionCommandeLocal gestionCommande;
    
    /*
    @Override
    public List<ArticleBis> listerBis() {
        return gestionArticle.listerBis();
    }*/
    
    @Override
    public List<String> lister(){
        return gestionArticle.lister();
    }
    
    public List<ArticleBis> listerBis(){
        return gestionArticle.listerBis();
    }
    
    
    
    @Override
    public void creer(String art)throws ExceptionArticle{
        /*Integer id;
        String lib, des;
        double prixHt;
        float tauxTva;
        int stock;
        System.out.println(art);
        String values[] = art.split(",");
        id = Integer.parseInt(values[0]);
        lib = values[1];
        des = values[2];
        prixHt = Double.parseDouble(values[3]);
        tauxTva = Float.parseFloat(values[4]);
        stock = Integer.parseInt(values[5]);
        System.out.println(id);
        System.out.println(des);
        System.out.println(prixHt);
        System.out.println(tauxTva);
        System.out.println(stock);
        */
        
        Article a = gestionArticle.creer(art);
        //return new ArticleBis(art);
    }

    @Override
    public List<CommandeBis> listerCommandeBis() {
        return gestionCommande.listerCommandeBis();
    }

    @Override
    public List<String> listerCommande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CommandeBis> findCommandesClient(String idC) {
        return gestionCommande.findCommandesClient(idC);
    }



}
