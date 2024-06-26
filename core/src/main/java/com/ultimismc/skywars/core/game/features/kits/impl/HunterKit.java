package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitBundle;
import com.ultimismc.skywars.core.game.features.kits.KitItem;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * @author DirectPlan
 */
@Getter
public class HunterKit extends Kit {

    private final PurchasableDesign design = new PurchasableDesign(Material.BOW);

    public HunterKit() {
        super("Hunter", KitRarity.RARE);

        addBundle(GameType.NORMAL, new NormalHunterBundle());
        addBundle(GameType.INSANE, new InsaneHunterBundle());
    }

    static class NormalHunterBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;
        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.BOW)
                    .itemEnchantment(Enchantment.ARROW_DAMAGE, 1));
            addItem(new KitItem(Material.ARROW).amount(16));
        }
    }

    static class InsaneHunterBundle extends KitBundle {

        @Getter private final PurchasableDesign design = null;

        @Override
        public void buildGameItems() {
            addItem(new KitItem(Material.BOW)
                    .itemEnchantment(Enchantment.ARROW_DAMAGE, 1));
            addItem(new KitItem(Material.ARROW).amount(16));
        }
    }
}
