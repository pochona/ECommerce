/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import controllers.CommandeFacadeLocal;
import controllers.LigneFacadeLocal;
import controllers.StatutFacadeLocal;
import entities.Commande;
import entities.Ligne;
import entities.Statut;
import entitiesBis.ArticleBis;
import entitiesBis.CommandeBis;
import exceptions.ExceptionCommande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import utilities.Log;

/**
 *
 * @author Laura
 */
@Stateless
public class GestionCommande implements GestionCommandeLocal {

    @EJB
    private CommandeFacadeLocal commandeFacade;

    @EJB
    private StatutFacadeLocal statutFacade;

    @EJB
    private LigneFacadeLocal ligneFacade;

    @Override
    public List<Commande> recupererCommandes() throws ExceptionCommande {
        return commandeFacade.findAll();
    }

    @Override
    public Commande findCommande(Integer id) throws ExceptionCommande {
        return commandeFacade.find(id);
    }

    @Override
    public List<Commande> findCommandesClient(Integer idClient) throws ExceptionCommande {
        return commandeFacade.findCommandesClient(idClient);
    }

    @Override
    public Statut findStatut(Integer id) throws ExceptionCommande {
        return statutFacade.find(id);
    }

    /*@Override
    public int findIdComByClient(int idClient) throws ExceptionCommande{
        return commandeFacade.findIdComByClient(idClient);
    }*/
    @Override
    public List<Ligne> getLigneCommande(Integer idCommande) throws ExceptionCommande {
        return ligneFacade.findByIdCommande(idCommande);
    }

    @Override
    public Commande creerCommande(Integer idClient, Integer idCompte) throws ExceptionCommande {
        Commande c = new Commande();
        c.setIdClient(idClient);
        c.setIdCompte(idCompte);
        c.setIdStatut(1);
        c.setDateCommande(new Date());
        commandeFacade.create(c);
        return c;
    }

    @Override
    public Ligne creerLigne(Integer idArticle, Integer idCommande, Integer qte) throws ExceptionCommande {
        Ligne l = new Ligne();
        l.setIdArticle(idArticle);
        l.setIdCommande(idCommande);
        l.setQte(qte);

        ligneFacade.create(l);
        return l;
    }

    @Override
    public List<CommandeBis> listerCommandeBis() {
        List <Commande> c = commandeFacade.findAll();
        List<CommandeBis> b = new ArrayList<CommandeBis>();
        for (Commande maCom : c) {
            CommandeBis bis = null;
            try {
                bis = new CommandeBis(maCom.getId(), maCom.getDateCommande(), maCom.getIdClient(), maCom.getIdTournee(), maCom.getIdStatut());
            } catch (NullPointerException e) {
                bis = new CommandeBis(maCom.getId(), maCom.getDateCommande(), maCom.getIdClient(), maCom.getIdStatut());
            }
            b.add(bis);
        }
        return b;
    }

}
