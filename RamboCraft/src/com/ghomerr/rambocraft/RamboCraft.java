package com.ghomerr.rambocraft;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.ghomerr.rambocraft.constants.RamboConstants;

public class RamboCraft extends JavaPlugin
{
	private static final Logger _LOGGER = Logger.getLogger(RamboConstants.MINECRAFT);
	
	@Override
	public void onEnable()
	{
		super.onEnable();
		
		_LOGGER.info(RamboConstants.PLUGIN_TAG + RamboConstants.VERSION + " loading...");
		
		// Create Listener
		
		// Register commands
	}
	
	@Override
	public void onDisable()
	{
		super.onDisable();
	}
}
