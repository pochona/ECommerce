/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import controllers.ArticleFacadeLocal;
import controllers.CommandeFacadeLocal;
import controllers.StatutFacadeLocal;
import entities.Article;
import entities.Commande;
import entities.Statut;
import exceptions.ErreurConnexionClient;
import exceptions.ExceptionArticle;
import exceptions.ExceptionClient;
import exceptions.ExceptionCommande;
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
public class GestionCommande implements GestionCommandeLocal{
    
    @EJB
    private CommandeFacadeLocal commandeFacade;
    
    @EJB
    private StatutFacadeLocal statutFacade;

    @Override
    public List<Commande> recupererCommandes() throws ExceptionCommande {
         return commandeFacade.findAll();
    }

    @Override
    public Commande findCommande(Integer id) throws ExceptionCommande {
        return commandeFacade.find(id);
    }

    @Override
    public List<Commande> findCommandesClient(Integer idClient) throws ExceptionCommande {
        return commandeFacade.findCommandesClient(idClient);
    }

    @Override
    public Statut findStatut(Integer id) throws ExceptionCommande {
        return statutFacade.find(id);
    }
    
    
    
}


    
    
    


