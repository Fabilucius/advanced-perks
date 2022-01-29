package de.fabilucius.advancedperks.data;

import de.fabilucius.advancedperks.AdvancedPerks;
import de.fabilucius.advancedperks.commons.MapCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class PerkDataRepository extends MapCache<Player, PerkData> implements Listener {

    private static final Logger LOGGER = Bukkit.getLogger();

    public PerkDataRepository() {
        Bukkit.getPluginManager().registerEvents(this, AdvancedPerks.getInstance());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.addPerkData(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.removePerkData(event.getPlayer());
    }

    public void consumePerkData(Player player, Consumer<PerkData> perkData) {
        PerkData data = this.getPerkData(player);
        if (data != null) {
            perkData.accept(data);
        }
    }

    public PerkData getPerkData(Player player) {
        if (!this.getPerkDataCache().containsKey(player)) {
            PerkData perkData = new PerkData(player);
            this.getPerkDataCache().put(player, perkData);
            return perkData;
        }
        return this.getPerkDataCache().get(player);
    }

    private void addPerkData(Player player) {
        if (!this.getPerkDataCache().containsKey(player)) {
            this.getPerkDataCache().put(player, new PerkData(player));
        }
    }

    private void removePerkData(Player player) {
        PerkData perkData = this.getPerkData(player);
        AdvancedPerks.getInstance().getPerkStateController().savePerkDataAsync(perkData);
        this.getPerkDataCache().remove(player);
    }

    public Map<Player, PerkData> getPerkDataCache() {
        return super.getCache();
    }
}
