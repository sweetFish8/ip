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

  public static Event fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 4) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "Corrupted Event data");
    }
    String[] fromTo = parts[3].split("-", 2);
    if (fromTo.length < 2) {
      throw new SparkleException(
          SparkleException.ErrorType.INVALID_FORMAT, "Invalid Event time format");
    }
    return new Event(parts[2], parts[1].equals("1"), fromTo[0], fromTo[1]);
  }

  public String toFileFormat() {
    return "E | " + super.toFileFormat() + " | " + from + "-" + to;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
  }
}
