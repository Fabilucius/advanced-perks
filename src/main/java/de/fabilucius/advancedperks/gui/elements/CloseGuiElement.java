package de.fabilucius.advancedperks.gui.elements;

import de.fabilucius.advancedperks.AdvancedPerks;
import de.fabilucius.advancedperks.commons.guisystem.GuiElement;
import de.fabilucius.advancedperks.commons.guisystem.GuiWindow;
import de.fabilucius.advancedperks.commons.guisystem.annotation.CancelInventoryInteraction;
import de.fabilucius.sympel.item.builder.types.ItemStackBuilder;
import org.bukkit.Material;

@CancelInventoryInteraction
public class CloseGuiElement extends GuiElement {
    public CloseGuiElement(GuiWindow guiWindow) {
        super(guiWindow, (guiElement, event) -> {
            event.getWhoClicked().closeInventory();
        }, ItemStackBuilder.fromMaterial(Material.BARRIER)
                .setDisplayName(AdvancedPerks.getMessageConfiguration().getMessage("Gui.Close-Gui.Name"))
                .setDescription(AdvancedPerks.getMessageConfiguration().getMessageList("Gui.Close-Gui.Description"))
                .build());
    }
}
