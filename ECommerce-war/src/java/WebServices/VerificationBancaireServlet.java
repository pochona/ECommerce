/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import entitiesBis.CompteShared;
import exceptions.ErreurConnexionClient;
import exceptions.ExceptionBancaire;
import exceptions.ExceptionClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.ServiceBanqueRemote;

/**
 *
 * @author Amaury
 */
public class VerificationBancaireServlet extends HttpServlet {

    
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
        
        String numCarte = request.getParameter("numCarte");
        String numCrypto = request.getParameter("numCrypto");
        CompteShared c = null;
        boolean verif = false;
        
        // On regarde si le numéro de compte saisie n'est pas null 
        if(numCrypto != null && !"".equals(numCrypto) && numCarte != null && !"".equals(numCarte)){
            try {
                c = serviceBanque.validerCoordonnees(numCarte, numCrypto);
                verif = true;
            } catch (ExceptionBancaire ex) {
                verif = false;
            }
        }
        
        if(verif){
        // Coordonnées bancaires ok :
        // On enregistre la commande, et on vide le panier
        }


        try (PrintWriter out = response.getWriter()) {
            response.setHeader("Refresh", "3;url=/ECommerce-war/MagasinServlet");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vérification bancaire</title>"); 
            out.println("<link rel='stylesheet' type='text/css' href='./css/style.css'>");
            out.println("<link rel='stylesheet' type='text/css' href='./css/bootstrap.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'><div class='row'>");
            out.println("<div class='col-md-12'><img src='./img/banniere.jpg' alt='Banniere'></div>");
            if(verif){
                response.setHeader("Refresh", "3;url=/ECommerce-war/MagasinServlet");
                out.println("<div class='col-md-12'><h1>Coordonnées validées</h1></div>");
                out.println("num compte: " + c.getId());
                out.println("<div class='col-md-12'><h3>Votre commande est en attente de prélèvement.</h3></div>");
                out.println("<div class='col-md-12'><p>Attendez 3 secondes, ou cliquez sur le bouton suivant si la redirection ne fonctionne pas<p></div>");
                out.println("<form action='/ECommerce-war/MagasinServlet' method='post'>"
                    + "<div class='col-md-12'><input class='btn btn-success' type='submit' name='Continuez' value='Retour au magasin' /></div>"+
                    "</form>");
            } else {
                response.setHeader("Refresh", "3;url=/ECommerce-war/PasserCommandeServlet");
                out.println("<div class='col-md-12'><h1>Coordonnées invalides</h1></div>");
                out.println("<div class='col-md-12'><h3>Veuillez resaisir vos coordonnées.</h3></div>");
                out.println("<div class='col-md-12'><p>Attendez 3 secondes, ou cliquez sur le bouton suivant si la redirection ne fonctionne pas.<p></div>");
                out.println("<form action='/ECommerce-war/PasserCommandeServlet' method='post'>"
                    + "<div class='col-md-12'><input class='btn btn-default' type='submit' name='retour' value='Retour' /></div>"+
                    "</form>");
            }
            out.println("</div></div>");
            out.println("</body>");
            out.println("</html>");
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
