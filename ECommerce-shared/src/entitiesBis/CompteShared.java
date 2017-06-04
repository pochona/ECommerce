/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesBis;

/**
 *
 * @author Amaury
 */
public class CompteShared {
    private String numCarte;
    private String crypto;
    private int id;
    private double solde;
    
    public CompteShared (String num, String crypto, int id, double solde){
        this.numCarte = num;
        this.crypto = crypto;
        this.id = id;
        this.solde = solde;
    }

    public String getNumCarte() {
        return numCarte;
    }

    public String getCrypto() {
        return crypto;
    }

    public int getId() {
        return id;
    }
    
    public double getSolde(){
        return solde;
    }
    
}
