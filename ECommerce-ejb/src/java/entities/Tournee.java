/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amaury
 */
@Entity
@Table(name = "tournee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tournee.findAll", query = "SELECT t FROM Tournee t")
    , @NamedQuery(name = "Tournee.findById", query = "SELECT t FROM Tournee t WHERE t.id = :id")
    , @NamedQuery(name = "Tournee.findByDateTournee", query = "SELECT t FROM Tournee t WHERE t.dateTournee = :dateTournee")})
public class Tournee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_TOURNEE")
    @Temporal(TemporalType.DATE)
    private Date dateTournee;

    public Tournee() {
    }

    public Tournee(Integer id) {
        this.id = id;
    }

    public Tournee(Integer id, Date dateTournee) {
        this.id = id;
        this.dateTournee = dateTournee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTournee() {
        return dateTournee;
    }

    public void setDateTournee(Date dateTournee) {
        this.dateTournee = dateTournee;
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
        if (!(object instanceof Tournee)) {
            return false;
        }
        Tournee other = (Tournee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tournee[ id=" + id + " ]";
    }
    
}
