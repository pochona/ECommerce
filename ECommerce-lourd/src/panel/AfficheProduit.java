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
 * @author Amaury_PC
 */
public class AfficheProduit extends JPanel {
 
    Fenetre maFenetre;
    
    public AfficheProduit(Fenetre maFenetre){
        this.maFenetre = maFenetre;

        GridLayout tableau = new GridLayout(10,3);
        //panel.setLayout(new FlowLayout());
        this.setLayout(tableau);
        
        
        System.out.println("Retour méthode List<String> lister()");
        List<String> list = this.maFenetre.getServiceCommercial().lister();

        for(String a : list) {
            System.out.println(a);
            JLabel JL = new JLabel();
            JL.setText(a);
            this.add(JL);
        }
        
        //produitList = new JList();
        //produitList.setVisibleRowCount(2);
        //JScrollPane scrollProduit = new JScrollPane(panel);
    }
}
