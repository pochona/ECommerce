/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amaury
 */
@Entity
@Table(name = "ligne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ligne.findAll", query = "SELECT l FROM Ligne l")
    , @NamedQuery(name = "Ligne.findById", query = "SELECT l FROM Ligne l WHERE l.id = :id")
    , @NamedQuery(name = "Ligne.findByIdArticle", query = "SELECT l FROM Ligne l WHERE l.idArticle = :idArticle")
    , @NamedQuery(name = "Ligne.findByIdCommande", query = "SELECT l FROM Ligne l WHERE l.idCommande = :idCommande")
    , @NamedQuery(name = "Ligne.findByQte", query = "SELECT l FROM Ligne l WHERE l.qte = :qte")})
public class Ligne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ARTICLE")
    private int idArticle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COMMANDE")
    private int idCommande;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTE")
    private int qte;

    public Ligne() {
    }

    public Ligne(Integer id) {
        this.id = id;
    }

    public Ligne(Integer id, int idArticle, int idCommande, int qte) {
        this.id = id;
        this.idArticle = idArticle;
        this.idCommande = idCommande;
        this.qte = qte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ligne)) {
            return false;
        }
        Ligne other = (Ligne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ligne[ id=" + id + " ]";
    }
    
}
