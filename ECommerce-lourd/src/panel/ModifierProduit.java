/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Amélien
 */
public class ModifierProduit extends JFrame{
    
        private JButton fermer, valider;
        private JLabel JLlib, JLdes, JLpx, JLtva, JLstk;
        private JTextField JTlib, JTdes, JTpx, JTtva, JTstk;
        private JPanel JP = new JPanel();
    
    
    
    public ModifierProduit(/*Object o*/){
	this.setSize(500, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Modification sur un produit");
        this.setLocationRelativeTo(null);
        this.getContentPane().add(init());
        this.setVisible(true);
    }
    
    
    
    public JPanel init(){
        
        
        JP.add(JLlib = new JLabel("Libellé"));
        JP.add(JTlib = new JTextField(""));
        JTlib.setPreferredSize(new Dimension(400, 30));
        JP.add(JLdes = new JLabel("Description"));
        JP.add(JTdes = new JTextField(""));
        JTdes.setPreferredSize(new Dimension(400, 30));
        JP.add(JLpx = new JLabel("Prix Hors Taxe"));
        JP.add(JTpx = new JTextField(""));
        JTpx.setPreferredSize(new Dimension(150, 30));
        JP.add(JLtva = new JLabel("Taux TVA"));
        JP.add(JTtva = new JTextField(""));
        JTtva.setPreferredSize(new Dimension(150, 30));
        JP.add(JLstk = new JLabel("Stock actuel"));
        JP.add(JTstk = new JTextField(""));
        JTstk.setPreferredSize(new Dimension(150, 30));
        valider = new JButton("Valider");
        fermer = new JButton("Fermer");
        JP.add(valider);
        JP.add(fermer);
        this.add(JP);
        return JP;
    }
    
    public static void main(String[] args){
        ModifierProduit a = new ModifierProduit();
    }
}
