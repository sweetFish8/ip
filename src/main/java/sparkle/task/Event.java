package sparkle.task;

import sparkle.exception.SparkleException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
  protected LocalDateTime from;
  protected LocalDateTime to;
  private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

  public Event(String description, String from, String to) throws SparkleException {
    super(description);
    this.from = parseDateTime(from, true);
    this.to = parseDateTime(to, false);
    if (this.from.isAfter(this.to)) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "Start time must be before end time!");
    }
  }

  public Event(String description, boolean isDone, String from, String to) throws SparkleException {
    super(description, isDone);
    this.from = parseDateTime(from, true);
    this.to = parseDateTime(to, false);
    if (this.from.isAfter(this.to)) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT,
          "Time flows forward, not backward! Fix it!");
    }
  }

  private LocalDateTime parseDateTime(String input, boolean isStart) throws SparkleException {
    String cleanedInput = input.replace(":", "");
    try {
      return LocalDateTime.parse(cleanedInput, DATE_TIME_FORMAT);
    } catch (DateTimeParseException e1) {
      try {
        LocalDate date = LocalDate.parse(cleanedInput, DATE_FORMAT);
        return date.atTime(isStart ? 0 : 23, isStart ? 0 : 59);
      } catch (DateTimeParseException e2) {
        throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT,
            "Invalid date format! Please use yyyy-MM-dd or yyyy-MM-dd HHmm (e.g., 2025-02-15 or 2025-02-15 1000)");
      }
    }
  }

  @Override
  public String toFileFormat() {
    return "E | " + super.toFileFormat() + " | " + from.format(DATE_TIME_FORMAT) + " | " + to.format(DATE_TIME_FORMAT);
  }

  public static Event fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 5) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT,
          "Yikes! Your event data is completely scrambled!");
    }
    return new Event(parts[2], parts[1].equals("1"), parts[3], parts[4]);
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT)
        + ")";
  }
}
