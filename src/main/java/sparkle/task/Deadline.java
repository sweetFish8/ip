package sparkle.task;

import sparkle.exception.SparkleException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task that has a specified deadline time.
 * Inherits from the Task class and includes functionality for handling
 * deadlines.
 */
public class Deadline extends Task {

  // Field to store the deadline time
  protected LocalDateTime by;

  // DateTime format patterns used for parsing and formatting dates
  private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

  /**
   * Constructs a Deadline object with the specified description and deadline
   * time.
   * 
   * @param description A brief description of the task.
   * @param by          The deadline in the format "yyyy-MM-dd HHmm" or
   *                    "yyyy-MM-dd".
   * @throws SparkleException If the provided date/time format is invalid.
   */
  public Deadline(String description, String by) throws SparkleException {
    super(description);
    this.by = parseDateTime(by);
  }

  /**
   * Constructs a Deadline object with the specified description, completion
   * status, and deadline time.
   * 
   * @param description A brief description of the task.
   * @param isDone      A boolean indicating whether the task is marked as done.
   * @param by          The deadline in the format "yyyy-MM-dd HHmm" or
   *                    "yyyy-MM-dd".
   * @throws SparkleException If the provided date/time format is invalid.
   */
  public Deadline(String description, boolean isDone, String by) throws SparkleException {
    super(description, isDone);
    this.by = parseDateTime(by);
  }

  /**
   * Parses the deadline time string into a LocalDateTime object.
   * 
   * @param by The deadline string to be parsed.
   * @return A LocalDateTime object corresponding to the deadline.
   * @throws SparkleException If the input string cannot be parsed into a valid
   *                          date/time format.
   */
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

  /**
   * Converts the deadline task into a string format suitable for saving to a
   * file.
   * 
   * @return A string representation of the deadline in a format suitable for
   *         storage.
   */
  @Override
  public String toFileFormat() {
    return "D | " + super.toFileFormat() + " | " + by.format(DATE_TIME_FORMAT);
  }

  /**
   * Creates a Deadline object from a string array formatted for file loading.
   * 
   * @param parts A string array containing deadline data in the format [type,
   *              done, description, by].
   * @return A Deadline object initialized with the data from the array.
   * @throws SparkleException If the input data is in an invalid format.
   */
  public static Deadline fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 4) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "Corrupted Deadline data");
    }
    return new Deadline(parts[2], parts[1].equals("1"), parts[3]);
  }

  /**
   * Returns a string representation of the deadline task, including its
   * description and deadline time.
   * 
   * @return A string representation of the deadline task in the format
   *         "[D][description] (by: [deadline])".
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
  }
}