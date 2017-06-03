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
public class ExceptionClient extends Exception {
    private String erreur;
    
    public ExceptionClient(String s){
        this.erreur = s;
    }
    
    @Override
    public String toString(){
        return "Erreur Client : " + this.erreur;
    }
}
