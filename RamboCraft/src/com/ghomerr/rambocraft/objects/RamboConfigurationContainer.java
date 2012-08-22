package com.ghomerr.rambocraft.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.inventory.ItemStack;

import com.ghomerr.rambocraft.RamboCraft;
import com.ghomerr.rambocraft.constants.RamboConstants;
import com.ghomerr.rambocraft.enums.PlayerGear;
import com.ghomerr.rambocraft.enums.RamboArmorEnchantments;
import com.ghomerr.rambocraft.enums.RamboBowEnchantments;
import com.ghomerr.rambocraft.enums.RamboGear;
import com.ghomerr.rambocraft.enums.RamboPotionEffects;
import com.ghomerr.rambocraft.utils.RamboConfigurationUtils;

/**
 * Store the Plugin configuration
 * 
 * @author Ghomerr
 */
public class RamboConfigurationContainer
{
	public HashMap<RamboPotionEffects, Integer> potionEffectsConfiguration = new HashMap<RamboPotionEffects, Integer>();

	public HashMap<RamboBowEnchantments, Integer> bowEnchantsConfiguration = new HashMap<RamboBowEnchantments, Integer>();
	public HashMap<RamboArmorEnchantments, Integer> armorEnchantsConfiguration = new HashMap<RamboArmorEnchantments, Integer>();

	public HashMap<RamboGear, ItemStack> ramboGearConfiguration = new HashMap<RamboGear, ItemStack>();
	public HashMap<PlayerGear, List<ItemStack>> playerGearConfiguration = new HashMap<PlayerGear, List<ItemStack>>();

	private RamboCraft _plugin = null;
	private static Logger _LOGGER = Logger.getLogger(RamboConstants.MINECRAFT);

	/**
	 * Constructor
	 * 
	 * @param plugin
	 */
	public RamboConfigurationContainer(final RamboCraft plugin)
	{
		this._plugin = plugin;
	}

