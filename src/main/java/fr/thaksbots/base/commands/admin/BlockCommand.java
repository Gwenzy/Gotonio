package fr.thaksbots.base.commands.admin;


import fr.thaksbots.base.Base;
import fr.thaksbots.base.Credentials;
import fr.thaksbots.base.commands.Command;
import fr.thaksbots.base.exceptions.exceptions.MissingArgsException;
import fr.thaksbots.base.exceptions.exceptions.ThaksbotException;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shû~ on 17/11/2017.
 */
public class BlockCommand extends Command {
    private static List<Long> blocked;

    public BlockCommand(String name, String prefix, boolean enabled) {
        super(name, prefix, enabled);
        this.addAuthorizedClient(Credentials.ID_DEVELOPPER);
        this.addNeededArg("ID");
        this.setHelpContent("Blocks "+ Base.cm.getCommand("dev").getFullCommand()+" command for someone");
        this.setAcceptPrivate(true);
        blocked = new ArrayList<>();
    }

    public static boolean isBlocked(IUser user){
        return blocked.contains(user.getLongID());
    }
    @Override
    public void handle(MessageReceivedEvent event){
        try {
            if(canBeExecuted(event)){
                long ID = Long.parseLong(getArgs(event.getMessage().getFormattedContent())[0]);
                if(blocked.contains(ID)) {
                    blocked.remove(ID);
                    event.getChannel().sendMessage("User "+ID+" is now unblocked from dev command");
                }
                else{
                    blocked.add(ID);
                    event.getChannel().sendMessage("User "+ID+" is now blocked from dev command");
                }
            }
        } catch (ThaksbotException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            try {
                throw new MissingArgsException(event.getChannel());
            } catch (MissingArgsException e1) {
                e1.printStackTrace();
            }
        }
    }
}
