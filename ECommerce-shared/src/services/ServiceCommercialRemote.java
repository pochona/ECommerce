/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entitiesBis.ArticleBis;
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
    
    public ArticleBis creer(Integer id, String lib, String description, double prixHt, float tauxTva, int stock);
}
