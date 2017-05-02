/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import exceptions.ExceptionClient;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import metiers.GestionClientLocal;

/**
 *
 * @author Amaury
 */
@WebService(serviceName = "ClientService")
public class ClientService {

     @EJB
    private GestionClientLocal monEjb;
     
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "chercherClient")
    public long chercherClient(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom) throws ExceptionClient {
        return monEjb.chercherClient(nom, prenom);
    }
}
