import javax.swing.*;

public class Login extends JFrame {
    private JTextField usernameT;
    private JTextField passwordT;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel loginPanel;

    public Login(){
        setContentPane(loginPanel);
        setTitle("Login");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        Login loginFrame = new Login();
    }
}
