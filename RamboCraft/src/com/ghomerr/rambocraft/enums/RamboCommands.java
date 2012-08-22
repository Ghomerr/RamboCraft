package com.ghomerr.rambocraft.enums;

import java.util.HashSet;

/**
 * All commands and aliases of the plugin
 * @author Ghomerr
 */
public enum RamboCommands
{
	RAMBOCRAFT(new String[] {"rc", "rambo", "rambocraft"});
	
	public HashSet<String> list;
	
	/**
	 * Constructor
	 * @param tab of all aliases of one command
	 */
	RamboCommands(final String[] tab)
	{
		list = new HashSet<String>();
		for (String cmd : tab)
		{
			list.add(cmd);
		}
	}
	
	/**
	 * Return true if the command contains the one of the aliases of the current command
	 * @param cmd to check
	 * @return true if the string corresponds to an existing command or one of its aliases
	 */
	public boolean has(final String cmd)
	{
		return list.contains(cmd.toLowerCase());
	}
}
