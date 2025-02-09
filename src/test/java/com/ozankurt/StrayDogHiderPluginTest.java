package com.ozankurt;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class StrayDogHiderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(StrayDogHiderPlugin.class);
		RuneLite.main(args);
	}
}
