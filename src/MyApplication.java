import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyApplication {
    private static HashMap<String, Doctor> doctors = new HashMap<>();

    public static void main(String[] args) {
        // Создаем начальных докторов
        initializeDoctors();

        // Главное окно
        JFrame frame = new JFrame("Clinic Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(2, 1));

        JButton patientButton = new JButton("I'm a Patient");
        JButton doctorButton = new JButton("I'm a Doctor");

        patientButton.addActionListener(e -> openPatientUI());
        doctorButton.addActionListener(e -> openDoctorUI());

        frame.add(patientButton);
        frame.add(doctorButton);
        frame.setVisible(true);
    }

    private static void initializeDoctors() {
        doctors.put("Dr. Smith", new Doctor("Dr. Smith"));
        doctors.put("Dr. Johnson", new Doctor("Dr. Johnson"));
        doctors.put("Dr. Brown", new Doctor("Dr. Brown"));
    }

    private static void openPatientUI() {
        JFrame frame = new JFrame("Patient Panel");
        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(5, 2));

        JLabel doctorLabel = new JLabel("Choose Doctor:");
        JComboBox<String> doctorList = new JComboBox<>(doctors.keySet().toArray(new String[0]));

        JLabel dayLabel = new JLabel("Choose Day:");
        JComboBox<String> dayList = new JComboBox<>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});

        JLabel timeLabel = new JLabel("Choose Time:");
        JTextField timeField = new JTextField();

        JButton submitButton = new JButton("Book Appointment");
        JLabel statusLabel = new JLabel("");

        submitButton.addActionListener(e -> {
            String selectedDoctor = (String) doctorList.getSelectedItem();
            String selectedDay = (String) dayList.getSelectedItem();
            String time = timeField.getText();

            if (selectedDoctor != null && !time.isEmpty()) {
                Doctor doctor = doctors.get(selectedDoctor);
                boolean success = doctor.addAppointment(selectedDay, time);
                if (success) {
                    statusLabel.setText("Appointment booked successfully!");
                } else {
                    statusLabel.setText("This time slot is not available.");
                }
            } else {
                statusLabel.setText("Please fill all fields.");
            }
        });

        frame.add(doctorLabel);
        frame.add(doctorList);
        frame.add(dayLabel);
        frame.add(dayList);
        frame.add(timeLabel);
        frame.add(timeField);
        frame.add(submitButton);
        frame.add(statusLabel);

        frame.setVisible(true);
    }

    private static void openDoctorUI() {
        JFrame frame = new JFrame("Doctor Panel");
        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(4, 2));

        JLabel doctorLabel = new JLabel("Select your name:");
        JComboBox<String> doctorList = new JComboBox<>(doctors.keySet().toArray(new String[0]));

        JButton viewAppointmentsButton = new JButton("View Appointments");
        JButton manageDaysOffButton = new JButton("Manage Days Off");

        viewAppointmentsButton.addActionListener(e -> {
            String selectedDoctor = (String) doctorList.getSelectedItem();
            if (selectedDoctor != null) {
                Doctor doctor = doctors.get(selectedDoctor);
                showAppointments(doctor);
            }
        });

        manageDaysOffButton.addActionListener(e -> {
            String selectedDoctor = (String) doctorList.getSelectedItem();
            if (selectedDoctor != null) {
                Doctor doctor = doctors.get(selectedDoctor);
                manageDaysOff(doctor);
            }
        });

        frame.add(doctorLabel);
        frame.add(doctorList);
        frame.add(viewAppointmentsButton);
        frame.add(manageDaysOffButton);

        frame.setVisible(true);
    }

    private static void showAppointments(Doctor doctor) {
        JFrame frame = new JFrame("Appointments for " + doctor.getName());
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTextArea appointmentsArea = new JTextArea();
        appointmentsArea.setEditable(false);

        doctor.getAppointments().forEach((day, times) -> {
            appointmentsArea.append(day + ":\n");
            for (String time : times) {
                appointmentsArea.append("  " + time + "\n");
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());

        frame.add(new JScrollPane(appointmentsArea), BorderLayout.CENTER);
        frame.add(closeButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void manageDaysOff(Doctor doctor) {
        JFrame frame = new JFrame("Days Off Management for " + doctor.getName());
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JList<String> daysList = new JList<>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
        daysList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton saveButton = new JButton("Save Days Off");
        saveButton.addActionListener(e -> {
            List<String> selectedDays = daysList.getSelectedValuesList();
            doctor.setDaysOff(selectedDays);
            JOptionPane.showMessageDialog(frame, "Days off updated successfully.");
            frame.dispose();
        });

        frame.add(new JScrollPane(daysList), BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

// Класс Doctor
class Doctor {
    private String name;
    private List<String> daysOff;
    private HashMap<String, List<String>> appointments;

    public Doctor(String name) {
        this.name = name;
        this.daysOff = new ArrayList<>();
        this.appointments = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(List<String> daysOff) {
        this.daysOff = daysOff;
    }

    public HashMap<String, List<String>> getAppointments() {
        return appointments;
    }

    public boolean addAppointment(String day, String time) {
        if (daysOff.contains(day)) {
            return false; // Нельзя забронировать прием в выходной
        }
        appointments.putIfAbsent(day, new ArrayList<>());
        if (appointments.get(day).contains(time)) {
            return false; // Уже забронировано
        }
        appointments.get(day).add(time);
        return true;
    }
}