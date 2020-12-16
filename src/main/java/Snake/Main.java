/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

//import java.awt.EventQueue;

import javax.swing.SwingUtilities;

//import javax.swing.JFrame;

/**
 *
 * @author tret
 */
public class Main {
    private Conf conf;
    private UserFrame userFrame;
    
    public Main() {
        conf = new Conf();
        userFrame = new UserFrame(conf);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //MainFrame.main(args);
        
        /*EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new Main();
                ex.setVisible(true);                
            }
        });*/
        
        Main main = new Main();
        main.go();
    }
    
    public void go() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                userFrame.setVisible(true);
            }
        });
    }
}
