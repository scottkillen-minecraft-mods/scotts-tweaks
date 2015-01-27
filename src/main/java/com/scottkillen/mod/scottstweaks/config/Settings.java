package com.scottkillen.mod.scottstweaks.config;

import com.google.common.base.Objects;
import com.scottkillen.mod.koresample.config.ConfigSyncable;
import com.scottkillen.mod.scottstweaks.TheMod;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public enum Settings implements ConfigSyncable
{
    INSTANCE;
    public static final String CONFIG_VERSION = "1";
    private static final String CATEGORY_CLAY_SPAWN =
            String.format("%s%sclay_spawn", Configuration.CATEGORY_GENERAL, Configuration.CATEGORY_SPLITTER);
    private static final String CATEGORY_FEATHER_DROP =
            String.format("%s%sfeather_drop", Configuration.CATEGORY_GENERAL, Configuration.CATEGORY_SPLITTER);
    private static final String CATEGORY_PLANTING =
            String.format("%s%splanting", Configuration.CATEGORY_GENERAL, Configuration.CATEGORY_SPLITTER);


    private int chickFeatherQuantity = 1;
    private int henFeatherQuantity = 1;
    private int chickFeatherRarity = 50000;
    private int henFeatherRarity = 25000;
    private int clayVeinQuantity = 6;
    private int clayVeinSize = 12;
    private int clayVeinSizeVariation = 6;
    private int claySpawnYMin = 50;
    private int claySpawnYMax = 60;
    private boolean doChicksDropFeathers = true;
    private boolean doHensDropFeathers = true;
    private boolean doPlantGrowable = true;

    private static int get(Configuration config, String settingName, String category, int defaultValue,
                           int minumumValue, int maximumValue)
    {
        return config.getInt(settingName, category, defaultValue, minumumValue, maximumValue,
                getLocalizedComment(settingName));
    }

    private static boolean get(Configuration config, String settingName, String category, boolean defaultValue)
    {
        return config.getBoolean(settingName, category, defaultValue, getLocalizedComment(settingName));
    }

    private static String getLocalizedComment(String settingName)
    {
        return StatCollector.translateToLocal("config." + TheMod.INSTANCE.modID() + ':' + settingName);
    }

    @Override
    public void convertOldConfig(Configuration oldConfig)
    {
        // Handle old config versions (none yet)

        syncConfig(TheMod.INSTANCE.configuration());
    }

    @Override
    public void syncConfig(Configuration config)
    {
        chickFeatherQuantity = get(config, "chickFeatherQuantity", CATEGORY_FEATHER_DROP, chickFeatherQuantity, 1, 10);
        chickFeatherRarity =
                get(config, "chickFeatherRarity", CATEGORY_FEATHER_DROP, chickFeatherRarity, 6000, Integer.MAX_VALUE);
        doChicksDropFeathers = get(config, "doChicksDropFeathers", CATEGORY_FEATHER_DROP, doChicksDropFeathers);
        doHensDropFeathers = get(config, "doHensDropFeathers", CATEGORY_FEATHER_DROP, doHensDropFeathers);
        henFeatherQuantity = get(config, "henFeatherQuantity", CATEGORY_FEATHER_DROP, henFeatherQuantity, 1, 10);
        henFeatherRarity =
                get(config, "henFeatherRarity", CATEGORY_FEATHER_DROP, henFeatherRarity, 6000, Integer.MAX_VALUE);

        doPlantGrowable = get(config, "doPlantGrowable", CATEGORY_PLANTING, doPlantGrowable);

        clayVeinQuantity = get(config, "clayVeinQuantity", CATEGORY_CLAY_SPAWN, clayVeinQuantity, 0, 255);
        clayVeinSize = get(config, "clayVeinSize", CATEGORY_CLAY_SPAWN, clayVeinSize, 1, 255);
        clayVeinSizeVariation =
                get(config, "clayVeinSizeVariation", CATEGORY_CLAY_SPAWN, clayVeinSizeVariation, 0, clayVeinSize);
        claySpawnYMax = get(config, "claySpawnYMax", CATEGORY_CLAY_SPAWN, claySpawnYMax, 1, 255);
        claySpawnYMin = get(config, "claySpawnYMin", CATEGORY_CLAY_SPAWN, claySpawnYMin, 1, claySpawnYMax);
    }

    public int chickFeatherQuantity() { return chickFeatherQuantity; }

    public int chickFeatherRarity() { return chickFeatherRarity; }

    public int claySpawnYMax() { return claySpawnYMax; }

    public int claySpawnYMin() { return claySpawnYMin; }

    public int clayVeinQuantity() { return clayVeinQuantity; }

    public int clayVeinSize() { return clayVeinSize; }

    public int clayVeinSizeVariation() { return clayVeinSizeVariation; }

    public boolean doChicksDropFeathers() { return doChicksDropFeathers; }

    public boolean doHensDropFeathers() { return doHensDropFeathers; }

    public boolean doPlantGrowable() { return doPlantGrowable;}

    public int henFeatherQuantity() { return henFeatherQuantity; }

    public int henFeatherRarity() { return henFeatherRarity; }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("chickFeatherQuantity", chickFeatherQuantity)
                .add("henFeatherQuantity", henFeatherQuantity).add("chickFeatherRarity", chickFeatherRarity)
                .add("henFeatherRarity", henFeatherRarity).add("clayVeinQuantity", clayVeinQuantity)
                .add("clayVeinSize", clayVeinSize).add("clayVeinSizeVariation", clayVeinSizeVariation)
                .add("claySpawnYMin", claySpawnYMin).add("claySpawnYMax", claySpawnYMax)
                .add("doChicksDropFeathers", doChicksDropFeathers).add("doHensDropFeathers", doHensDropFeathers)
                .add("doPlantGrowable", doPlantGrowable).toString();
    }
}
