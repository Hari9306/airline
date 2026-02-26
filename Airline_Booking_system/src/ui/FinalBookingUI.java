package ui;

import dao.BookingDAO;
import javax.swing.*;
import java.awt.event.*;

public class FinalBookingUI extends JFrame implements ActionListener{

    JButton confirmBtn;
    String u,s,n,a,g,pt,pn,seat;

    public FinalBookingUI(String u,String s,String n,String a,String g,String pt,String pn,String seat){

        this.u=u;
        this.s=s;
        this.n=n;
        this.a=a;
        this.g=g;
        this.pt=pt;
        this.pn=pn;
        this.seat=seat;

        setTitle("Confirm Booking");
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l=new JLabel("Confirm Ticket Booking?");
        l.setBounds(60,40,200,25);
        add(l);

        confirmBtn=new JButton("Confirm");
        confirmBtn.setBounds(90,100,120,30);
        add(confirmBtn);

        confirmBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        BookingDAO dao=new BookingDAO();

        boolean result=dao.bookTicket(u,s,n,a,g,pt,pn,seat);

        if(result){
            JOptionPane.showMessageDialog(this,"Ticket Booked Successfully!");

            dispose();
            new DashboardUI(u); // RETURN DASHBOARD
        }
        else{
            JOptionPane.showMessageDialog(this,"Booking Failed!");
        }
    }
}