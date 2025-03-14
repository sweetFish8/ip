package sparkle.core;

import sparkle.exception.SparkleException;
import java.util.ArrayList;
import sparkle.task.Task;


public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) throws SparkleException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        tasks.remove(taskNumber);
    }

    public void markTaskAsDone(int taskNumber) throws SparkleException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        tasks.get(taskNumber).markAsDone();
    }

    public void markTaskAsUndone(int taskNumber) throws SparkleException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        tasks.get(taskNumber).markAsUndone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
