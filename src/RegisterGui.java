import custom.ErrorLabel;
import custom.PasswordFieldCustom;
import custom.TextFieldCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterGui extends JFrame implements ActionListener, FocusListener {
    private ErrorLabel usernameErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel, emailErrorLabel;
    private TextFieldCustom usernameField, emailField;
    private PasswordFieldCustom passwordField, confirmPasswordField;
    private Font customFont;

    public RegisterGui(){
        super("TapTap Ind. Register");
        setSize(CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);

        // create custom font
        customFont = CustomTools.createFont(CommonConstants.FONT_PATH);

        addGuiComponent();
    }

    private void addGuiComponent(){
        // register label
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(customFont.deriveFont(68f));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        registerLabel.setBounds(
                0,
                0,
                CommonConstants.REGISTER_LABEL_SIZE.width, CommonConstants.REGISTER_LABEL_SIZE.height
        );

        // username field
        usernameField = new TextFieldCustom("Enter Username", 30);
        usernameField.setFont(customFont.deriveFont(32f));
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(Color.WHITE);
        usernameField.setBounds(
                50,
                registerLabel.getY() + 150,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        usernameField.addFocusListener(this);

        // username error label
        usernameErrorLabel = new ErrorLabel("*Invalid: Can't be less than 6 characters");
        usernameErrorLabel.setBounds(
                50,
                usernameField.getY() + 50,
                CommonConstants.TEXTFIELD_SIZE.width, 25
        );

        // password field
        passwordField = new PasswordFieldCustom("Enter Password", 30);
        passwordField.setFont(customFont.deriveFont(32f));
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(Color.WHITE);
        passwordField.setBounds(
                50,
                usernameField.getY() + 100,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        passwordField.addFocusListener(this);

        // password error label
        passwordErrorLabel = new ErrorLabel("*Invalid: Size > 6, 1 Upper and Lower, 1 #, and 1 Special Char");
        passwordErrorLabel.setBounds(
                50,
                passwordField.getY() + 50,
                CommonConstants.TEXTFIELD_SIZE.width, 25
        );

        // confirm password field
        confirmPasswordField = new PasswordFieldCustom("Confirm Password", 30);
        confirmPasswordField.setFont(customFont.deriveFont(32f));
        confirmPasswordField.setBackground(CommonConstants.SECONDARY_COLOR);
        confirmPasswordField.setForeground(Color.WHITE);
        confirmPasswordField.setBounds(
                50,
                passwordField.getY() + 100,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        confirmPasswordField.addFocusListener(this);

        // confirm password error label
        confirmPasswordErrorLabel = new ErrorLabel("*Invalid: Passwords are not the same");
        confirmPasswordErrorLabel.setBounds(
                50,
                confirmPasswordField.getY() + 50,
                CommonConstants.TEXTFIELD_SIZE.width, 25
        );

        // email field
        emailField = new TextFieldCustom("Enter E-Mail", 30);
        emailField.setFont(customFont.deriveFont(32f));
        emailField.setBackground(CommonConstants.SECONDARY_COLOR);
        emailField.setForeground(Color.WHITE);
        emailField.setBounds(
                50,
                confirmPasswordField.getY() + 100,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        emailField.addFocusListener(this);

        // email error label
        emailErrorLabel = new ErrorLabel("*Invalid: Not a valid format");
        emailErrorLabel.setBounds(
                50,
                emailField.getY() + 50,
                CommonConstants.TEXTFIELD_SIZE.width, 25
        );

        // register button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(customFont.deriveFont(32f));
        registerButton.setBackground(CommonConstants.BUTTON_COLOR);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(
                50,
                emailField.getY() + 100,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );
        registerButton.addActionListener(this);

        // register -> login label
        JLabel loginLabel = new JLabel("Already a user? Login Here");
        loginLabel.setFont(customFont.deriveFont(32f));
        loginLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        loginLabel.setBounds(
                (CommonConstants.FRAME_SIZE.width - loginLabel.getPreferredSize().width)/2,
                registerButton.getY() + 100,
                loginLabel.getPreferredSize().width, loginLabel.getPreferredSize().height
        );
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginGui().setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginLabel.setForeground(CommonConstants.SECONDARY_COLOR);
            }
        });

        // add to frame
        getContentPane().add(registerLabel);

        // username
        getContentPane().add(usernameField);
        getContentPane().add(usernameErrorLabel);

        // password
        getContentPane().add(passwordField);
        getContentPane().add(passwordErrorLabel);

        // confirm password
        getContentPane().add(confirmPasswordField);
        getContentPane().add(confirmPasswordErrorLabel);

        // email
        getContentPane().add(emailField);
        getContentPane().add(emailErrorLabel);

        getContentPane().add(registerButton);
        getContentPane().add(loginLabel);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @SuppressWarnings("deprecation")
    @Override
    public void focusLost(FocusEvent e) {
        Object fieldSource = e.getSource();
        if(fieldSource == usernameField){
            // valid username has to be greater or equal to 6
            if(usernameField.getText().length() < 6 || usernameField.isHasPlaceHolder()){
                usernameErrorLabel.setVisible(true); // show error label
            }else{
                usernameErrorLabel.setVisible(false); // hide error label
            }
        }else if(fieldSource == passwordField){
            // if password isn't 6 characters long, has 1 uppercase and 1 lowercase, and a special character it is invalid
            String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()<>~`])(?=\\S+$).{6,30}$";
            Pattern p = Pattern.compile(passwordRegex); // compile regex
            Matcher m = p.matcher(passwordField.getText()); // match regex with password
            if(!m.find()) passwordErrorLabel.setVisible(true); // if it doesn't match show error label
            else passwordErrorLabel.setVisible(false); // else hide error label
        }else if(fieldSource == confirmPasswordField){
            // if passwords don't match it is invalid
            if(!passwordField.getText().equals(confirmPasswordField.getText())){ // if passwords don't match show error label
               confirmPasswordErrorLabel.setVisible(true);
            }else{
                confirmPasswordErrorLabel.setVisible(false);
            }
        }else if(fieldSource == emailField){
            // checks email if it is in valid format
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; // regex for email
            Pattern p = Pattern.compile(emailRegex); // compile regex
            Matcher m = p.matcher(emailField.getText()); // match regex with email
            if(!m.find()) emailErrorLabel.setVisible(true); // if it doesn't match show error label
            else emailErrorLabel.setVisible(false); // else hide error label
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // checks if there is no errors and that all field have been filled
        String command = e.getActionCommand();
        if(command.equals("Register")){
            // validate that no error labels are present and that they aren't left in placeholder state
            boolean isValid = !usernameErrorLabel.isVisible() && !passwordErrorLabel.isVisible() && !confirmPasswordErrorLabel.isVisible() // if error labels are visible then it is invalid
                    && !emailErrorLabel.isVisible() && !usernameField.isHasPlaceHolder() && !passwordField.isHasPlaceHolder() // if fields are in placeholder state then it is invalid
                    && !confirmPasswordField.isHasPlaceHolder() && !emailField.isHasPlaceHolder(); // if fields are in placeholder state then it is invalid

            // result dialog
            JDialog resultDialog = new JDialog(); // create a new dialog
            resultDialog.setSize(CommonConstants.RESULT_DIALOG_SIZE); // set size
            resultDialog.setLocationRelativeTo(this); // center the dialog
            resultDialog.setModal(true);
            resultDialog.getContentPane().setBackground(CommonConstants.PRIMARY_COLOR); // set background color

            // result label
            JLabel resultLabel = new JLabel();
            resultLabel.setFont(customFont.deriveFont(22f)); // set font
            resultLabel.setPreferredSize(CommonConstants.RESULT_DIALOG_SIZE); // set size
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER); // center the text
            resultDialog.add(resultLabel); // add to dialog
            resultLabel.setForeground(CommonConstants.SECONDARY_COLOR); // set text color

            if(isValid){
                // store the user info into the UserDB
                String username = usernameField.getText();
                String password = passwordField.getText();
                UserDB.addUser(username, password);

                // show a dialog that shows that the user has been added to the UserDB
                resultLabel.setText("Account Registered!");

                // take user back to the login gui (after closing dialog window)
                resultDialog.addWindowListener(new WindowAdapter() { // handle window events. These events pertain to the lifecycle of a window, such as opening, closing, minimizing, and so on. Here's an explanation of both:
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                        new LoginGui().setVisible(true);
                    }
                });
            }else{
                // show an error label
                resultLabel.setText("Invalid Credentials");
            }

            resultDialog.setVisible(true);
        }
    }
}