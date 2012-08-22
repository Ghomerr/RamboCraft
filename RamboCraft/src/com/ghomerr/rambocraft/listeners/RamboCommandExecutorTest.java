package com.ghomerr.rambocraft.listeners;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ghomerr.rambocraft.RamboCraft;
import com.ghomerr.rambocraft.constants.RamboConstants;
import com.ghomerr.rambocraft.enums.RamboCommands;
import com.ghomerr.rambocraft.enums.RamboPotionEffects;
import com.ghomerr.rambocraft.objects.RamboConfigurationContainer;
import com.ghomerr.rambocraft.utils.RamboConfigurationUtils;
import com.ghomerr.rambocraft.utils.RamboPlayerUtils;

/**
 * Command Executor which treats commands
 * @author Ghomerr
 */
public class RamboCommandExecutorTest implements CommandExecutor
{
	private static final Logger _LOGGER = Logger.getLogger(RamboConstants.MINECRAFT);
	
	private RamboCraft _plugin = null;
	private RamboConfigurationContainer _container = null;
	
	/**
	 * Constructor 
	 * @param plugin RamboCraft
	 */
	public RamboCommandExecutorTest(final RamboCraft plugin)
	{
		_plugin = plugin;
		_container = _plugin.configContainer;
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] split)
	{
		boolean result = false;
		
		if (_plugin.isEnabled)
		{
			if (sender instanceof Player)
			{
				final Player player = (Player) sender;
			
				// /rc ...
				if (RamboCommands.RAMBOCRAFT.has(label))
				{
					// At least one parameter
					if (split.length >= 1)
					{
						final String param1 = split[0];
						
						if ("berambo".equalsIgnoreCase(param1))
						{
							if (split.length >= 2)
							{
								final int duration = Integer.parseInt(split[1]);
								RamboPlayerUtils.addRamboPotionsEffects(_container, player, duration, false);
							}
							else
							{
								RamboPlayerUtils.addRamboPotionsEffects(_container, player);
							}
							RamboPlayerUtils.addRamboGear(_container, player);
						}
						else if ("beplayer".equalsIgnoreCase(param1))
						{
							RamboPlayerUtils.removeRamboPotionsEffects(_container, player);
							RamboPlayerUtils.addPlayerGear(_container, player);
						}
						else if ("potion".equalsIgnoreCase(param1))
						{
							if (split.length >= 3)
							{
								final String name = split[1];
								final int value = RamboConfigurationUtils.getValidPotionAmplifier(split[2]);
								
								final RamboPotionEffects test = RamboPotionEffects.getRamboPotionEffect(name);
								if (test != RamboPotionEffects.UNKNOWN_EFFECT)
								{
									_container.potionEffectsConfiguration.put(test, new Integer(value));
								}
							}
						}
						else if ("rambogear".equalsIgnoreCase(param1))
						{
							RamboPlayerUtils.addRamboGear(_container, player);
						}
						else if ("playergear".equalsIgnoreCase(param1))
						{
							RamboPlayerUtils.addPlayerGear(_container, player);
						}
						else if ("clear".equalsIgnoreCase(param1))
						{
							RamboPlayerUtils.clearFullInventory(player);
							RamboPlayerUtils.removeRamboPotionsEffects(_container, player);
						}
						else
						{
							player.sendMessage("Help");
						}
						// TODO : other commands
					}
					// No Parameter
					else
					{
						// TODO: print help
						player.sendMessage("Help");
					}
				}
			}
		}
		else
		{
			_LOGGER.warning(RamboConstants.PLUGIN_TAG + " plugin is disabled. Commands have no effects.");
		}
		
		return result;
	}
}
