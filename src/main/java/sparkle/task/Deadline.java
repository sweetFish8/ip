package sparkle.task;

import sparkle.exception.SparkleException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
  protected LocalDateTime by;
  private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

  public Deadline(String description, String by) throws SparkleException {
    super(description);
    this.by = parseDateTime(by);
  }

  public Deadline(String description, boolean isDone, String by) throws SparkleException {
    super(description, isDone);
    this.by = parseDateTime(by);
  }

  private LocalDateTime parseDateTime(String by) throws SparkleException {
    String cleanedBy = by.replace(":", "");
    try {
      return LocalDateTime.parse(cleanedBy, DATE_TIME_FORMAT);
    } catch (DateTimeParseException e1) {
      try {
        LocalDate date = LocalDate.parse(cleanedBy, DATE_FORMAT);
        return date.atTime(23, 59);
      } catch (DateTimeParseException e2) {
        throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT,
            "Invalid date format! Please use yyyy-MM-dd or yyyy-MM-dd HHmm (e.g., 2025-02-15 or 2025-02-15 1000)");
      }
    }
  }

  @Override
  public String toFileFormat() {
    return "D | " + super.toFileFormat() + " | " + by.format(DATE_TIME_FORMAT);
  }

  public static Deadline fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 4) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "Corrupted Deadline data");
    }
    return new Deadline(parts[2], parts[1].equals("1"), parts[3]);
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
  }
}
