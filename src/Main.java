import controllers.interfaces.IDoctorController;
import controllers.interfaces.IPatientController;
import controllers.DoctorController;
import controllers.PatientController;
import data.PostgresDB;
import data.interfaceces.IDB;
import repositories.PatientRepository;
import repositories.interfaces.IPatientRepository;
//import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "150207", "postgres");

        IPatientRepository pRepo = new PatientRepository(db);
        IPatientController pController = new PatientController(pRepo);

        pController.createPatient("Erkhan" , "Piriyev" , "Akerke" , "Monday" , "9:00");

        db.close();
    }
}