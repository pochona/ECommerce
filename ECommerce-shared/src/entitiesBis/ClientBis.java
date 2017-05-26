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
public class ClientBis implements Serializable{
    
    private Integer idBis;
    private String nomBis; 
    private String prenomBis; 
    private String villeBis;
    private String adresseBis; 
    private String telBis;
    private String mailBis; 
    private String mdpBis;

    public ClientBis(Integer idBis, String nomBis, String prenomBis, String villeBis, String adresseBis, String telBis, String mailBis, String mdpBis) {
        this.idBis = idBis;
        this.nomBis = nomBis;
        this.prenomBis = prenomBis;
        this.villeBis = villeBis;
        this.adresseBis = adresseBis;
        this.telBis = telBis;
        this.mailBis = mailBis;
        this.mdpBis = mdpBis;
    }

    public Integer getIdBis() {
        return idBis;
    }

    public String getNomBis() {
        return nomBis;
    }

    public String getPrenomBis() {
        return prenomBis;
    }

    public String getVilleBis() {
        return villeBis;
    }

    public String getAdresseBis() {
        return adresseBis;
    }

    public String getTelBis() {
        return telBis;
    }

    public String getMailBis() {
        return mailBis;
    }

    public String getMdpBis() {
        return mdpBis;
    }

    public void setIdBis(Integer idBis) {
        this.idBis = idBis;
    }

    public void setNomBis(String nomBis) {
        this.nomBis = nomBis;
    }

    public void setPrenomBis(String prenomBis) {
        this.prenomBis = prenomBis;
    }

    public void setVilleBis(String villeBis) {
        this.villeBis = villeBis;
    }

    public void setAdresseBis(String adresseBis) {
        this.adresseBis = adresseBis;
    }

    public void setTelBis(String telBis) {
        this.telBis = telBis;
    }

    public void setMailBis(String mailBis) {
        this.mailBis = mailBis;
    }

    public void setMdpBis(String mdpBis) {
        this.mdpBis = mdpBis;
    }

    @Override
    public String toString() {
        return "ClientBis{" + "idBis=" + idBis + ", nomBis=" + nomBis + ", prenomBis=" + prenomBis + ", villeBis=" + villeBis + ", adresseBis=" + adresseBis + ", telBis=" + telBis + ", mailBis=" + mailBis + ", mdpBis=" + mdpBis + '}';
    }
    
}
