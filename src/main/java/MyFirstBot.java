import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class MyFirstBot extends TelegramLongPollingBot  {

    Properties properties = new Properties();

    private String botLogin ;
    private String botToken ;

    public MyFirstBot() throws IOException {
        //GEt token and login from comfiguration file
        try(InputStream inputStream = Files.newInputStream(Paths.get("botconfig.ini"))) {
            properties.load(inputStream);
            this.botLogin = properties.getProperty("login");
            this.botToken = properties.getProperty("token");

        }
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
        System.out.println(message);

    }
}
