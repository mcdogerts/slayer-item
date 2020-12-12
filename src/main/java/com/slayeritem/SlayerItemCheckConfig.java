package com.slayeritem;


import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("slayeritem")
public interface SlayerItemCheckConfig extends Config {

    enum NotificationSound
    {
        CRIER_BELL(3813), //3813, SoundEffectID.TOWN_CRIER_BELL_DING
        ANVIL(3790); //3790, SoundEffectID.SMITH_ANVIL_TINK

        private int id;

        NotificationSound(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    @ConfigItem(
            keyName = "manualtask",
            name = "Set Task Manually",
            description = "",
            position = 0
    )
    default boolean manualTask() { return false; }

    @ConfigItem(
            keyName = "task",
            name = "Task",
            description = "",
            position = 1
    )
    default Task currentTask() { return Task.NONE; }

    @ConfigItem(
            keyName = "overlay",
            name = "Player Overlay",
            description = "Show overlay over player wtih required item.",
            position = 2
    )
    default boolean showOverlay()
    {
        return true;
    }


    @ConfigItem(
            keyName = "chat",
            name = "Chat Notification",
            description = "",
            position = 3
    )
    default boolean sendChat()  { return true; }

    @ConfigItem(
            keyName = "sound",
            name = "Sound Notification",
            description = "",
            position = 4
    )
    default boolean playSound()
    {
        return true;
    }

    @ConfigItem(
            keyName = "soundfx",
            name = "Sound Effect",
            description = "",
            position = 5
    )
    default NotificationSound notificationSound(){
        return NotificationSound.CRIER_BELL;
    }

    @ConfigItem(
            keyName = "timer",
            name = "Notification delay",
            description = "Time delay in seconds before chat and sound notification can be repeated.",
            position = 6
    )
    default int notificationDelay(){
        return 20;
    }

    @ConfigItem(
            keyName = "initdelay",
            name = "Initial notification delay",
            description = "Inital delay in seconds before sending chat and sound notifications after logging in.",
            position = 7
    )
    default int initDelay(){ return 30;}

}
