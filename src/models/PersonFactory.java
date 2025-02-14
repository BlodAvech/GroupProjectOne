package models;

public class PersonFactory {
    private static final PersonFactory INSTANCE = new PersonFactory();

    private PersonFactory() {}

    public static PersonFactory getInstance() {
        return INSTANCE;
    }

    public enum PersonType {
        Doctor,
        Patient
    }

    public  PersonType[] getPersonTypes() {
        return PersonType.values();
    }

    public static Person createPerson(PersonType type) {
        return switch (type) {
            case Doctor -> new Doctor();
            case Patient -> new Patient();
            default -> new Person();
        };
    }
}
