package ui;

import dao.SeatDAO;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SeatSelectionUI extends JFrame {

    String username,scheduleId,name,age,gender,proofT,proofNo,travelClass;
    String selectedSeat=null;

    public SeatSelectionUI(String u,String s,String n,String a,String g,String pt,String pn,String cls){

        username=u;
        scheduleId=s;
        name=n;
        age=a;
        gender=g;
        proofT=pt;
        proofNo=pn;
        travelClass=cls;

        setTitle("Aircraft Seat Selection");
        setSize(900,650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title=new JLabel("Select Seat — "+travelClass,SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,22));
        title.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
        add(title,BorderLayout.NORTH);

        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        SeatDAO dao=new SeatDAO();
        Set<String> booked=dao.getBookedSeats(scheduleId);

        char row='A';

        // FIRST CLASS
        mainPanel.add(section("FIRST CLASS",Color.RED));
        for(int i=0;i<2;i++){
            mainPanel.add(rowPanel(row,3,booked,"First Class"));
            row++;
        }

        mainPanel.add(space());

        // BUSINESS
        mainPanel.add(section("BUSINESS CLASS",Color.BLUE));
        for(int i=0;i<3;i++){
            mainPanel.add(rowPanel(row,6,booked,"Business Class"));
            row++;
        }

        mainPanel.add(space());

        // ECONOMY
        mainPanel.add(section("ECONOMY CLASS",Color.GREEN));
        for(int i=0;i<6;i++){
            mainPanel.add(rowPanel(row,6,booked,"Economy Class"));
            row++;
        }

        mainPanel.setPreferredSize(new Dimension(850,900));

        JScrollPane scroll=new JScrollPane(
                mainPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        add(scroll,BorderLayout.CENTER);

        JButton confirm=new JButton("Confirm Seat");
        confirm.setFont(new Font("Arial",Font.BOLD,18));
        confirm.setBackground(new Color(255,170,0));

        confirm.addActionListener(e->{

            if(selectedSeat==null){
                JOptionPane.showMessageDialog(this,"Select seat first");
                return;
            }

            new FinalBookingUI(username,scheduleId,name,age,gender,proofT,proofNo,selectedSeat);
            dispose(); // CLOSE THIS PAGE
        });

        JPanel bottom=new JPanel();
        bottom.add(confirm);
        add(bottom,BorderLayout.SOUTH);

        setVisible(true);
    }


    private JPanel rowPanel(char row,int seats,Set<String> booked,String section){

        JPanel panel=new JPanel(new FlowLayout(FlowLayout.CENTER,15,8));
        panel.setBackground(Color.WHITE);

        int left = seats/2;
        int right = seats-left;

        for(int i=1;i<=left;i++)
            panel.add(seatBtn(""+row+i,booked,section));

        panel.add(Box.createHorizontalStrut(60));

        for(int i=left+1;i<=seats;i++)
            panel.add(seatBtn(""+row+i,booked,section));

        return panel;
    }


    private JButton seatBtn(String seat,Set<String> booked,String section){

        JButton btn=new JButton(seat);
        btn.setPreferredSize(new Dimension(55,40));
        btn.setFont(new Font("Arial",Font.BOLD,13));

        if(!section.equals(travelClass)){
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setEnabled(false);
            return btn;
        }

        if(booked.contains(seat)){
            btn.setBackground(Color.RED);
            btn.setEnabled(false);
        }
        else{
            btn.setBackground(Color.GREEN);

            btn.addActionListener(e->{
                selectedSeat=seat;
                resetColors(btn.getParent().getParent());
                btn.setBackground(Color.BLUE);
            });
        }

        return btn;
    }


    private void resetColors(Container parent){
        for(Component p:parent.getComponents()){
            if(p instanceof JPanel){
                for(Component c:((JPanel)p).getComponents()){
                    if(c instanceof JButton){
                        JButton b=(JButton)c;
                        if(b.isEnabled())
                            b.setBackground(Color.GREEN);
                    }
                }
            }
        }
    }

    private JLabel section(String text,Color c){
        JLabel l=new JLabel(text,SwingConstants.CENTER);
        l.setFont(new Font("Arial",Font.BOLD,17));
        l.setForeground(c);
        l.setBorder(BorderFactory.createEmptyBorder(25,0,10,0));
        return l;
    }

    private Component space(){
        return Box.createVerticalStrut(30);
    }
}