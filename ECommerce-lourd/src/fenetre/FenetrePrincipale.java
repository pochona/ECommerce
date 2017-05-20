/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Amélien
 */

public class FenetrePrincipale extends JFrame{
    
    
    JButton serviceBout1 = new JButton("Service Commercial");
    JButton serviceBout2 = new JButton("Service Livraison");
    JButton serviceBout3 = new JButton("Service Comptable");
    JButton serviceBout4 = new JButton("Service Réaprovisionnement");
    JPanel aux = new JPanel();
    
    public FenetrePrincipale(){
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Projet Ecommerce");
        this.setLocationRelativeTo(null);
        this.getContentPane().add(init());
        this.setVisible(true);
    }
    
    public JPanel init(){
        
        JPanel JP = new JPanel();
        JP.setLayout(null);
        JP.add(serviceBout1);
        serviceBout1.setBounds(70, 70, 200, 80);
        serviceBout1.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                FenetreServiceCommercial fd = new FenetreServiceCommercial();
                
                aux=fd.FenetreServiceCommercial();
                setContentPane(aux);
                validate();
                //repaint();
            }
        }
        );
        JP.add(serviceBout2);
        serviceBout2.setBounds(70, 270, 200, 80);
        JP.add(serviceBout3);
        serviceBout3.setBounds(380, 70, 200, 80);
        JP.add(serviceBout4);
        serviceBout4.setBounds(380, 270, 200, 80);  // (x,y, largeur, hauteur)
        return JP;
    }
    
    public static void main(String argv[]) {
        FenetrePrincipale fp = new FenetrePrincipale();
    }
    
}
