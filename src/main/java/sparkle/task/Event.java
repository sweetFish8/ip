package sparkle.task;

import sparkle.exception.SparkleException;

public class Event extends Task {
  protected String from;
  protected String to;

  public Event(String description, String from, String to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  public Event(String description, boolean isDone, String from, String to) {
    super(description, isDone);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toFileFormat() {
    return "E | " + super.toFileFormat();
  }

  public static Event fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 5) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "Corrupted Event data");
    }
    return new Event(parts[2], parts[1].equals("1"), parts[3], parts[4]);
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
  }
}
