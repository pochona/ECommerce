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
public class ExceptionArticle extends Exception{
    private String erreur;
    
    public ExceptionArticle(String s){
        this.erreur = s;
    }
    
    public ExceptionArticle() {
        this.erreur = "Message non personnalisé.";
    }
    
    @Override
    public String toString(){
        return "Erreur Article : " + this.erreur;
    }
}
