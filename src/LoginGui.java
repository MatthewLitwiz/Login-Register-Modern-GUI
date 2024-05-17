import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import custom.PasswordFieldCustom;
import custom.TextFieldCustom;

public class LoginGui extends JFrame implements ActionListener{  
    private TextFieldCustom usernameField; // username field
    private PasswordFieldCustom passwordField;

    public LoginGui(){
        super("TapTap Ind. Login");
        setSize(CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null); // center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        // login image
        JLabel loginImage = CustomTools.loadImage(CommonConstants.LOGIN_IMAGE_PATH);
        loginImage.setBounds(
            (CommonConstants.FRAME_SIZE.width - loginImage.getPreferredSize().width) / 2, //  The width of the frame (or container) ||  The preferred width of the loginImage label (i.e., the width of the image). ||  Subtracting the width of the image from the width of the frame and dividing by 2 centers the image horizontally.
            25, // The y position is set to 25 pixels from the top of the frame.
            CommonConstants.LOGIN_IMAGE_SIZE.width, CommonConstants.LOGIN_IMAGE_SIZE.height // The width and height of the label are set to specific dimensions from CommonConstants.LOGIN_IMAGE_SIZE
        );

        // username field
        usernameField = new TextFieldCustom("Enter Username", 38); // The text field is initialized with the placeholder text "Username".
        usernameField.setBounds(
                50,
                loginImage.getY() + 315,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );

        // password field
        passwordField = new PasswordFieldCustom("Enter Password", 38); // The text field is initialized with the placeholder text "Password".
        passwordField.setBounds(
            50, 
            usernameField.getY() + 100,
            CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height 
        );

        // login button
        JButton loginButton = new JButton("Login"); // The login button is initialized with the text "Login".
        loginButton.setFocusable(false);
        loginButton.setBounds(
        50, 
        passwordField.getY() + 100,
        CommonConstants.BUTTON_SIZE.width, CommonConstants.BUTTON_SIZE.height
        );
        loginButton.addActionListener(this); // The login button is set to listen for an action event.

        // login -> register label
        JLabel registerLabel = new JLabel("Don't have an account? Register here!"); // The registerLabel label is initialized with the text "Don't have an account? Register here!".
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // The cursor is set to the hand cursor when the mouse hovers over the label.
        registerLabel.setBounds(
                (CommonConstants.FRAME_SIZE.width - registerLabel.getPreferredSize().width) / 2, // The width of the frame (or container) || The preferred width of the registerLabel label (i.e., the width of the text). || Subtracting the width of the text from the width of the frame and dividing by 2 centers the text horizontally.
                loginButton.getY() + 100, // The y position is set to 100 pixels below the loginButton button.
                registerLabel.getPreferredSize().width, registerLabel.getPreferredSize().height // The width and height of the label are set to the preferred dimensions of the label.
        );

        // add to frame
        getContentPane().add(loginImage); // The loginImage label is added to the frame. (the frame itself contains several layers, with the content pane being the primary layer where components are added)
        getContentPane().add(usernameField); // The usernameField text field is added to the frame.
        getContentPane().add(passwordField); // The passwordField text field is added to the frame.
        getContentPane().add(loginButton); // The loginButton button is added to the frame.
        getContentPane().add(registerLabel); // The registerLabel label is added to the frame.

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // The command is retrieved from the action event.
        if(command.equalsIgnoreCase("Login")){
            // create dialog box
            JDialog resultDialog = new JDialog();
            resultDialog.setPreferredSize(CommonConstants.RESULT_DIALOG_SIZE);
            resultDialog.pack(); // used to resize the window to fit the preferred size and layouts of its component
            resultDialog.setLocationRelativeTo(this); // positions the dialog relative to the current instance of the enclosing class
            resultDialog.setModal(true);
            
            // create label (display result)
            JLabel resultLabel = new JLabel();
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER); // The text is centered horizontally.
            resultDialog.add(resultLabel);

            // retrieve entered credentials
            String username = usernameField.getText(); // The username is retrieved from the usernameField text field.
            String password = passwordField.getText(); // The password is retrieved from the passwordField text field.

            // validate credentials in UserDB
            if(UserDB.userDB.get(username) != null){
                // checks password
                String validPass = UserDB.userDB.get(username);
                if(password.equals(validPass)){
                    // display a success dialog
                    resultLabel.setText("Login Successful!"); // The text of the resultLabel label is set to "Login Successful!".
                } else{
                    // display an incorrect password dialog
                    resultLabel.setText("Incorrect Password!"); // The text of the resultLabel label is set to "Incorrect Password!".

                }   
            } else{
                // display an incorrect username dialog
                resultLabel.setText("Incorrect Username!"); // The text of the resultLabel label is set to "Incorrect Username!".

            }
            resultDialog.setVisible(true); // The resultDialog dialog is set to visible.

            }

        }

    }
