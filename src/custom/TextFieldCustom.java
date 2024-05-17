package custom;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JTextField;

// creating a custom class is necessary as JTextField doesn't support placeholder text

public class TextFieldCustom extends JTextField{
    private String placeholderText;
    private boolean hasPlaceHolder;

    public boolean isHasPlaceHolder() {
        return hasPlaceHolder;
    }

    public TextFieldCustom(String placeholderText, int charLimit){
        super();
        this.placeholderText = placeholderText;

        // placeholder text status
        this.hasPlaceHolder = true;

        // limit char input in field
        setDocument(new LimitText(charLimit));
        setText(this.placeholderText);

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
                
        }}});

        // check to see if user has removed focus for this field
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                // check if field is empty
                if(getText().toString().length() <= 0){
                    hasPlaceHolder = true;
                    setText(placeholderText);

                }

            }


        });
    }
    
}
