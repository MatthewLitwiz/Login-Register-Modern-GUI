package custom;

import java.awt.Color;

import javax.swing.JLabel;

public class ErrorLabel extends JLabel{
    public ErrorLabel(String errorText) {
        super(errorText);
        setForeground(Color.RED);
        setVisible(false);
    }

    
}
