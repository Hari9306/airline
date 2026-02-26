package ui;

import dao.HistoryDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class BookingHistoryUI extends JFrame{

    JTable table;
    DefaultTableModel model;

    public BookingHistoryUI(String username){

        setTitle("Booking History");
        setSize(900,450);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel title=new JLabel("Your Booking History");
        title.setBounds(370,10,200,30);
        add(title);

        model=new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "BookingID",
                "Flight",
                "Source",
                "Destination",
                "Passenger",
                "Date",
                "Seat",
                "Status"
        });

        table=new JTable(model);
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(30,50,830,320);
        add(sp);

        loadData(username);

        setVisible(true);
    }

    private void loadData(String username){

        HistoryDAO dao=new HistoryDAO();
        List<String[]> list=dao.getHistory(username);

        for(String row[]:list)
            model.addRow(row);

        if(list.isEmpty())
            JOptionPane.showMessageDialog(this,"No Bookings Found");
    }
}