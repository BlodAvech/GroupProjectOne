import controllers.OrderController;
import controllers.interfaces.IDoctorController;
import controllers.interfaces.IOrderController;
import controllers.interfaces.IPatientController;
import controllers.DoctorController;
import controllers.PatientController;
import data.PostgresDB;
import data.interfaceces.IDB;
import models.Doctor;
import models.Order;
import repositories.DoctorRepository;
import repositories.OrderRepository;
import repositories.PatientRepository;
import repositories.interfaces.IDoctorRepository;
import repositories.interfaces.IOrderRepository;
import repositories.interfaces.IPatientRepository;
//import repositories.interfaces.IUserRepository;
public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "150207", "postgres");

        IPatientRepository pRepo = new PatientRepository(db);
        IPatientController pController = new PatientController(pRepo);
        IDoctorRepository dRepo = new DoctorRepository(db);
        IDoctorController dController = new DoctorController(dRepo);
        IOrderRepository oRepo = new OrderRepository(db);
        IOrderController oController = new OrderController(oRepo);



        MyApplication app = new MyApplication(pController , dController , oController);
        app.start();
        db.close();
    }
}