package fr.thaksbots.base.commands.admin;

import fr.thaksbots.base.Credentials;
import fr.thaksbots.base.commands.Command;
import fr.thaksbots.base.exceptions.exceptions.ThaksbotException;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.RequestBuffer;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Shû~ on 08/02/2018.
 */
public class MuteCommand extends Command {

    public static HashMap<Long, List<Long>> muted;

    public MuteCommand(String name, String prefix, boolean enabled) {
        super(name, prefix, enabled);
        this.addNeededArg("Utilisateur");
        this.addAuthorizedGroup("362655159446142988");
        this.addAuthorizedGroup("362653877264187393");
        this.addAuthorizedClient(Credentials.ID_DEVELOPPER);

    }

    @Override
    public void handle(MessageReceivedEvent event){
        try {
            if(canBeExecuted(event)){
                Long longID;
                try{
                    longID = Long.parseLong(getArgs(event.getMessage().getFormattedContent())[0]);

                }catch(NumberFormatException e){
                    if(event.getMessage().getMentions().size()==0)
                        longID = (event.getGuild().getUsersByName(getArgs(event.getMessage().getFormattedContent())[0]).get(0)).getLongID();
                    else
                        longID = event.getMessage().getMentions().get(0).getLongID();
                }

                final long longIDF = longID;
                if(muted.get(event.getGuild().getLongID()).contains(longID)){
                    muted.get(event.getGuild().getLongID()).remove(longID);
                    RequestBuffer.request(()->event.getChannel().sendMessage("L'utilisateur ID "+longIDF+" peut parler"));
                } else {
                    muted.get(event.getGuild().getLongID()).add(longID);
                    RequestBuffer.request(()->event.getChannel().sendMessage("L'utilisateur ID "+longIDF+" ne peut plus parler"));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            RequestBuffer.request(()->event.getChannel().sendMessage("Utilisateur inconnu"));
        } catch (ThaksbotException e) {
            e.printStackTrace();
        }
    }
}
