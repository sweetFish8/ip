package sparkle.core;

public class Parser {

    public static String[] parse(String userInput) {
        String[] commandParts = userInput.split(" ", 2);
        String command = commandParts[0].toLowerCase();
        String details = commandParts.length > 1 ? commandParts[1].trim() : "";
        return new String[]{command, details};
    }
}
