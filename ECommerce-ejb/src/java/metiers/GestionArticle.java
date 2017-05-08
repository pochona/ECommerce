/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import controllers.ArticleFacadeLocal;
import exceptions.ErreurConnexionClient;
import exceptions.ExceptionArticle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Laura
 */

@Stateless
public class GestionArticle implements GestionArticleLocal{
    
    @EJB
    private ArticleFacadeLocal articleFacade;

    @Override
    public long creerArticle(String description, String lib) throws ExceptionArticle {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String chercherArticle(String description, String lib) throws ExceptionArticle {
    return articleFacade.chercherArticle(description, lib);
    }

    
}


    
    
    


