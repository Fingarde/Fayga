package fr.fingarde.fayga.bot.command.defaut;



import fr.fingarde.fayga.bot.BotDiscord;
import fr.fingarde.fayga.bot.command.Command;
import net.dv8tion.jda.client.entities.Application;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.entities.impl.JDAImpl;
import net.dv8tion.jda.core.events.Event;

import java.awt.*;

import static fr.fingarde.fayga.bot.BotDiscord.jda;

public class CommandDefault {

    private final BotDiscord botDiscord;

    public CommandDefault(BotDiscord botDiscord) {
        this.botDiscord = botDiscord;
    }


    @Command(name="stop", type = Command.ExecutorType.ALL)
    private void stop() {
        botDiscord.setRunning(false);
    }

    @Command(name = "help", type = Command.ExecutorType.USER)
    private void help(Guild user, MessageChannel channel) {
       if (channel instanceof TextChannel) {
           TextChannel textChannel = (TextChannel)channel;
           if (!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS)) return;
       }

       EmbedBuilder builder = new EmbedBuilder();

       builder.setAuthor( user.getName() + " #" + user.getJDA().getSelfUser().getDiscriminator() , user.getJDA().getSelfUser().getAvatarUrl(), user.getJDA().getSelfUser().getAvatarUrl()+"?size=256");
       builder.setTitle("Information");
       builder.setDescription("[>](1) Le message a été envoyé depuis le channel " + channel.getName());
       builder.addField("Commands :", "prefix = '!'", false);
       builder.addField("help :", "Display all commands", false);
       builder.addField("game :", "Set current game", false);
       builder.addField("getgame :", "Display current game", false);
       builder.setColor(Color.green);

       channel.sendMessage(builder.build()).queue();

    }

    @Command(name= "game")
    private void game(JDA jda, String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String str : args){
            if (builder.length() > 0) builder.append(" ");
            builder.append(str);
        }
        jda.getPresence().setGame(Game.of(builder.toString(), null));
    }

    @Command(name = "getgame", type = Command.ExecutorType.USER)
    private void getgame(Guild user, MessageChannel channel) {
        if (channel instanceof TextChannel) {
            TextChannel textChannel = (TextChannel)channel;
            if (!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) return;
        }
        channel.sendMessage(user.getJDA().getSelfUser().getName()).queue();
    }
}
