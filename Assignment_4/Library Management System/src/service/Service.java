package service;

import java.util.Scanner;

public abstract class Service {
    public int readIndex(int size) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String request = scanner.nextLine().trim();
            if (request.matches("\\d+")) {
                int index = Integer.parseInt(request);
                if (size >= index && index >= 0) {
                    return index;
                }
            } else {
                System.out.println("Please type an index between 0 and " + size);
            }
        }
    }
}
