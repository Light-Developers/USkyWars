package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.combat.AttackCause;

/**
 * @author DirectPlan
 */
@Getter
public abstract class KillMessage extends Cosmetic {

    private final String category = "Kill Message";
    private final KillMessageBundle messageBundle = new KillMessageBundle();

    public KillMessage(String name, CosmeticRarity rarity) {
        super(name, rarity);

        addDescription("Select the " + getNameWithCategory());
        addDescription("for in-game chat messages!");

        buildBundle(messageBundle);
    }

    public abstract void buildBundle(KillMessageBundle messageBundle);

    public void triggerKillMessage(AttackCause attackCause, User user, User killer) {
        MessageType messageType = MessageType.translate(attackCause);
        MessageResolver messageResolver = messageBundle.getResolver(messageType);
        String killMessage = messageResolver.resolveMessage(user, killer);
        Bukkit.broadcastMessage(PluginUtility.translateMessage(killMessage));

        String screenMessage = messageBundle.resolveScreenMessage(user, killer);
        PluginUtility.sendTitle(user.getPlayer(), 0, 60, 0, screenMessage, "&7You are now a spectator!");
    }
}
