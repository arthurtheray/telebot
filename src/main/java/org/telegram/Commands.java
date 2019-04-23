package org.telegram;

public class Commands {
    public static String newCommand(String command) {
        if (command.equals("/start")) {
            return "Хрю! \n Хрюхрю хрю: /help \nХрюХрю хрюххрр хрюю!";
        }
        if (command.equals("/help")) {
            return "Хрю";
        }

        return "Хрю" + command;
    }
}
