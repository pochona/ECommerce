/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import controllers.ArticleFacadeLocal;
import entities.Article;
import entities.Commande;
import entities.Ligne;
import entities.Tournee;
import entitiesBis.ArticleBis;
import entitiesBis.ClientBis;
import entitiesBis.CommandeBis;
import entitiesBis.StatutBis;
import exceptions.ExceptionArticle;
import exceptions.ExceptionCommande;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionArticle;
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
    
    @EJB
    private ArticleFacadeLocal articleFacade;
    
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
        for(Map.Entry<Integer, Integer> entry : cmdALivrer.entrySet()){
            Commande c = null;
            try {
                c = gestionCommande.findCommande(entry.getKey());
            } catch (ExceptionCommande ex) {
                Logger.getLogger(ServiceCommercial.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.setIdTournee(livraison.getId());
            c.setIdStatut(3);
            gestionArticle.destockerCommande(c);
        }
    }

    @Override
    public boolean getDispoArticleCommande(Integer idC) {
        boolean verif = true;
        List<Ligne> list;
        try {
            list = gestionCommande.getLigneCommande(idC);
            Iterator it = list.iterator();
            while(verif && it.hasNext()){
                Ligne l = (Ligne) it.next();
                Article a = articleFacade.find(l.getIdArticle());
                if(a.getStock() < l.getQte()){
                    verif = false;
                }
            }
        } catch (ExceptionCommande ex) {
            Logger.getLogger(ServiceCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verif;
    }
}
