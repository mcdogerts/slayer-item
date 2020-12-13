package com.slayeritem;

import net.runelite.api.Item;
import net.runelite.api.ItemID;

import javax.annotation.Nullable;

public enum Task {
    NONE("none", -1),
    BANSHEE("banshee", ItemID.EARMUFFS, ItemID.SLAYER_HELMET, ItemID.SLAYER_HELMET_I, ItemID.BLACK_SLAYER_HELMET, ItemID.BLACK_SLAYER_HELMET_I, ItemID.GREEN_SLAYER_HELMET, ItemID.GREEN_SLAYER_HELMET_I, ItemID.HYDRA_SLAYER_HELMET, ItemID.HYDRA_SLAYER_HELMET_I, ItemID.PURPLE_SLAYER_HELMET, ItemID.PURPLE_SLAYER_HELMET_I, ItemID.RED_SLAYER_HELMET, ItemID.RED_SLAYER_HELMET_I, ItemID.TURQUOISE_SLAYER_HELMET, ItemID.TURQUOISE_SLAYER_HELMET_I, ItemID.TWISTED_SLAYER_HELMET, ItemID.TWISTED_SLAYER_HELMET_I),
    BASILISK("basilisk", ItemID.MIRROR_SHIELD, ItemID.VS_SHIELD, ItemID.VS_SHIELD_24266),
    CAVE_HORROR("cave horror", ItemID.WITCHWOOD_ICON),
    COCKATRICE("cockatrice", ItemID.MIRROR_SHIELD, ItemID.VS_SHIELD, ItemID.VS_SHIELD_24266),
    DESERT_LIZARD("desert lizard", ItemID.ICE_COOLER),
    DEVIL("devil", ItemID.FACEMASK, ItemID.SLAYER_HELMET, ItemID.SLAYER_HELMET_I, ItemID.BLACK_SLAYER_HELMET, ItemID.BLACK_SLAYER_HELMET_I, ItemID.GREEN_SLAYER_HELMET, ItemID.GREEN_SLAYER_HELMET_I, ItemID.HYDRA_SLAYER_HELMET, ItemID.HYDRA_SLAYER_HELMET_I, ItemID.PURPLE_SLAYER_HELMET, ItemID.PURPLE_SLAYER_HELMET_I, ItemID.RED_SLAYER_HELMET, ItemID.RED_SLAYER_HELMET_I, ItemID.TURQUOISE_SLAYER_HELMET, ItemID.TURQUOISE_SLAYER_HELMET_I, ItemID.TWISTED_SLAYER_HELMET, ItemID.TWISTED_SLAYER_HELMET_I),
    DRAGON("dragon", ItemID.ANTIDRAGON_SHIELD, ItemID.ANTIDRAGON_SHIELD_8282, ItemID.ANTIDRAGON_SHIELD_NZ, ItemID.DRAGONFIRE_SHIELD, ItemID.DRAGONFIRE_SHIELD_11284, ItemID.ANTIFIRE_POTION1, ItemID.ANTIFIRE_POTION2, ItemID.ANTIFIRE_POTION3, ItemID.ANTIFIRE_POTION4, ItemID.EXTENDED_ANTIFIRE1, ItemID.EXTENDED_ANTIFIRE2, ItemID.EXTENDED_ANTIFIRE3, ItemID.EXTENDED_ANTIFIRE4, ItemID.SUPER_ANTIFIRE_POTION1, ItemID.SUPER_ANTIFIRE_POTION2, ItemID.SUPER_ANTIFIRE_POTION3, ItemID.SUPER_ANTIFIRE_POTION4, ItemID.ANTIFIRE_MIX1, ItemID.ANTIFIRE_MIX2, ItemID.EXTENDED_ANTIFIRE_MIX1, ItemID.EXTENDED_ANTIFIRE_MIX2, ItemID.SUPER_ANTIFIRE_MIX1, ItemID.SUPER_ANTIFIRE_MIX2),
    DRAKE("drake", ItemID.BOOTS_OF_STONE, ItemID.BOOTS_OF_BRIMSTONE, ItemID.GRANITE_BOOTS),
    FEVER_SPIDER("fever spider", ItemID.SLAYER_GLOVES, ItemID.SLAYER_GLOVES_6720),
    GARGOYLE("gargoyle", ItemID.ROCK_HAMMER, ItemID.ROCK_THROWNHAMMER),
    HARPIE_BUG_SWARM("bug swarm", ItemID.LIT_BUG_LANTERN),
    HYDRA("hydra", ItemID.BOOTS_OF_STONE, ItemID.BOOTS_OF_BRIMSTONE, ItemID.GRANITE_BOOTS),
    KILLERWATT("killerwatt", ItemID.INSULATED_BOOTS),
    KURASK("kurask", ItemID.LEAFBLADED_SPEAR, ItemID.LEAFBLADED_SWORD, ItemID.LEAFBLADED_BATTLEAXE, ItemID.BROAD_ARROWS, ItemID.BROAD_BOLTS),
    MOLANISK("molanisk", ItemID.SLAYER_BELL),
    MORGE("morge", ItemID.FISHING_EXPLOSIVE_6664, ItemID.FISHING_EXPLOSIVE),
    ROCKSLUG("rockslug", ItemID.BAG_OF_SALT, ItemID.BRINE_SABRE),
    SPECTRE("spectre", ItemID.NOSE_PEG, ItemID.SLAYER_HELMET, ItemID.SLAYER_HELMET_I, ItemID.BLACK_SLAYER_HELMET, ItemID.BLACK_SLAYER_HELMET_I, ItemID.GREEN_SLAYER_HELMET, ItemID.GREEN_SLAYER_HELMET_I, ItemID.HYDRA_SLAYER_HELMET, ItemID.HYDRA_SLAYER_HELMET_I, ItemID.PURPLE_SLAYER_HELMET, ItemID.PURPLE_SLAYER_HELMET_I, ItemID.RED_SLAYER_HELMET, ItemID.RED_SLAYER_HELMET_I, ItemID.TURQUOISE_SLAYER_HELMET, ItemID.TURQUOISE_SLAYER_HELMET_I, ItemID.TWISTED_SLAYER_HELMET, ItemID.TWISTED_SLAYER_HELMET_I),
    SULPHUR_LIZARD("sulphur lizard", ItemID.BOOTS_OF_STONE, ItemID.BOOTS_OF_BRIMSTONE, ItemID.GRANITE_BOOTS),
    TUROTH("turoth",ItemID.LEAFBLADED_SPEAR, ItemID.LEAFBLADED_SWORD, ItemID.LEAFBLADED_BATTLEAXE, ItemID.BROAD_ARROWS, ItemID.BROAD_BOLTS),
    WALL_BEAST("wall beast", ItemID.SPINY_HELMET, ItemID.SLAYER_HELMET, ItemID.SLAYER_HELMET_I, ItemID.BLACK_SLAYER_HELMET, ItemID.BLACK_SLAYER_HELMET_I, ItemID.GREEN_SLAYER_HELMET, ItemID.GREEN_SLAYER_HELMET_I, ItemID.HYDRA_SLAYER_HELMET, ItemID.HYDRA_SLAYER_HELMET_I, ItemID.PURPLE_SLAYER_HELMET, ItemID.PURPLE_SLAYER_HELMET_I, ItemID.RED_SLAYER_HELMET, ItemID.RED_SLAYER_HELMET_I, ItemID.TURQUOISE_SLAYER_HELMET, ItemID.TURQUOISE_SLAYER_HELMET_I, ItemID.TWISTED_SLAYER_HELMET, ItemID.TWISTED_SLAYER_HELMET_I),
    WYRM("wyrm", ItemID.BOOTS_OF_STONE, ItemID.BOOTS_OF_BRIMSTONE, ItemID.GRANITE_BOOTS),
    WYVERN("wyvern", ItemID.ELEMENTAL_SHIELD, ItemID.MIND_SHIELD, ItemID.DRAGONFIRE_SHIELD, ItemID.DRAGONFIRE_SHIELD_11284, ItemID.ANCIENT_WYVERN_SHIELD, ItemID.ANCIENT_WYVERN_SHIELD_21634),
    ZYGOMITE("zygomite", ItemID.FUNGICIDE_SPRAY_10, ItemID.FUNGICIDE_SPRAY_2, ItemID.FUNGICIDE_SPRAY_3, ItemID.FUNGICIDE_SPRAY_4, ItemID.FUNGICIDE_SPRAY_5, ItemID.FUNGICIDE_SPRAY_6, ItemID.FUNGICIDE_SPRAY_7, ItemID.FUNGICIDE_SPRAY_8, ItemID.FUNGICIDE_SPRAY_9, ItemID.FUNGICIDE_SPRAY_1),;


    private String name;
    private int[] requiredItems;

    Task(String name, int... requiredItems){
        this.name = name;
        this.requiredItems = requiredItems;
    }

    public int[] getTaskItems() {
        return requiredItems;
    }

    public static int getDisplayItem(Task task)
    {
        return task.requiredItems[0];
    }

    public String getName(Task task){
        return  task.name;
    }
}