package com.slayeritem;

import com.google.inject.Inject;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.plugins.slayer.SlayerConfig;
import net.runelite.client.plugins.slayer.SlayerPlugin;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@PluginDescriptor(
		name = "Slayer Item",
		description = "Checks inventory and equipment for item need for slayer monsters.",
		tags = {"slayer", "item"}
)
@PluginDependency(SlayerPlugin.class)
public class SlayerItemCheck extends Plugin {
	@Provides
	SlayerItemCheckConfig provideSlayerItemConfig(ConfigManager configManager) {
		return configManager.getConfig(SlayerItemCheckConfig.class);
	}

	@Inject
	private Client client;
	@Inject
	private ConfigManager configManager;
	@Inject
	private InfoBoxManager infoBoxManager;
	@Inject
	private ChatMessageManager chatMessageManager;
	@Inject
	private SlayerItemCheckConfig config;
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private PlayerOverlay overlay;
	@Inject
	private PluginManager pluginManager;
	@Inject
	private SlayerPlugin slayerPlugin;


	private Instant lastNotification;
	private Instant lastLog;
	private Duration delayDuration;
	private final Duration logDelay = Duration.ofMinutes(2);
	private Task previousTask;
	private final ArrayList<Integer> taskItems = new ArrayList<>();
	private boolean overlayActive;
	private int kourendVar;

	@Override
	protected void startUp() throws Exception {
		lastLog = Instant.now();
		delayDuration = Duration.ofSeconds(config.notificationDelay());

		if (!config.manualTask()) {
			setTask(configManager.getConfig(SlayerConfig.class).taskName());
		}
		if (!config.showOverlay()) {
			overlayActive = false;
		}
	}

	@Override
	protected void shutDown() throws Exception {
		overlayActive = false;
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event) {
		switch (event.getGameState()) {
			case HOPPING:
			case LOGGING_IN:
			case LOGGED_IN:
				kourendVar = client.getVar(Varbits.DIARY_KOUREND_ELITE);
				lastNotification = Instant.now().plusSeconds(config.initDelay());
				break;
		}
	}

	@Subscribe
	public void onGameTick(GameTick tick) {
		if (!config.manualTask()) {
			if (pluginManager.isPluginEnabled(slayerPlugin)){
				String taskName = configManager.getConfig(SlayerConfig.class).taskName();
				if(taskName != null){
					setTask(taskName);
					createNotification();
				}
			} else if (Instant.now().compareTo(lastLog.plus(logDelay)) >= 0) { //Help reduce log spam
				lastLog = Instant.now();
				log.info("Slayer plugin needs to be enabled to gather current slayer task automatically.");
			} else {
				overlayManager.remove(overlay);
				overlayActive = false;
			}
		} else if (config.manualTask()) {
			createNotification();
		}
	}

	public Task getTaskFromList() {
		String currentTaskName;
		if (!(config.manualTask())) {
			currentTaskName = configManager.getConfig(SlayerConfig.class).taskName().toLowerCase();
		} else {
			currentTaskName = config.currentTask().toString().toLowerCase();
		}
		for (Task t : Task.values()) {
			if (currentTaskName.contains(t.name().toLowerCase())) {
				return t;
			}
		}
		return null;
	}

	private void buildItemList(Task task) {
		taskItems.clear();
		Arrays.stream(task.getTaskItems()).forEach(taskItems::add);
	}

	private boolean checkInventories(ArrayList<Integer> items) {
		boolean invNull = checkInvNull();
		boolean equipNull = checkEquipNull();
		if(invNull && !equipNull){
			for (Integer i : items) {
				if (client.getItemContainer(InventoryID.EQUIPMENT).contains(i)){
					return true;
				}
			}
			return false;
		} else if(!invNull && equipNull){
			for (Integer i : items) {
				if (client.getItemContainer(InventoryID.INVENTORY).contains(i)){
					return true;
				}
			}
			return false;
		} else if (!invNull && !equipNull){
			for (Integer i : items) {
				if (client.getItemContainer(InventoryID.INVENTORY).contains(i) || client.getItemContainer(InventoryID.EQUIPMENT).contains(i)) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public boolean checkInvNull(){
		return client.getItemContainer(InventoryID.INVENTORY) == null;
	}

	public boolean checkEquipNull(){
		return client.getItemContainer(InventoryID.EQUIPMENT) == null;
	}

	private void setTask(String task) {
		for (Task t : Task.values()) {
			if (task.contains(t.getName(t))) {
				configManager.setConfiguration("slayeritem", "task", t);
			}
		}
	}

	private void createNotification() {
		Task currentTask = getTaskFromList();
		if (currentTask != null && (currentTask.getTaskItems()[0] > 0)) {
			//stops from building the item list multiple times if the slayer task hasn't changed
			if (currentTask != previousTask) {
				buildItemList(currentTask);
				previousTask = currentTask;
			}
			//Won't send any notifications if Kourend Elite is complete
			if (!(kourendVar == 1 && currentTask.getTaskItems()[0] == ItemID.BOOTS_OF_STONE)) {
				if (!checkInventories(taskItems)) {
					delayDuration = Duration.ofSeconds(config.notificationDelay());

					//Player Overlay
					if (config.showOverlay() && !overlayActive) {
						overlayManager.add(overlay);
						overlayActive = true;
					} else if (!config.showOverlay() && overlayActive){
						overlayManager.remove(overlay);
						overlayActive = false;
					}

					//Chat notification
					if (config.sendChat()) {
						if (lastNotification != null && Instant.now().compareTo(lastNotification.plus(delayDuration)) >= 0) {
							sendChatMessage("You don't have the required item for your " + config.currentTask().toString().replace("_", " ").toLowerCase() + " task.");
							if (!(config.playSound())) {
								lastNotification = Instant.now();
							}
						}
					}

					//Sound notification
					if (config.playSound()) {
						if (lastNotification != null && Instant.now().compareTo(lastNotification.plus(delayDuration)) >= 0) {
							client.playSoundEffect(config.notificationSound().getId(), SoundEffectVolume.HIGH);
							lastNotification = Instant.now();
						}
					}
				} else if (overlayActive) {
					overlayManager.remove(overlay);
					overlayActive = false;
				}
			} else {
				overlayManager.remove(overlay);
				overlayActive = false;
			}
		}
	}

	private void sendChatMessage(String chatMessage) {
		final String message = new ChatMessageBuilder()
				.append(ChatColorType.HIGHLIGHT)
				.append(chatMessage)
				.build();

		chatMessageManager.queue(
				QueuedMessage.builder()
						.type(ChatMessageType.CONSOLE)
						.runeLiteFormattedMessage(message)
						.build());
	}
}
