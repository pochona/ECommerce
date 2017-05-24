/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;


import entities.Article;
import entities.Commande;
import entities.Ligne;
import exceptions.ExceptionArticle;
import exceptions.ExceptionCommande;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionArticleLocal;
import metiers.GestionCommandeLocal;

/**
 *
 * @author Amaury
 */
public class SuiviCommandeServlet extends HttpServlet {

    
    @EJB
    private GestionCommandeLocal gestionCommande;
     
    
    @EJB
    private GestionArticleLocal gestionArticle;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Long idClientL = (Long) session.getAttribute("idClient");
        Integer idClient = idClientL.intValue();
        List<Commande> listCommande = null;
        double prixTotUnitaire = 0;
        double prixTot = 0;
        double montantTot = 0;
        // On verifie que l'id client existe, sinon, on n'est pas connecté : on redirige
        if(idClient != null){
            
            try {
                listCommande = gestionCommande.findCommandesClient(idClient);
            } catch (ExceptionCommande ex) {
                //listCommande = null;
            }
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Magasin</title>");      
                out.println("<link rel='stylesheet' type='text/css' href='./css/style.css'>");
                out.println("<link rel='stylesheet' type='text/css' href='./css/bootstrap.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<ul class='navbar-perso' style='margin-bottom: 20px;'>"
                        + "<li><form method='get' action='/ECommerce-war/MagasinServlet'><button type='submit'>Magasin</button></form></li>"
                        + "<li class='nav-right'><form method='post' action='/ECommerce-war/AuthentificationServlet'><button name='type' value='deconnexionClient' type='submit'>Déconnexion ("+idClient+")</button></form></li>"
                        + "<li class='nav-right'><form method='post' action='/ECommerce-war/PanierServlet'><button type='submit'>Panier</button></form></li>"
                        + "<li class='active nav-right'><form method='post' action='/ECommerce-war/SuiviCommandeServlet'><button type='submit'>Suivi de commande</button></form></li>"
                    + "</ul>");
                if(listCommande.size() != 0){
                    for (Commande laComm : listCommande) {
                        List<Ligne> ligneComm = gestionCommande.getLigneCommande(laComm.getId());
                        
                        out.println("<div class='row'><div class='col-md-12'>");
                        out.println("<div class='panel panel-default'>");
                        out.println("<div class='panel-heading'>Commande n°"+laComm.getId()+" - "+laComm.getDateCommande()+"</div>");
                        out.println("<div class='panel-body'>");
                        out.println("<ul class='list-group'>");
                        for (Ligne maLigne : ligneComm) {
                            Article monArt = gestionArticle.findArticle(maLigne.getIdArticle());
                            prixTotUnitaire = (Math.round(monArt.getPrixHt()*(1+monArt.getTauxTva()) * 100.0) / 100.0);
                            prixTot = prixTotUnitaire * maLigne.getQte();
                            montantTot = montantTot + prixTot;
                            
                            out.println("<li class='list-group-item'>"+monArt.getLib()+" - Quantité : "+maLigne.getQte()+"<span class='badge'>"+prixTot+" euros</span></li>");
                        }
                        montantTot = Math.round(montantTot*100.0)/100.0;
                        out.println("</ul>");
                        out.println("<div class='pull-right'>Montant de la commande : "+montantTot+"</div>");
                        out.println("</div>");
                        out.println("<div class='panel-footer'><div>");
                        out.println("<span class='label label-info'>");
                        try {
                            out.println(gestionCommande.findStatut(laComm.getIdStatut()).getLib());
                        } catch (ExceptionCommande ex) {
                            out.println("Statut inconnu");
                        }
                        out.println("</span>");
                        out.println("<span class='pull-right'>");
                        out.println("<form action='/ECommerce-war/DetailCommandeServlet' method='post'>"
                                        + "<input name='comm' value='"+laComm.getId()+"' style='display:none' />"
                                        + "</form>");
                        out.println("</span>");
                        out.println("</div></div>"); // close footer
                        out.println("</div>");// close panel-defaut
                        out.println("</div></div>"); // close col-md / row
                    }
                } else {
                    out.println("<div class='row'><div class='col-md-12'><div class='alert alert-danger'>Vous n'avez passé aucune commande</div></div></div>");
                }
                out.println("</div>"); // close container
                out.println("</body>");
                out.println("</html>");
            } catch (ExceptionArticle ex) {
                Logger.getLogger(SuiviCommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExceptionCommande ex) {
                Logger.getLogger(SuiviCommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
           response.setHeader("Refresh", "0;url=/ECommerce-war/erreur.html");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
