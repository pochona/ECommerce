/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import entities.Article;
import exceptions.ExceptionArticle;
import exceptions.ExceptionClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionArticleLocal;
import metiers.GestionClientLocal;

/**
 *
 * @author Amaury
 */
public class PanierServlet extends HttpServlet {

    
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
        Long idClient = (Long) session.getAttribute("idClient");
        Map panier = null;
        // On verifie que l'id client existe, sinon, on n'est pas connecté : on redirige
        if(idClient != null){
            panier = (Map) session.getAttribute("panier");  
            
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
                out.println("<ul class='navbar-perso'>"
                        + "<li class='active'><form method='get' action='/ECommerce-war/MagasinServlet'><button type='submit'>Magasin</button></form></li>"
                        + "<li class='nav-right'><form method='get' action='/ECommerce-war/AuthentificationServlet'><button name='type' value='deconnexionClient' type='submit'>Déconnexion ("+idClient+")</button></form></li>"
                        + "<li class='nav-right'><form method='get' action='/ECommerce-war/PanierServlet'><button type='submit'>Panier</button></form></li>"
                        + "<li class='nav-right'><form method='get' action='./index.html'><button type='submit'>Suivi de commande</button></form></li>"
                    + "</ul>");
                if(panier != null) {
                    for(Object art : panier.entrySet()){
                        Map.Entry a = (Map.Entry) art;
                        Article monArt = null;
                        try {
                            Integer idArt = (Integer) a.getKey();
                            monArt = gestionArticle.findArticle(idArt);
                        } catch (ExceptionArticle ex) {
                            Logger.getLogger("Article introuvable");
                        }
                        out.println("<div class='col-md-12'>");
                        out.println("<div class='panel panel-default'>");
                        out.println("<div class='panel-heading'>"+monArt.getLib());
                        out.println("<span class='pull-right'>Quantité :"+a.getValue()+"</span>");
                        out.println("</div>");
                        out.println("<div class='panel-body'>");
                        out.println("<div class='article-prix'>Prix Hors Taxe : "+monArt.getPrixHt()+" euros</div>");
                        out.println("<div class='article-prix'>Prix TTC : "+(Math.round(monArt.getPrixHt()*(1+monArt.getTauxTva()) * 100.0) / 100.0)+" euros</div>");
                        out.println("</div>");
                        out.println("</div>");// close panel-defaut
                        out.println("</div>"); // close col-md
                    }
                    out.println("<div class='col-md-12'>");
                    out.println("<form method='get' action='/ECommerce-war/MagasinServlet'><button type='submit'>Passer la commande (non fonctionnel)</button></form>");
                    out.println("</div>");
                } else {
                    out.println("<div class='col-md-12'>");
                    out.println("<p>Votre panier est actuellement vide.</p>");
                    out.println("</div>");
                }
                out.println("</div>"); // close container
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // Client non identifié 
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
                    out.println("<h1>Vous devez être connecté pour accéder au magasin</h1>");
                   out.println("<form method='get' action='./index.html'><button type='submit'>Retour à la connexion</button></form>");
                    out.println("</body>");
                    out.println("</html>");
                }
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
