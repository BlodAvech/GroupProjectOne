package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyApplication {
    private static List<Doctor> doctors = List.of(
            new Doctor("Erkhan", "Piriyev"),
            new Doctor("Nursultan", "Makhmutov"),
            new Doctor("Beybarys", "Kuanysh")
    );

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyApplication::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Patient Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel nameLabel = new JLabel("First Name:");
        JTextField nameField = new JTextField();
        JLabel surnameLabel = new JLabel("Last Name:");
        JTextField surnameField = new JTextField();

        JLabel doctorLabel = new JLabel("Select Doctor:");
        JComboBox<String> doctorBox = new JComboBox<>();
        for (Doctor doctor : doctors) {
            doctorBox.addItem(doctor.getFullName());
        }

        JLabel dayLabel = new JLabel("Select Day:");
        JComboBox<String> dayBox = new JComboBox<>();
        for (Person.DayOfWeek day : Person.DayOfWeek.values()) {
            dayBox.addItem(day.toString());
        }

        JLabel timeLabel = new JLabel("Select Time:");
        JComboBox<String> timeBox = new JComboBox<>();
        for (Person.SessionTime time : Person.SessionTime.values()) {
            timeBox.addItem(time.toString());
        }

        JButton submitButton = new JButton("Register");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String doctorName = (String) doctorBox.getSelectedItem();
                String selectedDay = (String) dayBox.getSelectedItem();
                String selectedTime = (String) timeBox.getSelectedItem();
                savePatient(name, surname, doctorName, selectedDay, selectedTime);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(doctorLabel);
        panel.add(doctorBox);
        panel.add(dayLabel);
        panel.add(dayBox);
        panel.add(timeLabel);
        panel.add(timeBox);
        panel.add(new JLabel());
        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void savePatient(String name, String surname, String doctorName, String day, String time) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt", true))) {
            writer.write(name + " " + surname + " - Doctor: " + doctorName + " - Day: " + day + " - Time: " + time);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Patient registered successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving patient data.");
        }
    }
}
