package fr.thaksbots.base.listeners;

import fr.thaksbots.base.Base;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.member.UserLeaveEvent;

/**
 * Created by Shû~ on 08/02/2018.
 */
public class LeaveListener extends ThaksbotListener implements IListener<UserLeaveEvent> {

    public LeaveListener(boolean enabled){
        this.setEnabled(enabled);
    }
    @Override
    public void handle(UserLeaveEvent userLeaveEvent) {

         Base.logged.getChannelByID(362655929747111936L).sendMessage("**"+userLeaveEvent.getUser().mention()+"** a quitté la **Gotonio Family™**. Bye bye, et ne reviens plus !");

    }
}
