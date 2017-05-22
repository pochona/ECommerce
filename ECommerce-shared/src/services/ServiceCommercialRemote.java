/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entitiesBis.ArticleBis;
import exceptions.ExceptionArticle;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Amélien
 */

@Remote
public interface ServiceCommercialRemote {
    
    //List<ArticleBis> listerBis();
    
    List<String> lister();
    
    public void creer(String art) throws ExceptionArticle;
}