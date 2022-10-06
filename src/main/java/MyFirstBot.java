import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class MyFirstBot extends TelegramLongPollingBot {

    Properties properties = new Properties();

    private String botLogin;
    private String botToken;

    public MyFirstBot() throws IOException {
        //GEt token and login from comfiguration file
        try (InputStream inputStream = Files.newInputStream(Paths.get("botconfig.ini"))) {
//            inputStream.close();
            properties.load(inputStream);
            this.botLogin = properties.getProperty("login");
            this.botToken = properties.getProperty("token");

        }
    }


    void sendMessage(long chatId, String text) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        execute(message);

    }

    void sendPhoto(long chatId, String photoName) throws Exception {
//        var name = new Random().nextInt(2);
        var photo = getClass().getClassLoader().getResourceAsStream(photoName + ".jpg");

        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, "photo"));

        execute(message);
    }


    @Override
    public String getBotUsername() {
        return botLogin;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();
        System.out.println(message);

        try {
            switch (message) {
                case "/ship":
                    sendPhoto(chatId, "ship");
                    break;
                case "/get 20$":
                    sendPhoto(chatId, "$20");
                    break;
                case "/make me happy":
                    sendPhoto(chatId, "1");
                    break;
                default:
                    sendMessage(chatId, "I don't understand");
                    break;
            }

        } catch (Exception e) {

        }


    }
}
