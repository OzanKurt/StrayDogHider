package com.ozankurt;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(StrayDogHiderConfig.GROUP)
public interface StrayDogHiderConfig extends Config
{
	String GROUP = "straydoghider";

	@ConfigItem(
			position = 1,
			keyName = "hideStrayDogs",
			name = "Hide stray dogs",
			description = "Configures whether or not stray dogs are hidden."
	)
	default boolean hideStrayDogs()
	{
		return true;
	}
}
