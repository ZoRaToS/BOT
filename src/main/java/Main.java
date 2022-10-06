import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
public class Main {
    public static void main(String[] args) throws Exception {
        var bots = new TelegramBotsApi(DefaultBotSession.class);
        bots.registerBot(new MyFirstBot());
    }
}
