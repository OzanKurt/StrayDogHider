package com.ozankurt;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import net.runelite.api.NPC;
import net.runelite.api.NpcID;
import net.runelite.api.Renderable;
import net.runelite.client.callback.Hooks;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.Set;

@PluginDescriptor(
        name = "Stray Dog Hider",
        description = "Hide stray dogs",
        tags = {"dog", "dogs", "stray", "hide"}
)
public class StrayDogHiderPlugin extends Plugin {
    private static final Set<Integer> STRAY_DOG_IDS = ImmutableSet.of(
            NpcID.STRAY_DOG,
            NpcID.STRAY_DOG_2922,
            NpcID.STRAY_DOG_3829,
            NpcID.STRAY_DOG_3830,
            NpcID.STRAY_DOG_10675,
            NpcID.STRAY_DOG_14112,
            NpcID.STRAY_DOG_10760,
            NpcID.MOLOSSUS,
            NpcID.MOLOSSUS_12993,
            NpcID.MOLOSSUS_12994,
            NpcID.MOLOSSUS_12995,
            NpcID.MOLOSSUS_12999
    );

    @Inject
    private StrayDogHiderConfig config;

    @Inject
    private Hooks hooks;

    private boolean hideStrayDogs;

    private final Hooks.RenderableDrawListener drawListener = this::shouldDraw;

    @Provides
    StrayDogHiderConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(StrayDogHiderConfig.class);
    }

    @Override
    protected void startUp() {
        updateConfig();

        hooks.registerRenderableDrawListener(drawListener);
    }

    @Override
    protected void shutDown() {
        hooks.unregisterRenderableDrawListener(drawListener);
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged e) {
        if (e.getGroup().equals(StrayDogHiderConfig.GROUP)) {
            updateConfig();
        }
    }

    private void updateConfig() {
        hideStrayDogs = config.hideStrayDogs();
    }

    @VisibleForTesting
    boolean shouldDraw(Renderable renderable, boolean drawingUI) {
        if (renderable instanceof NPC) {
            NPC npc = (NPC) renderable;

            if (STRAY_DOG_IDS.contains(npc.getId())) {
                return !hideStrayDogs;
            }
        }

        return true;
    }
}
