package AplikasiParkirOtomatis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login extends JFrame {

    private JPanel MainPanel;
    private JButton submitButton;
    private JPasswordField passwordField1;
    private JTextField textField1;

    public Login(){
        setContentPane(MainPanel);
        setTitle("AplikasiParkirOtomatis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username =  Login.this.textField1.getText();
                String password =  Login.this.passwordField1.getText();
                if(Objects.equals(username, "admin") && Objects.equals(password, "123")){
                    JOptionPane.showMessageDialog(Login.this,"Anda berhasil masuk");
                }else{
                    JOptionPane.showMessageDialog(Login.this,"Username atau Password anda salah!!");
                }
            }
        });
    }
    public static void main(String[] args){
        new Login();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
