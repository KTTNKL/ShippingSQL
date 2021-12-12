import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField usernameT;
    private JTextField passwordT;
    private JButton loginButton;
    private JPanel loginPanel;

    public Login(){
        setContentPane(loginPanel);
        setTitle("Login");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passwordT.getText().equals("") || usernameT.getText().equals("")){
                    JOptionPane.showMessageDialog(null,
                            "Both field must not be blank",
                            "Login failed",
                            JOptionPane.WARNING_MESSAGE);
                }else{
                    DataConnection app = new DataConnection();
                    if(passwordT.getText().equals("")){
                        JOptionPane.showMessageDialog(null,
                                "Username or Password is incorrect",
                                "Login failed",
                                JOptionPane.WARNING_MESSAGE);
                    }else if(passwordT.getText().equals("123")){
                        if(usernameT.getText().equals("login1")){
                            new Profile();//KH
                        }
                        else if(usernameT.getText().equals("login3")){
                            new ManageAccount();//NV

                        }else if(usernameT.getText().equals("login6")){
                            new ManageAccount();//NV

                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "Username or Password is incorrect",
                                    "Login failed",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Username or Password is incorrect",
                                "Login failed",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Login loginFrame = new Login();
    }
}
