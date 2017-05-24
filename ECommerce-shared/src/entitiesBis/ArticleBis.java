/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesBis;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ameli
 */
public class ArticleBis implements Serializable {
    
    private Integer idBis;
    private String libBis;
    private String descriptionBis;
    private Double prixHtBis;
    private Float tauxTvaBis;
    private Integer stockBis;
   // private List list;
    
    
    public ArticleBis(Integer id, String lib, String description, Double prixHt, Float tauxTva, Integer stock) {
        
        this.idBis = id;
        this.libBis = lib;
        this.descriptionBis = description;
        this.prixHtBis = prixHt;
        this.tauxTvaBis = tauxTva;
        this.stockBis = stock;
    }
    
    public ArticleBis(String art){
        Integer id;
        String lib, des;
        double prixHt;
        float tauxTva;
        int stock;

        String values[] = art.split(",");
        
        id = Integer.parseInt(values[0]);
        lib = values[1];
        des = values[2];
        prixHt = Double.parseDouble(values[3]);
        tauxTva = Float.parseFloat(values[4]);
        stock = Integer.parseInt(values[5]);
        
        this.idBis = id;
        this.libBis = lib;
        this.descriptionBis = des;
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
/*
    public List getList() {
        return list;
    }
  */  
    
}
