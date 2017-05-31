/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entitiesBis.CommandeBis;
import entitiesBis.StatutBis;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Laura
 */

@Remote
public interface ServiceComptableRemote {
    
    List<CommandeBis> listerCommandeBis();
    
    StatutBis findDescrStatutById(String idStatut);
    
    List<CommandeBis> findCommandesByStatut(String idC);
    
    public void modifieIdStatut(String idCom);
    
    double getPUArticle(String id);
    
    int getQteLigne(String idCom);
    
    double getSolde(String idCom);
    
}
