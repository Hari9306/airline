package ui;

import dao.UserDAO;
import javax.swing.*;
import java.awt.event.*;

public class LoginUI extends JFrame implements ActionListener{

    JTextField user;
    JPasswordField pass;

    public LoginUI(){

        setTitle("Login");
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addLabel("Username",30,30);
        user=new JTextField();
        user.setBounds(120,30,120,25);
        add(user);

        addLabel("Password",30,70);
        pass=new JPasswordField();
        pass.setBounds(120,70,120,25);
        add(pass);

        JButton btn=new JButton("Login");
        btn.setBounds(90,110,100,30);
        add(btn);
        btn.addActionListener(this);

        setVisible(true);
    }

    void addLabel(String t,int x,int y){
        JLabel l=new JLabel(t);
        l.setBounds(x,y,80,25);
        add(l);
    }

    public void actionPerformed(ActionEvent e){

        if(new UserDAO().login(user.getText(),new String(pass.getPassword()))){
            new DashboardUI(user.getText());
            dispose(); // CLOSE LOGIN
        }
        else{
            JOptionPane.showMessageDialog(this,"Invalid Login");
        }
    }

    public static void main(String[] args){
        new LoginUI();
    }
}