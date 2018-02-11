package fr.thaksbots.base.listeners;

import fr.thaksbots.base.commands.admin.MuteCommand;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by Shû~ on 08/02/2018.
 */
public class MessageListener extends ThaksbotListener implements IListener<MessageReceivedEvent> {

    public MessageListener(boolean enabled) {
        this.setEnabled(enabled);
    }

    @Override
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(MuteCommand.muted.get(messageReceivedEvent.getGuild().getLongID()).contains(messageReceivedEvent.getAuthor().getLongID()))
            messageReceivedEvent.getMessage().delete();
    }
}
