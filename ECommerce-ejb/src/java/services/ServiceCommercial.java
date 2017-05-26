/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Article;
import entitiesBis.ArticleBis;
import entitiesBis.ClientBis;
import entitiesBis.CommandeBis;
import exceptions.ExceptionArticle;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionArticleLocal;
import metiers.GestionCommandeLocal;
import java.lang.String;
import metiers.GestionClientLocal;

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
    
    @EJB
    private GestionClientLocal gestionClient;
    
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
        Article a = gestionArticle.creer(art);
    }
    
    public void supprimer(String i){
        Article a = gestionArticle.supprimer(i);
    }
    
    public void editer(ArticleBis a){
        gestionArticle.editer(a);
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
    
    @Override
    public List<ClientBis> listerClientBis() {
        return gestionClient.listerClientBis();
    }
    
    @Override
    public List<CommandeBis> listerCommandeBisALivrer(){
        return gestionCommande.listerCommandeBisALivrer();
    }
}
