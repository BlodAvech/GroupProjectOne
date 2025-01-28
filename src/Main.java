import java.util.*;
import Classes.Doctor;
import Classes.Client;
import Classes.Person.DayOfWeek;
import Classes.Person.SessionTime;

public class Main {
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();

    public static void main(String[] args) {
        initializeDoctors();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Manage Doctor\n2. Manage Patient\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageDoctor(scanner);
                    break;
                case 2:
                    managePatient(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeDoctors() {
        doctors.add(new Doctor("Erkhan", "Piriyev"));
        doctors.add(new Doctor("Nursultan", "Makhmutov"));
        doctors.add(new Doctor("Beybarys", "Kuanysh"));
    }

    private static void manageDoctor(Scanner scanner) {
        Doctor doctor = selectDoctor(scanner);
        if (doctor == null) return;

        System.out.println("1. Restrict Day\n2. View Schedule");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter day to restrict (e.g., MONDAY):");
                String dayInput = scanner.nextLine().toUpperCase();

                try {
                    DayOfWeek day = DayOfWeek.valueOf(dayInput);
                    doctor.restrictDay(day);
                    System.out.println("Day restricted successfully.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid day.");
                }
                break;
            case 2:
                System.out.println(doctor);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void managePatient(Scanner scanner) {
        System.out.println("Enter patient first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter patient last name:");
        String lastName = scanner.nextLine();

        Client client = new Client(firstName, lastName);
        clients.add(client);

        Doctor doctor = selectDoctor(scanner);
        if (doctor == null) return;

        client.setDoctor(doctor);

        System.out.println("Enter day for the appointment (e.g., MONDAY):");
        String dayInput = scanner.nextLine().toUpperCase();

        try {
            DayOfWeek day = DayOfWeek.valueOf(dayInput);
            if (!doctor.isWorkingOn(day)) {
                System.out.println("Doctor is not working on this day. Choose another day.");
                return;
            }

            System.out.println("Available times:");
            Set<SessionTime> occupiedTimes = doctor.getSessions(day);
            for (SessionTime time : SessionTime.values()) {
                if (!occupiedTimes.contains(time)) {
                    System.out.println(time);
                }
            }

            System.out.println("Enter time for the appointment (e.g., 9:00):");
            String timeInput = scanner.nextLine();
            SessionTime selectedTime = Arrays.stream(SessionTime.values())
                    .filter(t -> t.toString().equals(timeInput))
                    .findFirst()
                    .orElse(null);

            if (selectedTime == null || occupiedTimes.contains(selectedTime)) {
                System.out.println("Invalid or already occupied time.");
                return;
            }

            doctor.addSession(day, selectedTime);
            client.setDay(day);
            client.setTime(selectedTime);

            System.out.println("Appointment booked successfully:");
            System.out.println(client);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid day.");
        }
    }

    private static Doctor selectDoctor(Scanner scanner) {
        System.out.println("Select a doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getFullName());
        }

        int doctorChoice = scanner.nextInt();
        scanner.nextLine();

        if (doctorChoice < 1 || doctorChoice > doctors.size()) {
            System.out.println("Invalid choice.");
            return null;
        }

        return doctors.get(doctorChoice - 1);
    }
}
