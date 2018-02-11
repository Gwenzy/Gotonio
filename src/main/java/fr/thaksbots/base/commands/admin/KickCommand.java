package fr.thaksbots.base.commands.admin;

import fr.thaksbots.base.Base;
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
public class KickCommand extends Command {

    public static HashMap<Long, List<Long>> muted;

    public KickCommand(String name, String prefix, boolean enabled) {
        super(name, prefix, enabled);
        this.addNeededArg("Utilisateur");
        this.addFacultativeArg("Raison");
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
                String raison = getArgMessage(event.getMessage().getFormattedContent(), 1);
                if(event.getGuild().getUsers().contains(Base.logged.getUserByID(longIDF))){

                    if(raison.equals(""))
                        event.getGuild().kickUser(Base.logged.getUserByID(longIDF));
                    else
                        event.getGuild().kickUser(Base.logged.getUserByID(longIDF), raison);
                    RequestBuffer.request(()->event.getChannel().sendMessage("L'utilisateur ID "+longIDF+" a été kick : "+(raison.equals("")?"Pas de raison":raison)));
                } else {
                    RequestBuffer.request(()->event.getChannel().sendMessage("L'utilisateur ID "+longIDF+" n'est pas présent sur le serveur"));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            RequestBuffer.request(()->event.getChannel().sendMessage("Utilisateur inconnu"));
        } catch (ThaksbotException e) {
            e.printStackTrace();
        }
    }
}
