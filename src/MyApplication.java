import controllers.interfaces.IDoctorController;
import controllers.interfaces.IOrderController;
import controllers.interfaces.IPatientController;
import models.Doctor;
import models.Patient;
import models.Order;

import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] SPECIALIZATIONS = {"Терапевт", "Ортопед", "Хирург", "Пародонтолог" , "Ортодонт" , "Детский стоматолог"};
    private static final String[] WEEK_DAYS = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
    private final IDoctorController doctorController;
    private final IPatientController patientController;
    private final IOrderController orderController;

    public MyApplication(IPatientController patientController, IDoctorController doctorController, IOrderController orderController) {
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.orderController = orderController;
    }

    public void start() {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить доктора");
            System.out.println("2. Найти доктора по ID");
            System.out.println("3. Показать всех докторов");
            System.out.println("4. Добавить пациента");
            System.out.println("5. Найти пациента по ID");
            System.out.println("6. Показать всех пациентов");
            System.out.println("7. Создать заказ");
            System.out.println("8. Найти заказ по ID");
            System.out.println("9. Показать все заказы");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> getDoctorById();
                case 3 -> getAllDoctors();
                case 4 -> addPatient();
                case 5 -> getPatientById();
                case 6 -> getAllPatients();
                case 7 -> createOrder();
                case 8 -> getOrderById();
                case 9 -> getAllOrders();
                case 0 -> { return; }
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    private void addDoctor() {
        System.out.print("Введите имя доктора: ");
        String name = scanner.nextLine();
        System.out.print("Введите фамилию доктора: ");
        String surname = scanner.nextLine();

        String[] specializations = SPECIALIZATIONS;
        System.out.println("Выберите специализацию доктора:");
        for (int i = 0; i < specializations.length; i++) {
            System.out.println((i + 1) + ". " + specializations[i]);
        }
        int specChoice = scanner.nextInt();
        scanner.nextLine();
        String doctorType = specializations[specChoice - 1];

        boolean[] workdays = new boolean[7];
        String[] weekDays = WEEK_DAYS;
        for (int i = 0; i < 7; i++) {
            System.out.print("Работает ли доктор в " + weekDays[i] + " (1 - да, 0 - нет): ");
            workdays[i] = scanner.nextInt() == 1;
        }
        scanner.nextLine();

        System.out.println(doctorController.createDoctor(name, surname, doctorType, workdays));
    }

    private void getDoctorById() {
        System.out.print("Введите ID доктора: ");
        int id = scanner.nextInt();
        System.out.println(doctorController.getDoctorById(id));
    }

    private void getAllDoctors() {
        List<Doctor> doctors = doctorController.getAllDoctors();
        doctors.forEach(System.out::println);
    }

    private void addPatient() {
        System.out.print("Введите имя пациента: ");
        String name = scanner.nextLine();
        System.out.print("Введите фамилию пациента: ");
        String surname = scanner.nextLine();
        System.out.println(patientController.createPatient(name, surname));
    }

    private void getPatientById() {
        System.out.print("Введите ID пациента: ");
        int id = scanner.nextInt();
        System.out.println(patientController.getPatientById(id));
    }

    private void getAllPatients() {
        List<Patient> patients = patientController.getAllPatients();
        for(Patient patient : patients)
        {
            System.out.println(patient.getId() + " " + patient.getFullName());
        }
    }

    private void getOrderById() {
        System.out.print("Введите ID заказа: ");
        int id = scanner.nextInt();
        System.out.println(orderController.getOrderById(id));
    }

    private void createOrder() {
        List<Patient> patients = patientController.getAllPatients();
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i));
        }
        System.out.print("Выберите ID пациента из списка: ");
        int patientId = scanner.nextInt();

        String[] specializations = SPECIALIZATIONS;
        System.out.println("Выберите специализацию доктора:");
        for (int i = 0; i < specializations.length; i++) {
            System.out.println((i + 1) + ". " + specializations[i]);
        }
        int specChoice = scanner.nextInt();
        if(specChoice == 0)return;
        scanner.nextLine();
        String doctorType = specializations[specChoice - 1];

        List<Doctor> doctors = doctorController.getAllDoctors();
        doctors.removeIf(doc -> !doc.getDoctorType().equals(doctorType));
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i));
        }
        System.out.print("Выберите ID доктора из списка: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        if(doctorId == 0)return;
        Doctor selectedDoctor = doctors.get(doctorId - 1);
        boolean[] workdays = selectedDoctor.getWorkdays();
        String[] weekDays = WEEK_DAYS;

        System.out.println("Выберите день недели из доступных:");
        for (int i = 0; i < 7; i++) {
            if (workdays[i]) {
                System.out.println((i + 1) + ". " + weekDays[i]);
            }
        }
        int dayChoice = scanner.nextInt();
        scanner.nextLine();
        String weekDay = weekDays[dayChoice - 1];

        System.out.println(orderController.createOrder(patients.get(patientId-1).getId(), doctors.get(doctorId-1).getId(), weekDay, doctorType) ? "Заказ создан" : "Ошибка создания заказа");
    }

    private void getAllOrders() {
        List<Order> orders = orderController.getAllOrders();
        for (Order order : orders)
        {
            System.out.println(order.getId()+"."
                    + patientController.getPatientById(order.getPatientId()).getFullName() + " / "
                    + doctorController.getDoctorById(order.getDoctorId()).getFullName() + " / "
                    + order.getWeekday() + " / "
                    + order.getDoctorType() + " / "
            );
        }
    }
}
