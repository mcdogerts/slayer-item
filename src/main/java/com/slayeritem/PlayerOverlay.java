package com.slayeritem;

import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerOverlay extends Overlay {

    private static final int OFFSET = 45;
    private final Client client;
    private final SlayerItemCheck plugin;
    private final ItemManager itemManager;
    private int displayItem;
    private Task currentTask;


    @Inject
    private PlayerOverlay(Client client, SlayerItemCheck plugin, ItemManager itemManager)
    {
        this.client = client;
        this.plugin = plugin;
        this.itemManager = itemManager;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.UNDER_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        currentTask = plugin.getSlayerTask();
        if(currentTask == null){
            return null;
        }

        displayItem = Task.getDisplayItem(currentTask);
        if(displayItem < 0){
            return null;
        }

        BufferedImage image = itemManager.getImage(displayItem);

        if (image == null)
        {
            return null;
        }

        renderItem(graphics, image);

        return null;
    }

    private void renderItem(Graphics2D graphics, BufferedImage image) {
        //render the item
        LocalPoint playerPos = client.getLocalPlayer().getLocalLocation();
        int offset = client.getLocalPlayer().getLogicalHeight() + OFFSET;

        Point imageLoc = Perspective.getCanvasImageLocation(client, playerPos, image, offset);

        if (imageLoc != null)
        {
            OverlayUtil.renderImageLocation(graphics, imageLoc, image);
        }

    }
}
