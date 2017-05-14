/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesBis;

import java.util.List;

/**
 *
 * @author ameli
 */
public class ArticleBis {
    
    private Integer idBis;
    private String libBis;
    private String descriptionBis;
    private double prixHtBis;
    private float tauxTvaBis;
    private int stockBis;
    private List list;
    
    
    public ArticleBis(Integer id, String lib, String description, double prixHt, float tauxTva, int stock) {
        
        //foreach(List<Article>){
        
        
        this.idBis = id;
        this.libBis = lib;
        this.descriptionBis = description;
        this.prixHtBis = prixHt;
        this.tauxTvaBis = tauxTva;
        this.stockBis = stock;
    }
    
    
}
