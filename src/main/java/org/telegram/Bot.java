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

        if (update.getMessage().hasText()) {
            String command = update.getMessage().getText();
            sendReply(update.getMessage().getChatId().toString(), command);
        }

        if (update.getMessage().hasPhoto()) {
            sendReply(update.getMessage().getChatId().toString(), "фото");
        }

        if (!update.getMessage().isGroupMessage() && !update.getMessage().isChannelMessage()) {
            sendReply(update.getMessage().getChatId().toString(), " Добовьте бота на канал или в группу");
        }

        if (update.getMessage().isChannelMessage() || update.getMessage().isGroupMessage()) {
            sendReply(update.getMessage().getChatId().toString(), " бот на канале (или в группе");
        }

    }

    private synchronized void sendReply(String chatId, String command) {
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
        return Main.getBotUsername();
    }

    public String getBotToken() {
        return Main.getBotToken();
    }


}


