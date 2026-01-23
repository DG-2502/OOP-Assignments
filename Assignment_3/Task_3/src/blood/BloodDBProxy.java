package blood;

import Main.BloodDB;
import Main.BloodSample;
import Main.DB;
import Main.SecuritySystem;

import java.util.List;
import java.util.Scanner;

public class BloodDBProxy implements DB {
    private final SecuritySystem security;
    private BloodDB db;
    private final BloodSample denied = new BloodSample(0, "access", "denied", null, null, null);

    public BloodDBProxy() {
        this.security = new SecuritySystem();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter login: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        security.authorize(login, password);
        if (security.isAuthorized()) {
            this.db = new BloodDB();
        }
    }

    @Override
    public BloodSample getById(int id) {
        if (db != null) {
            return db.getById(id);
        }
        return denied;
    }

    @Override
    public List<BloodSample> find(String request) {
        if (db != null) {
            return db.find(request);
        }
        return List.of(denied);
    }
}
