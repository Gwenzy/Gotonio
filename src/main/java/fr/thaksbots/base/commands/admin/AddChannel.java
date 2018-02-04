package fr.thaksbots.base.commands.admin;


import fr.thaksbots.base.Base;
import fr.thaksbots.base.Credentials;
import fr.thaksbots.base.commands.Command;
import fr.thaksbots.base.exceptions.exceptions.ThaksbotException;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.RequestBuffer;

/**
 * Created by Shû~ on 17/11/2017.
 */
public class AddChannel extends Command {

    public AddChannel(String name, String prefix, boolean enabled) {
        super(name, prefix, enabled);
        this.addAuthorizedClient(Credentials.ID_DEVELOPPER);
        this.addAuthorizedClient("282328156659122187");
        this.setHelpContent("Ajoute ce channel comme channel de notifications");
    }

    @Override
    public void handle(MessageReceivedEvent event){
        try {
            if(canBeExecuted(event)){
                if(Base.tn.addChannelId(event.getChannel().getLongID()))
                    RequestBuffer.request(()->event.getChannel().sendMessage("Salon ajouté comme salon de notifications !"));

                else
                    RequestBuffer.request(()->event.getChannel().sendMessage("Salon déjà ajouté"));
            }
        } catch (ThaksbotException e) {
            e.printStackTrace();
        }
    }
}
