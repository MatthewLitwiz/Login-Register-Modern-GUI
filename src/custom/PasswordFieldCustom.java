package custom;

import javax.swing.JPasswordField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

// custom version of the JPasswordField to have placeholder text
public class PasswordFieldCustom extends JPasswordField{
    private String placeholderText;
    private boolean hasPlaceHolder;

    public boolean isHasPlaceHolder() {
        return hasPlaceHolder;
    }

    public PasswordFieldCustom(String placeholderText, int charLimit){
        super();
        this.placeholderText = placeholderText;

        // placeholder text status
        this.hasPlaceHolder = true;

        // limit char input in field
        setDocument(new LimitText(charLimit));
        setText(this.placeholderText);

        // changes styling to text
        setEchoChar((char) 0); // This method sets the echo character for the JPasswordField, which is the character that is displayed instead of the actual characters typed by the use

        addListeners();
    }

    private void addListeners(){
        // check for mouse clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (hasPlaceHolder){
                    hasPlaceHolder = false;
                    setText("");
                    setEchoChar('*');
                }
            }

        });

        // check for key presses
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(hasPlaceHolder){
                    hasPlaceHolder = false;
                    setText("");
                    setEchoChar('*');

        }}});

        // check to see if user has removed focus for this field
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void focusLost(FocusEvent e) {
                // check if field is empty
                if(getText().toString().length() <= 0){
                    hasPlaceHolder = true;
                    setText(placeholderText);
                    setEchoChar((char) 0);
                }

            }


        });
    }
    
}