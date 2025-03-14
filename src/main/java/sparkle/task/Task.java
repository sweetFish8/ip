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

  public String getDescription() {
    return description;
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
    char type = parts[0].charAt(0);

    switch (type) {
      case 'T':
        return Todo.fromFileFormat(parts);
      case 'D':
        return Deadline.fromFileFormat(parts);
      case 'E':
        return Event.fromFileFormat(parts);
      default:
        throw new SparkleException(
            SparkleException.ErrorType.INVALID_FORMAT,
            "Oops! This task data's a mess—good luck fixing that!");
    }
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }
}
