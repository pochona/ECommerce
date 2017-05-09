/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import entities.Article;
import exceptions.ErreurConnexionClient;
import exceptions.ExceptionArticle;
import exceptions.ExceptionClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
        List panier = null;
        // On verifie que l'id client existe, sinon, on n'est pas connecté : on redirige
        if(idClient != null){
            // On regarde si un article est actuellement ajouté au panier
            String idAjouter = request.getParameter("art");
            
            // Si on a un article ajouté, on l'ajoute à la session
            if(idAjouter != null){
                try {
                    artAjout = gestionArticle.findArticle(Integer.parseInt(idAjouter));
                    if(session.getAttribute("panier") != null){
                        panier = (List) session.getAttribute("panier");  
                        panier.add(artAjout.getId());
                        session.setAttribute("panier", panier);
                    } else {
                        panier = new ArrayList();
                        panier.add(artAjout.getId());
                        session.setAttribute("panier", panier);
                    }
                    
                    
                } catch (ExceptionArticle ex) {
                    Logger.getLogger("Article introuvable");
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
                    out.println("</head>");
                    out.println("<body>");
                    if(panier != null) {
                        for(Object art : panier){
                            out.println("Art : " + art);
                        }
                    }
                    out.println("<div style='float: right; font-size: 12px'>Connecté ("+idClient+")</div>");
                    if(idAjouter != null){
                        out.println("<div class='recap-ajout' style='border: solid 1px #EEEEEE; font-size: 20px; margin-bottom: 20px;'>Vous venez d'ajouter : "+artAjout.getLib()+"</div>"); 
                    }
                    for (Article monArt : listArt) {
                        out.println("<div class='article_pre' style='border: solid 1px #DDDDDD; width: 80%; float: left; margin-bottom: 20px;'");
                        out.println("<span class='article-lib'>Article : "+monArt.getLib()+"</span>");
                        out.println("<hr />");
                        out.println("<div class='article-des'>"+monArt.getDescription()+"</div>");
                        out.println("<hr />");
                        out.println("<div class='article-prix'>Prix Hors Taxe : "+monArt.getPrixHt()+" euros</div>");
                        out.println("<div class='article-prix'>Prix TTC : "+(Math.round(monArt.getPrixHt()*(1+monArt.getTauxTva()) * 100.0) / 100.0)+" euros</div>");
                        out.println("<hr />");
                        out.println("<div class='article-action' style='float:right;'>");
                        out.println("<form action='/ECommerce-war/MagasinServlet' method='post'>"
                                +"<input name='art' value='"+monArt.getId()+"' style='display:none' />" 
                            + "<input type='submit' name='Ajouter' value='Ajouter' />"+
                            "</form>");
                        out.println("</div>");
                        out.println("</div>");
                    }
                    
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (ExceptionArticle e) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet ClientServlet</title>");      
                    out.println("</head>");
                    out.println("<body>");
                    out.println("Erreur dans la selection des articles.");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } else {
            // Client non identifié 
            try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet ClientServlet</title>");     
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