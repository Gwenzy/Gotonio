package fr.thaksbots.base.listeners;

import fr.thaksbots.base.Base;
import fr.thaksbots.base.commands.admin.MuteCommand;
import fr.thaksbots.base.notifs.ThreadNotifs;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IGuild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

/**
 * Created by gwend on 15/11/2017.
 */
public class ReadyListener extends ThaksbotListener implements IListener<ReadyEvent> {
    public ReadyListener(boolean enabled){
        this.setEnabled(enabled);
    }

    @Override
    public void handle(ReadyEvent event) {
        if(this.getEnabled()){
            Base.logged.online();
            Base.logged.changePlayingText(Base.cm.getCommand("help").getFullCommand());
            Timer t = new Timer();

            Base.tn = new ThreadNotifs();
            t.schedule( Base.tn, 0, 5000);

            MuteCommand.muted = new HashMap<>();
            for(IGuild g : Base.logged.getGuilds()){
                MuteCommand.muted.put(g.getLongID(), new ArrayList<>());
            }
        }
    }
}
