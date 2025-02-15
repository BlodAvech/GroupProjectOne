import controllers.interfaces.IDoctorController;
import controllers.interfaces.IOrderController;
import controllers.interfaces.IPatientController;
import models.Doctor;
import models.Patient;
import models.Order;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ADMIN_PASSWORD = "123456"; // Установленный пароль администратора
    private static final String[] SPECIALIZATIONS = {"Терапевт", "Ортопед", "Хирург", "Пародонтолог", "Ортодонт", "Детский стоматолог"};
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
            System.out.println("\nВыберите роль:");
            System.out.println("1. Администратор");
            System.out.println("2. Пациент");
            System.out.println("0. Выйти");

            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            switch (roleChoice) {
                case 1 -> {
                    if (validateAdminPassword()) {
                        adminMenu();
                    } else {
                        System.out.println("Неверный пароль. Возврат в главное меню...");
                    }
                }
                case 2 -> patientMenu();
                case 0 -> {
                    System.out.println("Выход из программы...");
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    private boolean validateAdminPassword() {
        System.out.print("Введите пароль администратора: ");
        String inputPassword = scanner.nextLine();
        return inputPassword.equals(ADMIN_PASSWORD);
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\nАдмин-панель:");
            System.out.println("1. Добавить доктора");
            System.out.println("2. Найти доктора по ID");
            System.out.println("3. Показать всех докторов");
            System.out.println("4. Добавить пациента");
            System.out.println("5. Найти пациента по ID");
            System.out.println("6. Показать всех пациентов");
            System.out.println("7. Создать заказ");
            System.out.println("8. Найти заказ по ID");
            System.out.println("9. Показать все заказы");
            System.out.println("10. Показать пациентов по убыванию");
            System.out.println("0. Вернуться в главное меню");

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
                case 10 -> getPatientsAsc();
                case 0 -> {
                    System.out.println("Возврат в главное меню...");
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    private void patientMenu() {
        while (true) {
            System.out.println("\nМеню пациента:");
            System.out.println("1. Зарегистрироваться как пациент");
            System.out.println("2. Создать заказ");
            System.out.println("3. Найти доктора по ID");
            System.out.println("0. Вернуться в главное меню");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> createOrder();
                case 3 -> getDoctorById();
                case 0 -> {
                    System.out.println("Возврат в главное меню...");
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    private void addDoctor() {
        System.out.print("Введите имя доктора: ");
        String name = scanner.nextLine();
        System.out.print("Введите фамилию доктора: ");
        String surname = scanner.nextLine();

        System.out.println("Выберите специализацию доктора:");
        for (int i = 0; i < SPECIALIZATIONS.length; i++) {
            System.out.println((i + 1) + ". " + SPECIALIZATIONS[i]);
        }
        int specChoice = scanner.nextInt();
        scanner.nextLine();
        String doctorType = SPECIALIZATIONS[specChoice - 1];

        boolean[] workdays = new boolean[7];
        for (int i = 0; i < 7; i++) {
            System.out.print("Работает ли доктор в " + WEEK_DAYS[i] + " (1 - да, 0 - нет): ");
            workdays[i] = scanner.nextInt() == 1;
        }
        scanner.nextLine();

        System.out.println(doctorController.createDoctor(name, surname, doctorType, workdays));
    }

    private void getDoctorById() {
        System.out.print("Введите ID доктора: ");
        int id = scanner.nextInt();
        scanner.nextLine();
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
        scanner.nextLine();
        System.out.println(patientController.getPatientById(id));
    }

    private void getAllPatients() {
        List<Patient> patients = patientController.getAllPatients();
        patients.forEach(patient -> System.out.println(patient.getId() + " " + patient.getFullName()));
    }

    private void createOrder() {
        List<Patient> patients = patientController.getAllPatients();
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i));
        }
        System.out.print("Выберите ID пациента из списка: ");
        int patientId = scanner.nextInt();

        System.out.println("Выберите специализацию доктора:");
        for (int i = 0; i < SPECIALIZATIONS.length; i++) {
            System.out.println((i + 1) + ". " + SPECIALIZATIONS[i]);
        }
        int specChoice = scanner.nextInt();
        scanner.nextLine();
        String doctorType = SPECIALIZATIONS[specChoice - 1];

        List<Doctor> doctors = doctorController.getAllDoctors();
        doctors.removeIf(doc -> !doc.getDoctorType().equals(doctorType));
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i));
        }
        System.out.print("Выберите ID доктора из списка: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        boolean[] workdays = doctors.get(doctorId - 1).getWorkdays();
        System.out.println("Выберите день недели из доступных:");
        for (int i = 0; i < 7; i++) {
            if (workdays[i]) {
                System.out.println((i + 1) + ". " + WEEK_DAYS[i]);
            }
        }
        int dayChoice = scanner.nextInt();
        scanner.nextLine();
        String weekDay = WEEK_DAYS[dayChoice - 1];

        System.out.println(orderController.createOrder(patientId, doctorId, weekDay, doctorType) ? "Заказ создан" : "Ошибка создания заказа");
    }

    private void getOrderById() {
        System.out.print("Введите ID заказа: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(orderController.getOrderById(id));
    }

    private void getAllOrders() {
        List<Order> orders = orderController.getAllOrders();
        for (Order order : orders) {
            System.out.println(order.getId() + ". "
                    + patientController.getPatientById(order.getPatientId()).getFullName() + " / "
                    + doctorController.getDoctorById(order.getDoctorId()).getFullName() + " / "
                    + order.getWeekday() + " / "
                    + order.getDoctorType());
        }
    }

    private void getPatientsAsc()
    {
        List<Patient> patients = patientController.getAllPatients();
        patients.stream()
                .sorted(Comparator.comparing(p -> p.getName())) // Используем name
                .forEach(System.out::println);
    }
}
