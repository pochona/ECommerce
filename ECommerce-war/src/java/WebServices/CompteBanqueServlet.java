/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import entities.Article;
import entitiesBis.CompteShared;
import exceptions.ExceptionArticle;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionArticleLocal;
import services.ServiceBanqueRemote;

/**
 *
 * @author Amaury
 */
public class CompteBanqueServlet extends HttpServlet {

    
    @EJB
    private ServiceBanqueRemote serviceBanque;
     
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
        Integer idCompte = (Integer) session.getAttribute("idCompte");
        boolean alert = false;
        boolean ajout = false;
        // On verifie que l'id client existe, sinon, on n'est pas connecté : on redirige
        if(idCompte != null){
            
            // On traite le crédit / débit
            // On regarde si un article est actuellement ajouté au panier
            try {
                double montantDebit = request.getParameter("montantDedit") != null ? Double.parseDouble(request.getParameter("montantDedit")) : 0;
                double montantCredit = request.getParameter("montantCredit") != null ? Double.parseDouble(request.getParameter("montantCredit")) : 0;
                
                // Si on a un article ajouté, on l'ajoute à la session
                if(montantDebit != 0){
                    serviceBanque.debiterCompte(idCompte, montantDebit);
                    ajout = true;
                } else if(montantCredit != 0){
                    serviceBanque.crediterCompte(idCompte, montantCredit);
                    ajout = true;
                }
                
            } catch (NumberFormatException e){
                alert = true;
            }
            

            CompteShared cpt = serviceBanque.findCompteById(idCompte);
            
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Compte</title>"); 
                out.println("<link rel='stylesheet' type='text/css' href='./css/style.css'>");
                out.println("<link rel='stylesheet' type='text/css' href='./css/bootstrap.css'>");
                out.println("<script src='./js/jquery.js'></script>");
                out.println("<script src='./js/script.js'></script>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<div class='row'><div class='col-md-12'>"
                        + "<h1><form method='post' action='/ECommerce-war/AuthentificationBanqueServlet'><button name='type' class='pull-right btn btn-danger' value='deconnexionClient' type='submit'>Déconnexion ("+idCompte+")</button></form>Détail de votre compte</h1></div></div>");
                if(alert){
                    out.println("<div class='row'><div class='col-md-12'><div class='alert alert-danger'>Erreur de format</div></div></div>"); 
                } else if (ajout) {
                    out.println("<div class='row'><div class='col-md-12'><div class='alert alert-success'>Opération validée</div></div></div>"); 
                }
                
                out.println("<div class='row'>");
                // recap du panier
                out.println("<div class='col-md-12'>");
                out.println("<div class='panel panel-default'>");
                out.println("<div class='panel-heading'><h3>Solde actuel sur le compte : "+(Math.round(cpt.getSolde() * 100.0) / 100.0)+"</h3>");
                out.println("<span class='btn btn-default' id='btn-crediter'>Crediter</span>");
                out.println("<span class='btn btn-default' id='btn-debiter'>Debiter</span>");
                out.println("</div>");
                out.println("<div class='panel-body'>");
                // Form crediter
                out.println("<div id='form-crediter' style='display: none'><form  id='form-crediter' action='/ECommerce-war/CompteBanqueServlet' method='post'>");
                out.println("<h3>Créditer</h3>");
                out.println("<input type='text' class='form-control' id='montantCredit' name='montantCredit' placeholder='€'>");
                out.println("<button class='btn btn-success' type='submit'>Créditer</button>");
                out.println("</form></div>");
                
                // Former debiter
                out.println("<div id='form-dediter' style='display: none'><form  id='form-debiter' action='/ECommerce-war/CompteBanqueServlet' method='post'>");
                out.println("<h3>Déditer</h3>");
                out.println("<input type='text' class='form-control' id='montantDedit' name='montantDedit' placeholder='€'>");
                out.println("<button class='btn btn-danger' type='submit'>Déditer</button>");
                out.println("</form></div>");
                
                out.println("</div>"); // fin du panel body
                out.println("</div>");// close panel-defaut
                out.println("</div>"); // close col-md
                out.println("</div>"); // close row
                out.println("</div>"); // close container
                out.println("</body>");
                out.println("</html>");
            }
        } else {
           response.setHeader("Refresh", "0;url=/ECommerce-war/banque/erreur.html");
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
