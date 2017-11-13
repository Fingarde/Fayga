package fr.fingarde.fayga.bot;

import fr.fingarde.fayga.bot.command.CommandMap;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class BotDiscord implements Runnable{

    public static JDA jda;
    private final CommandMap commandMap = new CommandMap(this);
    private final Scanner scanner = new Scanner(System.in);

    private boolean running;

    public BotDiscord() throws LoginException, IllegalArgumentException, RateLimitedException{
        jda = new JDABuilder(AccountType.BOT).setToken("Mjg5ODAyMzgwMTM1MTA0NTEy.DOdgRQ.xf8jVuYuXJXJk1vrAtX422nqkuQ").buildAsync();
        jda.addEventListener(new BotListener(commandMap));
        System.out.println("Bot Connected");
    }

    public static JDA getJda() {
        return jda;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        running = true;
        while (running) {
            if (scanner.hasNextLine()) commandMap.commandConsole(scanner.nextLine());
        }

        scanner.close();
        System.out.println("Bot stopped.");
        jda.shutdown();
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            BotDiscord botDiscord = new BotDiscord();
            new Thread(botDiscord, "bot").start();
        } catch (LoginException | IllegalArgumentException| RateLimitedException e) {
            e.printStackTrace();

        }

    }

}
