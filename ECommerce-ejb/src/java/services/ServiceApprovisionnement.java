/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import metiers.GestionArticleLocal;

/**
 *
 * @author Amélien
 */

@Stateless
public class ServiceApprovisionnement implements ServiceApprovisionnementRemote{
    
    @EJB
    private GestionArticleLocal gestionArticle;
    
    @Override
    public Integer findStock(Integer idArticleBis){
        return gestionArticle.findStock(idArticleBis);
    }
    
    @Override
    public void editerStock(Integer idArticle, Integer nouveauStock){
        gestionArticle.editerStock(idArticle,nouveauStock);
    }
    
    
}
