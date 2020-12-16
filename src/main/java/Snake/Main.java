/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

//import java.awt.EventQueue;
//import javax.swing.JFrame;

/**
 *
 * @author tret
 */
public class Main {
    private UserFrame userFrame;
    private MainFrame mainFrame;
    
    public Main() {
       userFrame = new UserFrame();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainFrame.main(args);
        /*EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new Main();
                ex.setVisible(true);                
            }
        });*/
    }
}
