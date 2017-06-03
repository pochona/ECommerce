/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import controllers.ClientFacadeLocal;
import controllers.CompteFacadeLocal;
import entities.Client;
import entitiesBis.ClientBis;
import exceptions.ErreurConnexionClient;
import exceptions.ExceptionClient;
import exceptions.ExceptionCreationClient;
import java.util.ArrayList;
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
    private ClientFacadeLocal clientFacade;
    
    @Override
    public long chercherClient(String nom, String prenom) throws ExceptionClient {
        return clientFacade.chercherClient(nom, prenom);
    }

    @Override
    public List<Long> listeNumComptes(long idClient) throws ExceptionClient {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long validerConnexion(String email, String mdp) throws ErreurConnexionClient {
        return clientFacade.validerConnexion(email, mdp);
    }

    @Override
    public long creerClient(String nom, String prenom, String email, String mdp, String ville, String adresse, String tel) throws ExceptionCreationClient {
        Client c = new Client();
        c.setId(null);
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMail(email);
        c.setMdp(mdp);
        c.setTel(tel);
        c.setVille(ville);
        c.setAdresse(adresse);
        try {
            clientFacade.create(c);
            c = clientFacade.findWithMail(email);
        } catch (ExceptionClient e){
            throw new ExceptionCreationClient("Creation impossible");
        }
        return c.getId();
    }
    
        @Override
    public List<ClientBis> listerClientBis() {
        List<Client> c = clientFacade.findAll();
        List<ClientBis> b = new ArrayList<ClientBis>();
        for(Client monClt : c){
            ClientBis bis = new ClientBis(monClt.getId(), monClt.getNom(), monClt.getPrenom(), monClt.getVille(), monClt.getAdresse(), monClt.getTel(), monClt.getMail(), monClt.getMdp());
            b.add(bis);
        }
        return b;
    }
    
}
