package fr.thaksbots.base.listeners;

import fr.thaksbots.base.Base;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;

/**
 * Created by Shû~ on 05/02/2018.
 */
public class JoinListener extends ThaksbotListener implements IListener<UserJoinEvent> {

    public JoinListener(boolean enabled){
        this.setEnabled( enabled);
    }

    @Override
    public void handle(UserJoinEvent userJoinEvent) {
        if(this.getEnabled())
            Base.logged.getChannelByID(362655929747111936L).sendMessage("Salut "+userJoinEvent.getUser().mention()+" ! Bienvenue sur le serveur **Gotonio Family™**. Pense à lire le règlement, et amuse-toi !");

    }
}
