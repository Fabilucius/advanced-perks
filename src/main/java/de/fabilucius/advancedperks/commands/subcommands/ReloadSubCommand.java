package de.fabilucius.advancedperks.commands.subcommands;

import de.fabilucius.advancedperks.AdvancedPerks;
import de.fabilucius.sympel.command.command.AbstractSubCommand;
import de.fabilucius.sympel.command.metadata.Permission;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

@Permission("advancedperks.command.reload")
public class ReloadSubCommand extends AbstractSubCommand {
    public ReloadSubCommand() {
        super("reload");
    }

    @Override
    public void handleCommandExecute(CommandSender commandSender, String... strings) {
        AdvancedPerks.reloadPlugin();
        commandSender.sendMessage("~ Successfully reloaded Advanced Perks");
    }

    @Override
    public List<String> handleTabComplete(CommandSender commandSender, String... strings) {
        return Collections.emptyList();
    }
}
