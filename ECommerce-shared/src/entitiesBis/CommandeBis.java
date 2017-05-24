/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesBis;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Laura
 */
public class CommandeBis implements Serializable{
    
    
    private Integer idBis;
    private Date dateCommandeBis;
    private Integer idClientBis;
    private Integer idTourneeBis;
    private Integer idStatutBis;

    public CommandeBis(Integer idBis, Date dateCommandeBis, Integer idClientBis, Integer idTourneeBis, Integer idStatutBis) {
        this.idBis = idBis;
        this.dateCommandeBis = dateCommandeBis;
        this.idClientBis = idClientBis;
        this.idTourneeBis = idTourneeBis;
        this.idStatutBis = idStatutBis;
    }
    
    public CommandeBis(Integer idBis, Date dateCommandeBis, Integer idClientBis, Integer idStatutBis) {
        this.idBis = idBis;
        this.dateCommandeBis = dateCommandeBis;
        this.idClientBis = idClientBis;
        this.idStatutBis = idStatutBis;
    }


    
    public void setIdBis(Integer idBis) {
        this.idBis = idBis;
    }


    public void setIdTourneeBis(Integer idTourneeBis) {
        this.idTourneeBis = idTourneeBis;
    }

    public void setDateCommandeBis(Date dateCommandeBis) {
        this.dateCommandeBis = dateCommandeBis;
    }

    public void setIdClientBis(Integer idClientBis) {
        this.idClientBis = idClientBis;
    }

    public void setIdStatutBis(Integer idStatutBis) {
        this.idStatutBis = idStatutBis;
    }
 
    
    public Integer getIdBis() {
        return idBis;
    }


    public Integer getIdTourneeBis() {
        return idTourneeBis;
    }

    public Date getDateCommandeBis() {
        return dateCommandeBis;
    }

    public Integer getIdClientBis() {
        return idClientBis;
    }

    public Integer getIdStatutBis() {
        return idStatutBis;
    }

    
}
