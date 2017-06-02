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
    
    public List<CommandeBis> listerCommandeBis();
    
    public StatutBis findDescrStatutById(String idStatut);
    
    public List<CommandeBis> findCommandesByStatut(String idC);
    
    public void modifieIdStatut(Integer idCom, Integer idStatut);
    
    public double getPUArticle(String id);
    
    public int getQteLigne(String idCom);
    
    public double getSolde(String idCom);
    
    public double getPrixTotaleCommande(Integer idCommande);
    
}
