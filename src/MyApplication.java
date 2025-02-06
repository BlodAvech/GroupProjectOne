import controllers.interfaces.IDoctorController;
import controllers.interfaces.IPatientController;
import models.Doctor;
import models.Patient;

import javax.print.Doc;
import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private final IDoctorController doctorController;
    private final IPatientController patientController;

    private static final String[] DAYS_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static final String[] TIME_SLOTS = {
            "9:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00",
            "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00"
    };

    public MyApplication(IPatientController patientController, IDoctorController doctorController) {
        this.doctorController = doctorController;
        this.patientController = patientController;
    }

    public void Start() {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить врача");
            System.out.println("2. Найти врача по ID");
            System.out.println("3. Показать всех врачей");
            System.out.println("4. Добавить пациента");
            System.out.println("5. Найти пациента по ID");
            System.out.println("6. Показать всех пациентов");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createDoctor();
                    break;
                case 2:
                    getDoctorById();
                    break;
                case 3:
                    getAllDoctors();
                    break;
                case 4:
                    createPatient();
                    break;
                case 5:
                    getPatientById();
                    break;
                case 6:
                    getAllPatients();
                    break;
                case 0:
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void createDoctor() {
        System.out.println("Введите имя врача:");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию врача:");
        String surname = scanner.nextLine();

        boolean[] workdays = new boolean[6];
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            System.out.println("Работает в " + DAYS_OF_WEEK[i] + "? (true/false):");
            workdays[i] = scanner.nextBoolean();
        }
        scanner.nextLine();

        String response = doctorController.createDoctor(name, surname, workdays);
        System.out.println(response);
    }

    private void getDoctorById() {
        System.out.println("Введите ID врача:");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = doctorController.getDoctorById(id);
        System.out.println(response);
    }

    private void getAllDoctors() {
        List<Doctor> response = doctorController.getAllDoctors();
        System.out.println(response);
    }

    private void createPatient() {
        System.out.println("Введите имя пациента:");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию пациента:");
        String surname = scanner.nextLine();

        List<Doctor> doctors = doctorController.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("Нет доступных врачей. Сначала добавьте врача.");
            return;
        }

        System.out.println("Выберите врача из списка:");
        int index = 1;
        for (Doctor doctor : doctors) {
            System.out.println(index + ". " + doctor.toString());;
            index++;
        }

        int doctorChoice;
        while (true) {
            System.out.print("Введите номер врача: ");
            doctorChoice = scanner.nextInt();
            scanner.nextLine();
            if (doctorChoice > 0 && doctorChoice <= index - 1) {
                break;
            }
            System.out.println("Неверный выбор, попробуйте снова.");
        }

        int chosenIndex = (doctorChoice - 1) * 2;
        Doctor doctor = doctors.get(chosenIndex);

        System.out.println("Выберите день приема:");
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            if(!doctor.getWorkdays()[i]) continue;
            System.out.println((i + 1) + ". " + DAYS_OF_WEEK[i]);
        }

        int dayChoice;
        while (true) {
            System.out.print("Введите номер дня: ");
            dayChoice = scanner.nextInt();
            scanner.nextLine();
            if (dayChoice > 0 && dayChoice <= DAYS_OF_WEEK.length) {
                break;
            }
            System.out.println("Неверный выбор, попробуйте снова.");
        }
        String day = DAYS_OF_WEEK[dayChoice - 1];

        // Выбор времени
        System.out.println("Выберите время приема:");
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            System.out.println((i + 1) + ". " + TIME_SLOTS[i]);
        }

        int timeChoice;
        while (true) {
            System.out.print("Введите номер времени: ");
            timeChoice = scanner.nextInt();
            scanner.nextLine();
            if (timeChoice > 0 && timeChoice <= TIME_SLOTS.length) {
                break;
            }
            System.out.println("Неверный выбор, попробуйте снова.");
        }
        String time = TIME_SLOTS[timeChoice - 1];

        String response = patientController.createPatient(name, surname, day, time);
        System.out.println(response);
    }

    private void getPatientById() {
        System.out.println("Введите ID пациента:");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = patientController.getPatientById(id);
        System.out.println(response);
    }

    private void getAllPatients() {
        List<Patient> response = patientController.getAllPatients();
        System.out.println(response);
    }
}
