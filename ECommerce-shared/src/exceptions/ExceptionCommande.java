package exceptions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura
 */
public class ExceptionCommande extends Exception{
    private String erreur;
    
    public ExceptionCommande(String s){
        this.erreur = s;
    }
    
    public ExceptionCommande() {
        this.erreur = "Message non personnalisé.";
    }
    
    @Override
    public String toString(){
        return "Erreur Commande : " + this.erreur;
    }
}
