package ui;

import dao.FlightDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchFlightsUI extends JFrame implements ActionListener{

    JComboBox<String> srcBox,destBox;
    JDateChooser dateChooser;
    JButton searchBtn;
    JTable table;
    DefaultTableModel model;
    String username;

    public SearchFlightsUI(String user){

        username=user;

        setTitle("Search Flights");
        setSize(820,450);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1=new JLabel("Source");
        l1.setBounds(30,20,80,25);
        add(l1);

        srcBox=new JComboBox<>();
        srcBox.setBounds(100,20,150,25);
        add(srcBox);

        JLabel l2=new JLabel("Destination");
        l2.setBounds(280,20,100,25);
        add(l2);

        destBox=new JComboBox<>();
        destBox.setBounds(380,20,150,25);
        add(destBox);

        JLabel l3=new JLabel("Date");
        l3.setBounds(560,20,80,25);
        add(l3);

        dateChooser=new JDateChooser();
        dateChooser.setBounds(610,20,150,25);
        dateChooser.setDateFormatString("dd-MM-yyyy");
        add(dateChooser);

        searchBtn=new JButton("Search");
        searchBtn.setBounds(330,60,120,30);
        add(searchBtn);

        searchBtn.addActionListener(this);

        model=new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Flight No","Airline","Depart","Arrival","Seats","ScheduleID"
        });

        table=new JTable(model);
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(30,120,740,250);
        add(sp);

        FlightDAO dao=new FlightDAO();
        Set<String> airports=dao.getAirports();

        for(String a:airports){
            srcBox.addItem(a);
            destBox.addItem(a);
        }

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){

                int row=table.getSelectedRow();
                if(row==-1) return;

                String scheduleId=table.getValueAt(row,5).toString();

                new PassengerUI(username,scheduleId);
                dispose(); // CLOSE SEARCH
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        model.setRowCount(0);

        String src=srcBox.getSelectedItem().toString();
        String dest=destBox.getSelectedItem().toString();

        Date selectedDate=dateChooser.getDate();

        if(selectedDate==null){
            JOptionPane.showMessageDialog(this,"Select date");
            return;
        }

        if(src.equals(dest)){
            JOptionPane.showMessageDialog(this,"Source and Destination cannot be same");
            return;
        }

        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        String date=sdf.format(selectedDate);

        FlightDAO dao=new FlightDAO();
        List<String[]> list=dao.searchFlights(src,dest,date);

        for(String row[]:list)
            model.addRow(row);

        if(list.isEmpty())
            JOptionPane.showMessageDialog(this,"No Flights Found");
    }
}