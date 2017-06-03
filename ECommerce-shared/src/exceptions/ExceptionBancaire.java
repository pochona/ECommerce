package exceptions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amaury
 */
public class ExceptionBancaire extends Exception{
    private String erreur;
    
    public ExceptionBancaire(String s){
        this.erreur = s;
    }
    
    @Override
    public String toString(){
        return "Erreur Bancaire : " + this.erreur;
    }
}
