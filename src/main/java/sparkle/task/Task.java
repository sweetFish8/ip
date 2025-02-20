package sparkle.task;

import sparkle.exception.SparkleException;

public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }

  public String toFileFormat() {
    return (isDone ? "1" : "0") + " | " + description;
  }

  public static Task fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 2) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "Corrupted Task data");
    }

    boolean isDone = parts[0].equals("1");
    String description = parts[1];

    return new Task(description, isDone);
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }
}
