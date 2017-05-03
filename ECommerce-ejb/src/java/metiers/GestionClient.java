/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import controllers.ClientFacadeLocal;
import controllers.CompteFacadeLocal;
import exceptions.ExceptionClient;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Amaury
 */
@Stateless
public class GestionClient implements GestionClientLocal {

    @EJB
    private CompteFacadeLocal compteFacade;

    @EJB
    private ClientFacadeLocal clientFacade;
    
    @Override
    public long creerClient(String nom, String prenom) throws ExceptionClient {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long chercherClient(String nom, String prenom) throws ExceptionClient {
        return clientFacade.chercherClient(nom, prenom);
    }

    @Override
    public List<Long> listeNumComptes(long idClient) throws ExceptionClient {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long creerCompte(long idClient) throws ExceptionClient {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
