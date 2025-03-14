package sparkle.task;

import sparkle.exception.SparkleException;

/**
 * Represents a deadline task that has a specified deadline time. Inherits from the Task class and
 * includes functionality for handling start and end times
 */
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

  /**
   * Creates an Event object from a file format string array.
   *
   * @param parts The string array representing event data.
   * @return The Event object created from the data.
   * @throws SparkleException If the data is in an invalid format.
   */
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

  /**
   * Converts the event to a format suitable for saving to a file.
   *
   * @return The event data as a string for file storage.
   */
  public String toFileFormat() {
    return "E | " + super.toFileFormat() + " | " + from + "-" + to;
  }

  /**
   * Returns a string representation of the event.
   *
   * @return A string representation of the event with start and end times.
   */
  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
  }
}
