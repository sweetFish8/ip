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

    public Task deleteTask(int taskNumber) throws SparkleException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        Task deleteTask = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        return deleteTask;
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

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public int size() {
        return tasks.size();
    }
}
