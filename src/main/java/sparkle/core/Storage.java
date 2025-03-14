package sparkle.core;

import sparkle.exception.SparkleException;
import sparkle.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "./data/sparkle.txt";
    private static final String DIRECTORY_PATH = "./data";

    public ArrayList<Task> load() throws SparkleException {
        ArrayList<Task> newtasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return newtasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                newtasks.add(Task.fromFileFormat(parts));
            }
        } catch (FileNotFoundException e) {
            throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "That file's playing hide and seek… and winning!");
        }
        return newtasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            new File(DIRECTORY_PATH).mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("    Task list just went poof! Try saving again!");
        }
    }
}
