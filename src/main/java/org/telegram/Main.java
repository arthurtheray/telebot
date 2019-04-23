package org.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger log = Logger.getLogger(Bot.class.getName());
    public static String botUsername;
    public static String botToken;

    public static void main(String[] args) {

        if (args.length != 2) {
            log.log(Level.SEVERE, "Error: missing arguments");
            return;
        }

        botUsername = args[0];
        botToken = args[1];

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
