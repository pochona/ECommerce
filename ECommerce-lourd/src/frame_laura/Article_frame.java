/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame_laura;

/**
 *
 * @author Laura
 */

import exceptions.ExceptionArticle;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;





public class Article_frame extends JFrame implements ListSelectionListener {
    

//@EJB
//private GestionArticleLocal gestionArticle;
    
JButton bouton = new JButton("Valider");
JList liste = new JList();
JLabel etiquette = new JLabel(" ");


    public Article_frame() {
        
        initFrame();
    }
    
    public void initFrame() {
    
        /*String description = "Pour cuisiner";
        String lib = "Casserole";
    
        String libReturn ="";
    
        try {
            libReturn=gestionArticle.chercherArticle(description, lib);
        } catch (ExceptionArticle ex) {
            System.out.println("metiers.Article_frame.initFrame()");
        }*/

    
    /*String choix[] = {libReturn, " item3", " item4", " item5"};
        
        liste = new JList(choix);
        liste.addListSelectionListener(this);
        add(etiquette, BorderLayout.WEST);
        add(liste, BorderLayout.WEST);
        add(bouton,BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);*/

    }
    
    public void afficheArticle() {
    
        
    }

     public void valueChanged(ListSelectionEvent evt) { 
        etiquette.setText((String)liste.getSelectedValue());
    }
}
