/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesBis;

import java.io.Serializable;

/**
 *
 * @author Laura
 */
public class StatutBis implements Serializable{
    
    private Integer idBis;
    private String libBis;
    private String descriptionBis;

    public StatutBis(Integer idBis, String libBis, String descriptionBis) {
        this.idBis = idBis;
        this.libBis = libBis;
        this.descriptionBis = descriptionBis;
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

    public void setIdBis(Integer idBis) {
        this.idBis = idBis;
    }

    public void setLibBis(String libBis) {
        this.libBis = libBis;
    }

    public void setDescriptionBis(String descriptionBis) {
        this.descriptionBis = descriptionBis;
    }

    @Override
    public String toString() {
        return "StatutBis{" + "idBis=" + idBis + ", libBis=" + libBis + ", descriptionBis=" + descriptionBis + '}';
    }
    
    
    
}
