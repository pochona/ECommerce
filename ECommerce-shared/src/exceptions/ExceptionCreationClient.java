/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Amaury
 */
public class ExceptionCreationClient extends Exception {
    String erreur;
    
    public ExceptionCreationClient(String s){
        this.erreur = s;
    }
    
    public ExceptionCreationClient() {
        this.erreur = "Message non personnalisé.";
    }
    
    public String toString(){
        return "Erreur Creation Client : " + this.erreur;
    }
}
