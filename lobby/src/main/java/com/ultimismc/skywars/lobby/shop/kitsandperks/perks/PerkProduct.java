package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.StringUtil;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class PerkProduct {

    protected final Perk perk;

    public PerkProduct(Perk perk) {
        this.perk = perk;
    }

    public ProductItemDesign designProduct(User user) {
        Material displayMaterial = perk.getDisplayMaterial();
        short displayDurability = perk.getDisplayDurability();
        String description = perk.getDescription();
        PerkRarity rarity = perk.getRarity();
        boolean soulWellPerk = perk.isSoulWell();

        List<String> lore = new ArrayList<>(StringUtil.getCorrectDescription(ChatColor.GRAY, description, 31));
        lore.add(" ");
        lore.add("&7Rarity: " + rarity.getDisplayName());
        lore.add(" ");
        lore.add("&7Cost: " + perk.getDisplayPrice());
        if(soulWellPerk) {
            lore.add("&bAlso found in the Soul Well!");
        }
        return new ProductItemDesign(displayMaterial, displayDurability, null, lore);
    }
}