package dao;

import dao.HistoryDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class BookingHistoryUI extends JFrame{

    public BookingHistoryUI(String username){

        setTitle("Booking History");
        setSize(600,400);
        setLayout(null);
        setLocationRelativeTo(null);

        DefaultTableModel model=new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "BookingID","Flight","Date","Seat","Status"
        });

        JTable table=new JTable(model);
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(30,40,520,250);
        add(sp);

        HistoryDAO dao=new HistoryDAO();
        List<String[]> list=dao.getHistory(username);

        for(String r[]:list)
            model.addRow(r);

        setVisible(true);
    }
}