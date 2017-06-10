/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entitiesBis.ArticleBis;
import entitiesBis.ClientBis;
import entitiesBis.CommandeBis;
import entitiesBis.StatutBis;
import exceptions.ExceptionArticle;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author Amélien
 */

@Remote
public interface ServiceCommercialRemote {
    
    List<ArticleBis> listerBis();
    
    List<String> lister();
    
    List<CommandeBis> listerCommandeBis();
    
    //List<String> listerCommande();
    
    public void creer(String art) throws ExceptionArticle;
    
    public void supprimer(String i);
    
    public void editer(ArticleBis a);
    
    public ArticleBis retourArticle(Integer id);
    
    List<CommandeBis> findCommandesClient(String idC);
    
    List<ClientBis> listerClientBis();
    
    List<CommandeBis> listerCommandeBisALivrer();
    
    StatutBis findDescrStatutById(String idStatut);
    
    public List<CommandeBis> findCommandesByStatut(String idC);

    public void declencherLivraison(Map<Integer, Integer> cmdSelected);

    public boolean getDispoArticleCommande(Integer idBis);
}
