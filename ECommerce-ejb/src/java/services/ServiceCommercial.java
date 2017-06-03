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
import entitiesBis.StatutBis;
import exceptions.ExceptionArticle;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionArticleLocal;
import metiers.GestionCommandeLocal;
import metiers.GestionClientLocal;
import metiers.GestionStatutLocal;

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
    
    @EJB
    private GestionStatutLocal gestionStatut;
    
    @Override
    public List<String> lister(){
        return gestionArticle.lister();
    }
    
    @Override
    public List<ArticleBis> listerBis(){
        return gestionArticle.listerBis();
    }   
    
    @Override
    public void creer(String art)throws ExceptionArticle{
        Article a = gestionArticle.creer(art);
    }
    
    @Override
    public void supprimer(String i){
        Article a = gestionArticle.supprimer(i);
    }
    
    @Override
    public void editer(ArticleBis a){
        Article article = gestionArticle.editer(a);
    }
    
    @Override
    public ArticleBis retourArticle(Integer id){
        return gestionArticle.retourArticle(id);
    }

    @Override
    public List<CommandeBis> listerCommandeBis() {
        return gestionCommande.listerCommandeBis();
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

    @Override
    public StatutBis findDescrStatutById(String idStatut) {
        return gestionStatut.findDescrStatutById(idStatut);
    }

    @Override
    public List<CommandeBis> findCommandesByStatut(String idC) {
        return gestionCommande.findCommandesByStatut(idC);
    }
}
