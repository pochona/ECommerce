/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import entities.Commande;
import entities.Ligne;
import entitiesBis.CompteShared;
import exceptions.ExceptionBancaire;
import exceptions.ExceptionCommande;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionCommandeLocal;
import services.ServiceBanqueRemote;

/**
 *
 * @author Amaury
 */
public class VerificationBancaireServlet extends HttpServlet {

    
    @EJB
    private ServiceBanqueRemote serviceBanque;
    
    @EJB
    private GestionCommandeLocal gestionCommande;
     
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
        Integer idClient = ((Long) session.getAttribute("idClient")).intValue();
        Map panier = null;
        // On verifie que l'id client existe, sinon, on n'est pas connecté : on redirige
        if(idClient != null){

            String numCarte = request.getParameter("numCarte");
            String numCrypto = request.getParameter("numCrypto");
            
            panier = (Map) session.getAttribute("panier");  
            CompteShared compte = null;
            Commande commande = null;
            Ligne ligne = null;
            boolean verif = false;

            // On regarde si le numéro de compte saisie n'est pas null 
            if(numCrypto != null && !"".equals(numCrypto) && numCarte != null && !"".equals(numCarte)){
                try {
                    compte = serviceBanque.validerCoordonnees(numCarte, numCrypto);
                    verif = true;
                } catch (ExceptionBancaire ex) {
                    verif = false;
                }
            }

            if(verif){
                try {
                    // insertion de la commande
                    commande = gestionCommande.creerCommande(idClient, compte.getId());
                    
                    // insertion des articles
                    for(Object art : panier.entrySet()){
                        Map.Entry a = (Map.Entry) art;
                        Integer idArticle = (Integer) a.getKey();
                        Integer quantity = (Integer) a.getValue();
                        ligne = gestionCommande.creerLigne(idArticle, commande.getId(), quantity);
                    } // fin du for
                    
                    // mise à zero du panier
                    session.removeAttribute("panier");
                } catch (ExceptionCommande ex) {
                    Logger.getLogger(VerificationBancaireServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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
