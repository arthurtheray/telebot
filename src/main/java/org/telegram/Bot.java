package org.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot extends TelegramLongPollingBot {

    private static Logger log = Logger.getLogger(Bot.class.getName());

    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), command);

    }

    public synchronized void sendMsg(String chatId, String command) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(Commands.newCommand(command));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    public String getBotUsername() {
        return Main.botUsername;
    }

    public String getBotToken() {
        return Main.botToken;
    }


}


