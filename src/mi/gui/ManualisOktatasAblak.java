package mi.gui;

import javax.swing.*;
import java.awt.*;

public class ManualisOktatasAblak extends JFrame implements Runnable {

    public ManualisOktatasAblak(){
        setLayout(new BorderLayout());
    }

    @Override
    public void run() {
        createView();
    }

    private void createView() {
        JFrame frame = new ManualisOktatasAblak();
        frame.setSize(640, 480);
        frame.setTitle("ChatBot - Manuális oktatás");

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    // x kor bezárja
    }
}
