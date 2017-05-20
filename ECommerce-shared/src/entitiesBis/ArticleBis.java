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
        
        this.idBis = id;
        this.libBis = lib;
        this.descriptionBis = description;
        this.prixHtBis = prixHt;
        this.tauxTvaBis = tauxTva;
        this.stockBis = stock;
    }
    
    public String toString() {
        return "ArticleBis[ id=" + getIdBis() + " ] - Description : " + getDescriptionBis()+"";
    }

    public Integer getIdBis() {
        return idBis;
    }

    public String getLibBis() {
        return libBis;
    }

    public String getDescriptionBis() {
        return descriptionBis;
    }

    public double getPrixHtBis() {
        return prixHtBis;
    }

    public float getTauxTvaBis() {
        return tauxTvaBis;
    }

    public int getStockBis() {
        return stockBis;
    }

    public List getList() {
        return list;
    }
    
    
}
