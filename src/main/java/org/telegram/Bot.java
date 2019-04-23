package org.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot extends TelegramLongPollingBot {

    private static Logger log = Logger.getLogger(Bot.class.getName());
    private static String botUsername;
    private static String botToken;

    public void onUpdateReceived(Update update) {

        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);

    }

    public synchronized void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText("Хрюхрюхрюхрю"+message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

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


