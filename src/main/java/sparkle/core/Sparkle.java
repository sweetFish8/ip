package sparkle.core;

import sparkle.task.Task;
import sparkle.exception.SparkleException;

import java.util.Scanner;

public class Sparkle {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Sparkle(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (SparkleException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showLogo();
        ui.showGreeting();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String userInput = scanner.nextLine().trim();
                String[] commandParts = Parser.parse(userInput);
                String command = commandParts[0];
                String details = commandParts[1];

                try {
                    switch (command) {
                        case "bye":
                            storage.save(tasks.getTasks());
                            ui.showExitMessage();
                            return;
                        case "list":
                            ui.showTaskList(tasks.getTasks(), tasks.size());
                            break;
                        case "mark":
                            tasks.markTaskAsDone(Integer.parseInt(details) - 1);
                            ui.showTaskList(tasks.getTasks(), tasks.size());
                            break;
                        case "unmark":
                            tasks.markTaskAsUndone(Integer.parseInt(details) - 1);
                            ui.showTaskList(tasks.getTasks(), tasks.size());
                            break;
                        case "todo":
                            tasks.addTask(new Task(details));
                            ui.showTaskAdded(tasks.getTasks().get(tasks.size() - 1), tasks.size());
                            break;
                        default:
                            throw new SparkleException(SparkleException.ErrorType.UNKNOWN_COMMAND, command);
                    }
                } catch (SparkleException e) {
                    ui.showError(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Sparkle("data/sparkle.txt").run();
    }
}
