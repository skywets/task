import model.Patient;
import model.Root;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {


        while (true) {
            Scanner scanner = new Scanner(System.in);
            Parser parser = new Parser();
            System.out.println("Enter the name file.xml!");
            String fileName = scanner.nextLine();
            if (fileName.equals("")) {
                System.exit(0);
            }
            Root root = parser.parse(fileName);
            SortingByData sortingByData = new SortingByData(root);
            if (root != null) {
                System.out.println("Sorting byName: Enter word like this 'name' ");
                System.out.println("Sorting byBirthday: Enter word like this 'birthday' ");
                System.out.println("Press zero to exit the program");
                String answer = scanner.nextLine();
                if (answer.equals("")) {
                    getAll(root);
                }
                try {
                    switch (answer) {
                        case "name":
                            printSortingDataByName(sortingByData, root);
                            break;
                        case "birthday":
                            printSortingDataByBirthday(sortingByData, root);
                            break;
                        case "0":
                            System.exit(Integer.parseInt(answer));
                        default:
                            System.out.println("There is no such command! Try again!");
                    }
                } catch (MissingFormatArgumentException e) {
                    System.out.println("There is no such command! Try again!");
                }
            }

        }
    }

    public static void printSortingDataByName(SortingByData sortingByData, Root root) {
        List<Patient> patientList = sortingByData.sortByName(root);
        for (Patient people : patientList) {
            System.out.println(people);
        }
    }

    public static void printSortingDataByBirthday(SortingByData sortingByData, Root root) {
        List<Patient> patientList = sortingByData.sortByBirthday(root);
        for (Patient people : patientList) {
            System.out.println(people);
        }
    }

    public static void getAll(Root root) {
        List<Patient> patientList = root.getPatientList();
        for (Patient people : patientList) {
            System.out.println(people);
        }
    }
}



