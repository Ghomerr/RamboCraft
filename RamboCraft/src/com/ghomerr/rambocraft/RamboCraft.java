package com.ghomerr.rambocraft;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.ghomerr.rambocraft.constants.RamboConstants;
import com.ghomerr.rambocraft.listeners.RamboCommandExecutorTest;
import com.ghomerr.rambocraft.objects.RamboConfigurationContainer;
import com.ghomerr.rambocraft.utils.RamboConfigurationUtils;
import com.ghomerr.rambocraft.utils.RamboPlayerUtils;
import com.ghomerr.rambocraft.utils.RamboPluginUtils;

/**
 * RamboCraft Plugin
 * 
 * @author Ghomerr
 */
public class RamboCraft extends JavaPlugin
{
	private static final Logger _LOGGER = Logger.getLogger(RamboConstants.MINECRAFT);

	public boolean isEnabled = false;
	public boolean isDebugEnabled = false;
	
	public RamboConfigurationContainer configContainer = null;
	
	@Override
	public void onEnable()
	{
		super.onEnable();
		setDebugState(true);
		
		configContainer = new RamboConfigurationContainer(this);
		
		// Create Listeners
		final RamboCommandExecutorTest commandExecutor = new RamboCommandExecutorTest(this);

		// Register commands
		RamboPluginUtils.registerPluginCommands(this, commandExecutor);

		// Configure potion Effects
		final String tempConfigPotionEffects = "jump:min,regeneration:min,speed:max,increase_damage:max,damage_resistance:min";
		configContainer.configureRamboPotionsEffects(tempConfigPotionEffects);
		
		// Configure Rambo Gear
		final String tempConfigRamboGear = "gold_helmet,gold_chestplate,gold_leggings,gold_boots";
		configContainer.configureRamboGear(tempConfigRamboGear);
		
		// Configure Rambo Enchants
		final String tempConfigRamboBowEnchants = "fire:max,damage:max,infinite:max,knockback:min";
		final String tempConfigRamboArmorEnchants = "fall:max,explosions:min";
		configContainer.configureRamboEnchantments(tempConfigRamboBowEnchants, tempConfigRamboArmorEnchants);
		
		// Configure Player Gear
		final String tempConfigPlayerGear = "leather_helmet:1,leather_boots,leather_chestplate:max,leather_leggings:min,stone_sword:5,bow:max,arrows:max";
		configContainer.configurePlayerGear(tempConfigPlayerGear);
		
		_LOGGER.info(RamboConstants.PLUGIN_TAG + "Loading done.");
		
		isEnabled = true;
	}

	@Override
	public void onDisable()
	{
		super.onDisable();
	}
	
	/**
	 * Toggle the debug state and update the result to utils
	 * @return the new debug state
	 */
	public boolean toggleDebug()
	{
		isDebugEnabled = !isDebugEnabled;
		
		RamboPluginUtils.isDebugEnabled = isDebugEnabled;
		RamboPlayerUtils.isDebugEnabled = isDebugEnabled;
		RamboConfigurationUtils.isDebugEnabled = isDebugEnabled;
		
		return isDebugEnabled;
	}
	
	public void setDebugState(final boolean state)
	{
		isDebugEnabled = state;
		
		RamboPluginUtils.isDebugEnabled = isDebugEnabled;
		RamboPlayerUtils.isDebugEnabled = isDebugEnabled;
		RamboConfigurationUtils.isDebugEnabled = isDebugEnabled;
	}
}
