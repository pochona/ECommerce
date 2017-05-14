/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Laura
 */

@Remote
public interface ArticleFacadeRemote {
    
    // Création d'un produit (pour Back Office)
    String creer(Integer id, String lib, String description, double prixHt, float tauxTva, int stock);
    // Liste les produits sous la forme de chaînes
    List<String> lister();
    // Renvoie les infos d'un produit
    public String infos(long id);
    // Renvoie le prix d'un produit
    public double prix(long id);
    
}
