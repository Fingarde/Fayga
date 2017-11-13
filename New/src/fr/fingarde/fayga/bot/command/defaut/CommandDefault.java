package fr.fingarde.fayga.bot.command.defaut;

import com.sun.corba.se.impl.orb.ParserTable;
import fr.fingarde.fayga.bot.BotDiscord;
import fr.fingarde.fayga.bot.command.Command;
import fr.fingarde.fayga.bot.command.Command. ExecutorType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;

public class CommandDefault {

    private final BotDiscord botDiscord;

    public CommandDefault(BotDiscord botDiscord) {
        this.botDiscord = botDiscord;
    }

    @Command(name="stop", type = ExecutorType.ALL)
    private void stop() {
        botDiscord.setRunning(false);
    }

    @Command(name = "info", type = ExecutorType.USER)
    private void info(User user, MessageChannel channel) {
       if (channel instanceof TextChannel) {
           TextChannel textChannel = (TextChannel)channel;
           if (!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS)) return;
       }

       EmbedBuilder builder = new EmbedBuilder();

       builder.setAuthor(user.getName() + " #" + user.getDiscriminator() , user.getAvatarUrl(), user.getAvatarUrl()+"?size=256");
       builder.setTitle("Information");
       builder.setDescription("[>](1) Le message a été envoyé depuis le channel " + channel.getName());
       builder.addField("Created on", " "+user.getCreationTime(), false);
       builder.setColor(Color.green);

       channel.sendMessage(builder.build()).queue();

    }
}
