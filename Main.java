package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static final String FILE_NAME = "superbowls.txt";
    private static ArrayList<Superbowl> superbowls = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        runMenu();
    }

    // ---------------------------
    // LOAD DATA FROM FILE
    // ---------------------------
    private static void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                int year = Integer.parseInt(parts[0].substring(0, 4));
                String date = parts[0].substring(5, parts[0].length() - 1);
                String superbowlNumber = parts[1];
                String winningTeam = parts[2];
                int winningPoints = Integer.parseInt(parts[3]);
                String losingTeam = parts[4];
                int losingPoints = Integer.parseInt(parts[5]);
                String mvp = parts[6];
                String stadium = parts[7];
                String city = parts[8];
                String state = parts[9];

                superbowls.add(new Superbowl(
                        year, date, superbowlNumber,
                        winningTeam, winningPoints,
                        losingTeam, losingPoints,
                        mvp, stadium, city, state
                ));
            }

            System.out.println("Data loaded successfully: " + superbowls.size() + " entries.");

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // ---------------------------
    // MAIN MENU LOOP
    // ---------------------------
    private static void runMenu() {
        int choice;

        do {
            System.out.println("-----------------------");
            System.out.println(" NFL Superbowls menu ");
            System.out.println("-----------------------");
            System.out.println("List .................1");
            System.out.println("Select ...............2");
            System.out.println("Search................3");
            System.out.println("Exit..................0");
            System.out.println("-----------------------");
            System.out.print("Enter choice:> ");

            choice = getInt();

            switch (choice) {
                case 1: listSuperbowls(); break;
                case 2: selectYear(); break;
                case 3: searchMenu(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice."); 
            }

        } while (choice != 0);
    }

    // ---------------------------
    // OPTION 1: LIST BY YEAR RANGE
    // ---------------------------
    private static void listSuperbowls() {

        System.out.print("Enter start year > ");
        int start = getInt();

        System.out.print("Enter end year > ");
        int end = getInt();

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-13s | %-20s | %-20s |\n", "Year", "Superbowl No.", "Champions", "Runners-up");
        System.out.println("--------------------------------------------------------------------------------");

        for (Superbowl sb : superbowls) {
            if (sb.getYear() >= start && sb.getYear() <= end) {
                System.out.printf("| %-4d | %-13s | %-20s | %-20s |\n",
                        sb.getYear(),
                        sb.getSuperbowlNumber(),
                        sb.getWinningTeam(),
                        sb.getLosingTeam()
                );
            }
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    // ---------------------------
    // OPTION 2: SELECT YEAR
    // ---------------------------
    private static void selectYear() {
        System.out.print("Enter championship year > ");
        int year = getInt();

        for (Superbowl sb : superbowls) {
            if (sb.getYear() == year) {
                System.out.println(sb.toString());
                return;
            }
        }

        System.out.println("No Superbowl found for that year.");
    }

    // ---------------------------
    // OPTION 3: SEARCH SUB-MENU
    // ---------------------------
    private static void searchMenu() {
        int choice;

        do {
            System.out.println("-----------------------");
            System.out.println("Search superbowls by:");
            System.out.println("-----------------------");
            System.out.println("Team .................1");
            System.out.println("State................2");
            System.out.println("Main menu.............0");
            System.out.println("-----------------------");
            System.out.print("Enter choice:> ");

            choice = getInt();

            switch (choice) {
                case 1: searchByTeam(); break;
                case 2: searchByState(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    // ---------------------------
    // SEARCH TEAM
    // ---------------------------
    private static void searchByTeam() {
        System.out.print("Enter search term for NFL team > ");
        String term = scanner.nextLine().toLowerCase();

        ArrayList<String> details = new ArrayList<>();
        String teamName = null;

        for (Superbowl sb : superbowls) {
            if (sb.getWinningTeam().toLowerCase().contains(term)) {
                if (teamName == null) teamName = sb.getWinningTeam();
                details.add(sb.getYear() + " (" + sb.getSuperbowlNumber() + "), Winner");
            }
            if (sb.getLosingTeam().toLowerCase().contains(term)) {
                if (teamName == null) teamName = sb.getLosingTeam();
                details.add(sb.getYear() + " (" + sb.getSuperbowlNumber() + "), Runner-up");
            }
        }

        if (teamName == null) {
            System.out.println("No matching team found.");
            return;
        }

        Collections.sort(details);

        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-15s | %-20s |\n", "Team", "No. appearances", "Details");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-15d | \n", teamName, details.size());

        for (String s : details) {
            System.out.printf("| %-20s | %-15s | %-20s |\n", "", "", s);
        }

        System.out.println("---------------------------------------------------------------------------");
    }

    // ---------------------------
    // SEARCH STATE
    // ---------------------------
    private static void searchByState() {
        System.out.print("Enter search term for U.S. state > ");
        String term = scanner.nextLine().toLowerCase();

        ArrayList<Superbowl> matches = new ArrayList<>();

        for (Superbowl sb : superbowls) {
            if (sb.getState().toLowerCase().contains(term)) {
                matches.add(sb);
            }
        }

        if (matches.isEmpty()) {
            System.out.println("No Superbowl hosted in that state.");
            return;
        }

        matches.sort(Comparator.comparingInt(Superbowl::getYear));

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-30s |\n", "State", "Superbowl", "City & Stadium");
        System.out.println("----------------------------------------------------------------------------------------");

        String state = matches.get(0).getState();

        for (Superbowl sb : matches) {
            System.out.printf("| %-10s | %-10s | %-30s |\n",
                    state,
                    sb.getSuperbowlNumber() + " (" + sb.getYear() + ")",
                    sb.getCity() + ", " + sb.getStadium()
            );
        }

        System.out.println("----------------------------------------------------------------------------------------");
    }

    // ---------------------------
    // INPUT VALIDATION
    // ---------------------------
    private static int getInt() {
        while (true) {
            try {
                String line = scanner.nextLine();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }
}
