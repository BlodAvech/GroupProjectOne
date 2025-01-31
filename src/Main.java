import controllers.interfaces.IDoctorController;
import controllers.interfaces.IPatientController;
import controllers.DoctorController;
import controllers.PatientController;
import data.PostgresDB;
import data.interfaceces.IDB;
import repositories.DoctorRepository;
import repositories.PatientRepository;
import repositories.interfaces.IDoctorRepository;
import repositories.interfaces.IPatientRepository;
//import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "150207", "postgres");

        IPatientRepository pRepo = new PatientRepository(db);
        IPatientController pController = new PatientController(pRepo);
        IDoctorRepository dRepo = new DoctorRepository(db);
        IDoctorController dController = new DoctorController(dRepo);

        MyApplication app = new MyApplication(pController , dController);
        app.Start();
        db.close();
    }
}