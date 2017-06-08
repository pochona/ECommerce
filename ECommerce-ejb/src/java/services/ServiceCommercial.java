/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Article;
import entities.Commande;
import entities.Tournee;
import entitiesBis.ArticleBis;
import entitiesBis.ClientBis;
import entitiesBis.CommandeBis;
import entitiesBis.StatutBis;
import exceptions.ExceptionArticle;
import exceptions.ExceptionCommande;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionArticleLocal;
import metiers.GestionCommandeLocal;
import metiers.GestionClientLocal;
import metiers.GestionStatutLocal;
import utilities.Log;

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
    
    public void declencherLivraison(Map<Integer, Integer> cmdALivrer){
        Tournee livraison = gestionCommande.creerLivraison();
        Log.log(livraison.toString());
        for(Map.Entry<Integer, Integer> entry : cmdALivrer.entrySet()){
            Commande c = null;
            try {
                c = gestionCommande.findCommande(entry.getKey());
            } catch (ExceptionCommande ex) {
                Logger.getLogger(ServiceCommercial.class.getName()).log(Level.SEVERE, null, ex);
            }
            Log.log("edition ma commande : " + c.getId() + " id tournee : " + livraison.getId());
            c.setIdTournee(livraison.getId());
            c.setIdStatut(3);
        }
    }
}
