import controllers.interfaces.IDoctorController;
import controllers.interfaces.IPatientController;
import controllers.DoctorController;
import controllers.PatientController;
import data.PostgresDB;
import data.interfaceces.IDB;
//import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "150207", "postgres");

        db.close();
    }
}