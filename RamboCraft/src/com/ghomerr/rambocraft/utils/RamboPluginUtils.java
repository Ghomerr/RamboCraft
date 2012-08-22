package com.ghomerr.rambocraft.utils;

import java.util.logging.Logger;

import org.bukkit.command.PluginCommand;

import com.ghomerr.rambocraft.RamboCraft;
import com.ghomerr.rambocraft.constants.RamboConstants;
import com.ghomerr.rambocraft.enums.RamboCommands;
import com.ghomerr.rambocraft.listeners.RamboCommandExecutorTest;

/**
 * General Functions and Utils for the Plugin
 * 
 * @author Ghomerr
 */
public class RamboPluginUtils
{
	private static final Logger _LOGGER = Logger.getLogger(RamboConstants.MINECRAFT);

	public static boolean isDebugEnabled = false;
	public static boolean displayTestDebugMessages = true;

	/**
	 * Register main commands of the plugin
	 * 
	 * @param plugin RamboCraft
	 * @param commandExecutor which treats commands of the plugin
	 */
	public static void registerPluginCommands(final RamboCraft plugin, final RamboCommandExecutorTest commandExecutor)
	{
		for (final String cmd : RamboCommands.RAMBOCRAFT.list)
		{
			final PluginCommand pluginCmd = plugin.getCommand(cmd);
			if (pluginCmd != null)
			{
				pluginCmd.setExecutor(commandExecutor);
			}
			else
			{
				_LOGGER.severe(RamboConstants.PLUGIN_TAG + "Command " + cmd + " could not be added.");
			}
		}
	}
}
