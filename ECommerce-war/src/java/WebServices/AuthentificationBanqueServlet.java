/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;


import entitiesBis.CompteShared;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.ServiceBanque;
import services.ServiceBanqueRemote;

/**
 *
 * @author Amaury
 */
public class AuthentificationBanqueServlet extends HttpServlet {

    
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
        
        String type = request.getParameter("type");

        if(type.equals("connexionClient")) {
            String compte = request.getParameter("numCpt");
            String crypto = request.getParameter("crypto");
            try {
             CompteShared cpt = serviceBanque.validerCoordonnees(compte, crypto);
             HttpSession session = request.getSession();
             session.setAttribute("idCompte", cpt.getId());
                try (PrintWriter out = response.getWriter()) {
                    response.setHeader("Refresh", "3;url=/ECommerce-war/CompteBanqueServlet");
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Authentification</title>"); 
                    out.println("<link rel='stylesheet' type='text/css' href='./css/style.css'>");
                    out.println("<link rel='stylesheet' type='text/css' href='./css/bootstrap.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='container'><div class='row'>");
                    out.println("<div class='col-md-12'><img src='./img/banniereBanque.jpg' alt='Banniere'></div>");
                    out.println("<div class='col-md-12'><h1>Vous êtes bien connecté !</h1></div>");
                    out.println("<div class='col-md-12'><p>Attendez 3 secondes, ou cliquez sur le bouton suivant si la redirection ne fonctionne pas<p></div>");
                    out.println("<form action='/ECommerce-war/CompteBanqueServlet' method='post'>"
                            + "<div class='col-md-12'><input class='btn btn-success' type='submit' name='Continuez' value='Continuer sur votre compte.' /></div>"+
                            "</form>");
                    out.println("</div></div>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (exceptions.ExceptionBancaire ex) {
                //Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
                try (PrintWriter out = response.getWriter()) {
                    response.setHeader("Refresh", "3;url=/ECommerce-war/banque/index.html");
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Authentification</title>");  
                    out.println("<link rel='stylesheet' type='text/css' href='./css/style.css'>");
                    out.println("<link rel='stylesheet' type='text/css' href='./css/bootstrap.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='container'><div class='row'>");
                    out.println("<div class='col-md-12'><img src='./img/banniereBanque.jpg' alt='Banniere'></div>");
                    out.println("<div class='col-md-12'><h1>Mauvaise combinaison</h1></div>");
                    out.println("<div class='col-md-12'><p>Attendez 3 secondes, ou cliquez sur le bouton suivant si la redirection ne fonctionne pas<p></div>");
                    out.println("<form method='get' action='./banque/index.html'><button class='btn btn-info' type='submit'>Retour</button></form>");
                    out.println("</div></div>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } else if(type.equals("deconnexionClient")){
            HttpSession session = request.getSession();
            session.setAttribute("idCompte", null);
            try (PrintWriter out = response.getWriter()) {
                response.setHeader("Refresh", "3;url=/ECommerce-war/banque/index.html");
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Authentification</title>"); 
                out.println("<link rel='stylesheet' type='text/css' href='./css/style.css'>");
                out.println("<link rel='stylesheet' type='text/css' href='./css/bootstrap.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'><div class='row'>");
                out.println("<div class='col-md-12'><img src='./img/banniereBanque.jpg' alt='Banniere'></div>");
                out.println("<div class='col-md-12'><h1>Vous êtes déconnecté.</h1></div>");
                out.println("<div class='col-md-12'><p>Attendez 3 secondes, ou cliquez sur le bouton suivant si la redirection ne fonctionne pas<p></div>");
                out.println("<form method='get' action='./banque/index.html'><button class='btn btn-info' type='submit'>Retour à l'accueil</button></form>");
                out.println("</div></div>");    
                out.println("</body>");
                out.println("</html>");
            }
        } else { 
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Authentification</title>"); 
                out.println("<link rel='stylesheet' type='text/css' href='./css/style.css'>");
                out.println("<link rel='stylesheet' type='text/css' href='./css/bootstrap.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'><div class='row'>");
                out.println("<div class='col-md-12'><img src='./img/banniereBanque.jpg' alt='Banniere'></div>");
                out.println("<div class='col-md-12'><h1>Aucun choix effectué</h1></div>");
                out.println("<form method='get' action='./banque/index.html'><button class='btn btn-info' type='submit'>Retour</button></form>");
                out.println("</div></div>");    
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
