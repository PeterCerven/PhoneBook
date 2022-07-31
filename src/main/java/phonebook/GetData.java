package phonebook;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GetData {
    private final ArrayList<String> names;
    private final ArrayList<phonebook.Person> people;


    public GetData(String directory, String find) {
        this.names = new ArrayList<>();
        this.people = new ArrayList<>();
        getData(directory, find);

    }

    public ArrayList<phonebook.Person> getPairs() {
        return people;
    }


    public ArrayList<String> getNames() {
        return names;
    }


    private void getData(String directory, String find) {
        try {
            getDirectoryData(directory);
            getFindData(find);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getDirectoryData(String directory) throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/" + directory);
        Scanner bigFile = new Scanner((fileReader));
        while (bigFile.hasNextLine()) {
            String line = bigFile.nextLine();
            String name = line.substring(line.indexOf(" ") + 1);
            Integer number = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            people.add(new Person(number, name));
        }
        bigFile.close();
    }

    private void getFindData(String find) throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/" + find);
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()) {
            names.add(scanner.nextLine());
        }
        scanner.close();
    }

}
