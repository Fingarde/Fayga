package fr.fingarde.fayga.bot;

import fr.fingarde.fayga.bot.command.Command;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;


public class MyCommand {

    @Command(name="help",description="Montre les commandes du bot.",type= Command.ExecutorType.USER)
    public void onHelp(User user, String[] args, Message message, Guild guild, PrivateChannel privateChannel) {

    }
}