	/**
	 * Fill the configuration map of potions effects and their own amplifiers from the configuration line
	 * 
	 * @param potionEffectsConfigurationLine
	 */
	public void configureRamboPotionsEffects(final String potionEffectsConfigurationLine)
	{
		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "configureRamboPotionsEffects(potionEffectConfigurationLine=" + potionEffectsConfigurationLine
					+ ")");
		}

		final String[] split = potionEffectsConfigurationLine.split(RamboConstants.DELIMITER);

		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + split.length + " potion effects configured.");
		}

		for (final String str : split)
		{
			final String[] keyvalue = str.split(RamboConstants.DOUBLEDOTS);

			if (keyvalue.length == 2)
			{
				final RamboPotionEffects potionEffect = RamboPotionEffects.getRamboPotionEffect(keyvalue[0]);
				final int amplifier = RamboConfigurationUtils.getValidPotionAmplifier(keyvalue[1]);

				if (potionEffect != RamboPotionEffects.UNKNOWN_EFFECT && amplifier >= 0)
				{
					potionEffectsConfiguration.put(potionEffect, new Integer(amplifier));
				}
				else
				{
					_LOGGER.warning(RamboConstants.PLUGIN_TAG + " The potion effect configuration '" + str + "' is unknown or invalid.");
				}
			}
			else
			{
				_LOGGER.warning(RamboConstants.PLUGIN_TAG + "Invalid value for potion effect configuration effect:amplifier = " + str);
			}
		}

		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + potionEffectsConfiguration.size() + " potion effects stored.");
		}
	}

	/**
	 * Fill the configuration map of potions effects and their own amplifiers from the configuration line
	 * 
	 * @param potionEffectsConfigurationLine
	 */
	public void configureRamboEnchantments(final String bowEnchantsConfigurationLine, final String armorEnchantsConfigurationLine)
	{
		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "configureRamboEnchantments(bowEnchantsConfigurationLine=" + bowEnchantsConfigurationLine
					+ ", armorEnchantsConfigurationLine=" + armorEnchantsConfigurationLine + ")");
		}

		final String[] bowSplit = bowEnchantsConfigurationLine.split(RamboConstants.DELIMITER);
		final String[] armorSplit = armorEnchantsConfigurationLine.split(RamboConstants.DELIMITER);

		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + bowSplit.length + " bow enchants configured.");
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + armorSplit.length + " armor enchants configured.");
		}

		for (final String bowEnchantsConfig : bowSplit)
		{
			final String[] keyvalue = bowEnchantsConfig.split(RamboConstants.DOUBLEDOTS);

			if (keyvalue.length == 2)
			{
				final RamboBowEnchantments bowEnchant = RamboBowEnchantments.getRamboBowEnchantment(keyvalue[0]);
				final int level = RamboConfigurationUtils.getValidEnchantLevel(keyvalue[1], bowEnchant.enchant);

				if (bowEnchant != RamboBowEnchantments.UNKNOWN_ENCHANT && level > 0)
				{
					bowEnchantsConfiguration.put(bowEnchant, new Integer(level));
				}
				else
				{
					_LOGGER.warning(RamboConstants.PLUGIN_TAG + " The bow enchant configuration '" + bowEnchantsConfig + "' is unknown or invalid.");
				}
			}
			else
			{
				_LOGGER.warning(RamboConstants.PLUGIN_TAG + "Invalid value for bow enchant configuration enchant:level = " + bowEnchantsConfig);
			}
		}

		for (final String armorEnchantsConfig : armorSplit)
		{
			final String[] keyvalue = armorEnchantsConfig.split(RamboConstants.DOUBLEDOTS);

			if (keyvalue.length == 2)
			{
				final RamboArmorEnchantments armorEnchant = RamboArmorEnchantments.getRamboArmorEnchantment(keyvalue[0]);
				final int level = RamboConfigurationUtils.getValidEnchantLevel(keyvalue[1], armorEnchant.enchant);

				if (armorEnchant != RamboArmorEnchantments.UNKNOWN_ENCHANT && level > 0)
				{
					armorEnchantsConfiguration.put(armorEnchant, new Integer(level));
				}
				else
				{
					_LOGGER.warning(RamboConstants.PLUGIN_TAG + " The armor enchant configuration '" + armorEnchantsConfig
							+ "' is unknown or invalid.");
				}
			}
			else
			{
				_LOGGER.warning(RamboConstants.PLUGIN_TAG + "Invalid value for armor enchant configuration enchant:level = " + armorEnchantsConfig);
			}
		}

		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + bowEnchantsConfiguration.size() + " bow enchants stored.");
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + armorEnchantsConfiguration.size() + " armor enchants stored.");
		}
	}

	/**
	 * Fill the configuration map of Rambo Gear with items configured in the configuration line
	 * 
	 * @param gearConfigurationLine
	 */
	public void configureRamboGear(final String gearConfigurationLine)
	{
		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "configureRamboGear(gearConfigurationLine=" + gearConfigurationLine + ")");
		}

		final String[] split = gearConfigurationLine.split(RamboConstants.DELIMITER);

		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + split.length + " parts of rambo armor configured.");
		}

		for (final String armorPartName : split)
		{
			final RamboGear armorPart = RamboGear.getRamboGear(armorPartName);

			if (armorPart != RamboGear.UNKNOWN_ITEM)
			{
				final ItemStack part = new ItemStack(armorPart.mat, 1);
				ramboGearConfiguration.put(armorPart, part);
			}
			else
			{
				_LOGGER.warning(RamboConstants.PLUGIN_TAG + " The rambo armor part configuration '" + armorPartName + "' is unknown or invalid.");
			}
		}

		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + ramboGearConfiguration.size() + " parts of rambo armor stored.");
		}
	}

	/**
	 * Fill the configuration map of Player Gear with items configured in the configuration line
	 * 
	 * @param gearConfigurationLine
	 */
	public void configurePlayerGear(final String gearConfigurationLine)
	{
		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "configurePlayerGear(gearConfigurationLine=" + gearConfigurationLine + ")");
		}

		final String[] split = gearConfigurationLine.split(RamboConstants.DELIMITER);

		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + split.length + " parts of player armor configured.");
		}

		for (final String armorPartConf : split)
		{
			final String[] keyvalue = armorPartConf.split(RamboConstants.DOUBLEDOTS);
			PlayerGear item = PlayerGear.UNKNOWN_ITEM;
			int amount = -1;

			if (keyvalue.length == 1)
			{
				item = PlayerGear.getPlayerGear(keyvalue[0]);
				amount = item.min;
			}
			else if (keyvalue.length == 2)
			{
				item = PlayerGear.getPlayerGear(keyvalue[0]);
				amount = RamboConfigurationUtils.getValidItemAmount(keyvalue[1], item);
			}
			else
			{
				_LOGGER.warning(RamboConstants.PLUGIN_TAG + "Invalid value for player armor configuration item:amount or item = " + armorPartConf);
			}

			if (item != PlayerGear.UNKNOWN_ITEM && amount > 0)
			{
				final ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();

				for (int i = 0 ; i < amount ; i++)
				{
					final ItemStack stack = new ItemStack(item.mat, item.mat.getMaxStackSize());
					stacks.add(stack);
				}
				if (_plugin.isDebugEnabled)
				{
					_LOGGER.info(RamboConstants.DEBUG_TAG + "There will be " + stacks.size() + " stacks of " + item.mat + " in the player armor");
				}

				playerGearConfiguration.put(item, stacks);
			}
			else
			{
				_LOGGER.warning(RamboConstants.PLUGIN_TAG + " The player armor part configuration '" + armorPartConf + "' is unknown or invalid.");
			}
		}

		if (_plugin.isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "There are " + ramboGearConfiguration.size() + " parts of player armor stored.");
		}
	}
	
	// TODO : add safe potions / enchants / gears accessors
}
