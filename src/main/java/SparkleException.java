class SparkleException extends Exception {

  public enum ErrorType {
    EMPTY_TASK_DESCRIPTION,
    UNKNOWN_COMMAND,
    INVALID_TASK_NUMBER,
    INVALID_FORMAT
  }

  public SparkleException(ErrorType type, String details) {
    super(generateMessage(type, details));
  }

  private static String generateMessage(ErrorType type, String details) {
    switch (type) {
      case EMPTY_TASK_DESCRIPTION:
        return details
            + " Nothing in the description! At least give me some fireworks to set off...";
      case UNKNOWN_COMMAND:
        return details + "? Huh? That's just noise! Say something that actually makes sense~";
      case INVALID_TASK_NUMBER:
        return "That task number's playing hide and seek~ Try again!";
      case INVALID_FORMAT:
        return details;
      default:
        return "An unknown error occurred.";
    }
  }
}
