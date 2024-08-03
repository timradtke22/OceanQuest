package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    
    public GameWindow(GamePanel gamepanel) {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gamepanel);
        setResizable(false);
        setTitle("Ocean Quest");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                //Auto generated and not needed
                
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamepanel.getGame().windowFocusLost();
            }
            
        });
        
    }


    
    
}
