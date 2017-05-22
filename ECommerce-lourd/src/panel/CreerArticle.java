/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import exceptions.ExceptionArticle;
import fenetre.Fenetre;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import services.ServiceCommercialRemote;

/**
 *
 * @author Amaury_PC
 */
public class CreerArticle extends JPanel {

    Fenetre maFenetre;

    public CreerArticle(Fenetre maFenetre) {

        this.maFenetre = maFenetre;

        JButton valider = new JButton("Valider");
        
        this.setLayout(new FlowLayout());

        //String id,lib,des,px,tx,stk;
        JTextField articleID = new JTextField("999");
        articleID.setPreferredSize(new Dimension(380, 30));
        JTextField articleLib = new JTextField("Libellé");
        articleLib.setPreferredSize(new Dimension(550, 30));
        JTextField articleDes = new JTextField("Description");
        articleDes.setPreferredSize(new Dimension(550, 30));
        JTextField articlePrix = new JTextField("99.99");
        articlePrix.setPreferredSize(new Dimension(100, 30));
        JTextField articleTaux = new JTextField("0.2");
        articleTaux.setPreferredSize(new Dimension(100, 30));
        JTextField articleStock = new JTextField("0");
        articleStock.setPreferredSize(new Dimension(100, 30));
        JLabel JLtitre = new JLabel("||  CREATION D'UN NOUVEL ARTICLE  ||");
        JLabel JLid = new JLabel("Reférence : ");
        JLabel JLlib = new JLabel("Libellé : ");
        JLabel JLdes = new JLabel("Description : ");
        JLabel JLprix = new JLabel("Prix Hors Taxe : ");
        JLabel JLtaux = new JLabel("Taux TVA : ");
        JLabel JLstock = new JLabel("Stock : ");

        this.add(JLtitre);
        this.add(JLid);
        this.add(articleID);
        this.add(JLlib);
        this.add(articleLib);
        this.add(JLdes);
        this.add(articleDes);
        this.add(JLprix);
        this.add(articlePrix);
        this.add(JLtaux);
        this.add(articleTaux);
        this.add(JLstock);
        this.add(articleStock);
        this.add(valider);

        /*id=articleID.getText();
        lib=articleLib.getText();
        des=articleDes.getText();
        px=articlePrix.getText();
        tx=articleTaux.getText();
        stk=articleStock.getText();*/
 /*
        String art = id+","+lib+","+des+","+px+","+tx+","+stk;
        System.out.println(art+"PROBLEME");*/
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id, lib, des, px, tx, stk;
                    boolean entreeOK = false;
                    Integer id2;
                    String lib2, des2;
                    double prixHt2;
                    float tauxTva2;
                    int stock2;
                    id = articleID.getText();
                    lib = articleLib.getText();
                    des = articleDes.getText();
                    px = articlePrix.getText();
                    tx = articleTaux.getText();
                    stk = articleStock.getText();
                    String art = id + "," + lib + "," + des + "," + px + "," + tx + "," + stk;
                    id2 = Integer.parseInt(id);
                    prixHt2 = Double.parseDouble(px);
                    tauxTva2 = Float.parseFloat(tx);
                    stock2 = Integer.parseInt(stk);
                    maFenetre.getServiceCommercial().creer(art);
                } catch (NumberFormatException exc) {
                    System.out.println("PROBLEME DE TYPE222");
                    JOptionPane JOP;
                    JOP = new JOptionPane();
                    JOP.showMessageDialog(null, "Veuillez vérifier les types renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (ExceptionArticle ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        );
System.out.println("creer produit");
    }
}