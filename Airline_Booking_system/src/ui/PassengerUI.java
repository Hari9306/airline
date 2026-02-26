package ui;

import javax.swing.*;
import java.awt.event.*;

public class PassengerUI extends JFrame implements ActionListener{

    JTextField nameField,ageField,proofField;
    JComboBox<String> genderBox,proofType,classBox;
    JButton nextBtn;

    String username,scheduleId;

    public PassengerUI(String u,String s){

        username=u;
        scheduleId=s;

        setTitle("Passenger Details");
        setSize(380,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addLabel("Name",30,30);
        nameField=new JTextField();
        nameField.setBounds(150,30,170,25);
        add(nameField);

        addLabel("Age",30,70);
        ageField=new JTextField();
        ageField.setBounds(150,70,170,25);
        add(ageField);

        addLabel("Gender",30,110);
        genderBox=new JComboBox<>(new String[]{"Male","Female","Other"});
        genderBox.setBounds(150,110,170,25);
        add(genderBox);

        addLabel("Class",30,150);
        classBox=new JComboBox<>(new String[]{
                "First Class",
                "Business Class",
                "Economy Class"
        });
        classBox.setBounds(150,150,170,25);
        add(classBox);

        addLabel("Proof Type",30,190);
        proofType=new JComboBox<>(new String[]{
                "Aadhar","Passport","License"
        });
        proofType.setBounds(150,190,170,25);
        add(proofType);

        addLabel("Proof No",30,230);
        proofField=new JTextField();
        proofField.setBounds(150,230,170,25);
        add(proofField);

        nextBtn=new JButton("Next → Select Seat");
        nextBtn.setBounds(100,290,170,35);
        add(nextBtn);

        nextBtn.addActionListener(this);

        setVisible(true);
    }

    void addLabel(String text,int x,int y){
        JLabel l=new JLabel(text);
        l.setBounds(x,y,120,25);
        add(l);
    }

    public void actionPerformed(ActionEvent e){

        String name=nameField.getText().trim();
        String age=ageField.getText().trim();
        String proofNo=proofField.getText().trim();

        if(name.isEmpty()||age.isEmpty()||proofNo.isEmpty()){
            JOptionPane.showMessageDialog(this,"Fill all fields");
            return;
        }

        if(!age.matches("\\d+")){
            JOptionPane.showMessageDialog(this,"Age must be number");
            return;
        }

        new SeatSelectionUI(
                username,
                scheduleId,
                name,
                age,
                genderBox.getSelectedItem().toString(),
                proofType.getSelectedItem().toString(),
                proofNo,
                classBox.getSelectedItem().toString()
        );

        dispose(); // CLOSE THIS PAGE
    }
}