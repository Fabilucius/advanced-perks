package de.fabilucius.advancedperks.commands.subcommands;

import de.fabilucius.advancedperks.AdvancedPerks;
import de.fabilucius.advancedperks.perks.Perk;
import de.fabilucius.sympel.command.command.AbstractSubCommand;
import de.fabilucius.sympel.command.metadata.Permission;
import de.fabilucius.sympel.configuration.utilities.ReplaceLogic;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Permission("advancedperks.command.toggle")
public class ToggleSubCommand extends AbstractSubCommand {

    public ToggleSubCommand() {
        super("toggle");
    }

    @Override
    public void handleCommandExecute(CommandSender commandSender, String... arguments) {
        if (arguments.length == 2) {
            Player player = Bukkit.getPlayer(arguments[0]);
            if (player == null) {
                commandSender.sendMessage(AdvancedPerks.getMessageConfiguration().getMessage("Command.Player-Offline", new ReplaceLogic("<name>", arguments[0])));
                return;
            }
            Perk perk = AdvancedPerks.getPerkRegistry().getPerkByIdentifier(arguments[1]);
            if (perk == null) {
                commandSender.sendMessage(AdvancedPerks.getMessageConfiguration().getMessage("Command.Toggle.Perk-Not-Found", new ReplaceLogic("<perk>", arguments[1])));
                return;
            }
            AdvancedPerks.getPerkStateController().forceTogglePerk(player, perk);
            return;
        }
        commandSender.sendMessage(AdvancedPerks.getMessageConfiguration().getMessage("Command.Toggle.Syntax"));
    }

    @Override
    public List<String> handleTabComplete(CommandSender commandSender, String... arguments) {
        if (arguments.length == 2) {
            if (arguments[1].isEmpty()) {
                return AdvancedPerks.getPerkRegistry().getPerks().stream()
                        .map(Perk::getIdentifier)
                        .collect(Collectors.toList());
            } else {
                return AdvancedPerks.getPerkRegistry().getPerks().stream()
                        .map(Perk::getIdentifier)
                        .filter(identifier -> identifier.toLowerCase().startsWith(arguments[1].toLowerCase()))
                        .collect(Collectors.toList());
            }
        }
        return arguments.length > 1 ? Collections.emptyList() : null;
    }
}
