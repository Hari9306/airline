package ui;

import javax.swing.*;
import java.awt.event.*;

public class DashboardUI extends JFrame implements ActionListener{

    JButton bookBtn,historyBtn;
    String username;

    public DashboardUI(String user){

        username=user;

        setTitle("Dashboard");
        setSize(300,220);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel welcome=new JLabel("Welcome "+username);
        welcome.setBounds(70,20,200,25);
        add(welcome);

        bookBtn=new JButton("Book Ticket");
        bookBtn.setBounds(70,70,150,30);
        add(bookBtn);

        historyBtn=new JButton("Booking History");
        historyBtn.setBounds(70,120,150,30);
        add(historyBtn);

        bookBtn.addActionListener(this);
        historyBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==bookBtn){
            new SearchFlightsUI(username);
            dispose(); // CLOSE DASHBOARD
        }

        if(e.getSource()==historyBtn){
            new BookingHistoryUI(username);
        }
    }
}