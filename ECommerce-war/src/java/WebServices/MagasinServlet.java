/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import entities.Article;
import exceptions.ExceptionArticle;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

/**
 *
 * @author Amaury
 */
public class MagasinServlet extends HttpServlet {

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
        Article artAjout = null;
        Map panier = null;
        // On verifie que l'id client existe, sinon, on n'est pas connecté : on redirige
        if(idClient != null){
            // On regarde si un article est actuellement ajouté au panier
            String idAjouter = request.getParameter("art");
            
            // Si on a un article ajouté, on l'ajoute à la session
            if(idAjouter != null){
                try {
                    artAjout = gestionArticle.findArticle(Integer.parseInt(idAjouter));
                    if(session.getAttribute("panier") != null){
                        panier = (Map) session.getAttribute("panier");  
                        panier.put(artAjout.getId(), 1);
                        session.setAttribute("panier", panier);
                    } else {
                        panier = new HashMap();
                        panier.put(artAjout.getId(), 1);
                        session.setAttribute("panier", panier);
                    }
                    
                    
                } catch (ExceptionArticle ex) {
                    Logger.getLogger("Article introuvable");
                }
            } else {
                if(session.getAttribute("panier") != null){
                        panier = (Map) session.getAttribute("panier");  
                }
            }
            
            
            try {

                List<Article> listArt = gestionArticle.recupererArticle();

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
                    /*if(panier != null) {
                        
                        for(Object art : panier.entrySet()){
                            Map.Entry a = (Map.Entry) art;
                            out.println("Art : " + a.getKey() + " / quantité : " + a.getValue());
                        }
                    }*/
                    out.println("<div class='container'>");
                    out.println("<ul class='navbar-perso' style='margin-bottom: 20px;'>"
                            + "<li class='active'><form method='post' action='/ECommerce-war/MagasinServlet'><button type='submit'>Magasin</button></form></li>"
                            + "<li class='nav-right'><form method='post' action='/ECommerce-war/AuthentificationServlet'><button name='type' value='deconnexionClient' type='submit'>Déconnexion ("+idClient+")</button></form></li>"
                            + "<li class='nav-right'><form method='post' action='/ECommerce-war/PanierServlet'><button type='submit'>Panier</button></form></li>"
                            + "<li class='nav-right'><form method='post' action='/ECommerce-war/SuiviCommandeServlet'><button type='submit'>Suivi de commande</button></form></li>"
                        + "</ul>");
                    if(idAjouter != null){
                        out.println("<div class='row'><div class='col-md-12'><div class='alert alert-success'>Vous venez d'ajouter : "+artAjout.getLib()+"</div></div></div>"); 
                    }
                    for (Article monArt : listArt) {
                        out.println("<div class='row'><div class='col-md-12'>");
                        out.println("<div class='panel panel-default'>");
                        out.println("<div class='panel-heading'>"+monArt.getLib()+"</div>");
                        out.println("<div class='panel-body'>");
                        out.println("<div class='article-prix'>Prix Hors Taxe : "+monArt.getPrixHt()+" euros</div>");
                        out.println("<div class='article-prix'>Prix TTC : "+(Math.round(monArt.getPrixHt()*(1+monArt.getTauxTva()) * 100.0) / 100.0)+" euros</div>");
                        out.println("</div>");
                        out.println("<div class='panel-footer'>");
                        if(panier != null){
                            if(panier.containsKey(monArt.getId())){
                                out.println("<span class='help-block'>Déjà dans le panier</span>");
                            } else {
                                out.println("<form action='/ECommerce-war/MagasinServlet' method='post'>"
                                        +"<input name='art' value='"+monArt.getId()+"' style='display:none' />"
                                        + "<input class='btn btn-success' type='submit' name='Ajouter' value='Ajouter' /></form>");
                            }
                        } else {
                            out.println("<form action='/ECommerce-war/MagasinServlet' method='post'>"
                                        +"<input name='art' value='"+monArt.getId()+"' style='display:none' />"
                                        + "<input class='btn btn-success' type='submit' name='Ajouter' value='Ajouter' /></form>");
                        }
                        out.println("</div>");
                        out.println("</div>");// close panel-defaut
                        out.println("</div></div>"); // close col-md / row
                    }
                    out.println("</div>"); // close container
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (ExceptionArticle e) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Magasin</title>"); 
                    out.println("<link rel='stylesheet' type='text/css' href='./css/style.css'>");
                    out.println("<link rel='stylesheet' type='text/css' href='./css/bootstrap.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("Erreur dans la selection des articles.");
                    out.println("</body>");
                    out.println("</html>");
                }
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
