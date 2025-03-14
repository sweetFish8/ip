package sparkle.core;

/**
 * The {@code Parser} class is responsible for processing user input. It splits the input into a
 * command and its associated details.
 */
public class Parser {

  /**
   * Parses the user input into a command and its details.
   *
   * @param userInput The raw user input string.
   * @return An array where the first element is the command and the second element is the details.
   */
  public static String[] parse(String userInput) {
    String[] commandParts = userInput.split(" ", 2);
    String command = commandParts[0].toLowerCase();
    String details = commandParts.length > 1 ? commandParts[1].trim() : "";
    return new String[] {command, details};
  }
}
