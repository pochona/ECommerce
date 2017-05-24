/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import fenetre.Fenetre;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Laura
 */
public class GererCommande extends JPanel {
    Fenetre maFenetre;
    
    public GererCommande(Fenetre maFen){
        this.maFenetre = maFen;
        init(); 
       
    }
    
    public void init(){
        
         GridLayout tableau = new GridLayout(10,3);
        //panel.setLayout(new FlowLayout());
        this.setLayout(tableau);
        
        
        /*List<String> list = this.maFenetre.getServiceBanque().lister();
        for(String c : list) {
            System.out.println(c);
            JLabel JL = new JLabel();
            JL.setText(c);
            this.add(JL);
        }*/
    }
}
