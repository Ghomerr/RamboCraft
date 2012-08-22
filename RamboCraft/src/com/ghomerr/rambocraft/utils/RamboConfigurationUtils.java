package com.ghomerr.rambocraft.utils;

import java.util.logging.Logger;

import org.bukkit.enchantments.Enchantment;

import com.ghomerr.rambocraft.constants.RamboConstants;
import com.ghomerr.rambocraft.enums.PlayerGear;

/**
 * Configuration Utils
 * 
 * @author Ghomerr
 */
public class RamboConfigurationUtils
{
	private static final Logger _LOGGER = Logger.getLogger(RamboConstants.MINECRAFT);
	public static boolean isDebugEnabled = false;
	
	/**
	 * Get valid amplifier potion effect (from 0 to 3)
	 * 
	 * @param strAmplifier in number or "min" or "max"
	 * @return the valid amplifier value
	 */
	public static int getValidPotionAmplifier(final String strAmplifier)
	{
		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "getValidPotionAmplifier(strAmplifier=" + strAmplifier + ")");
		}
		
		if (RamboStringUtils.isInteger(strAmplifier))
		{
			final int amplifier = Integer.parseInt(strAmplifier) - 1;
			return (amplifier > RamboConstants.MAX_POTION_AMPLIFIER) ? RamboConstants.MAX_POTION_AMPLIFIER
					: (amplifier < RamboConstants.MIN_POTION_AMPLIFIER) ? RamboConstants.MIN_POTION_AMPLIFIER : amplifier;
		}
		else
		{
			if (RamboConstants.MAX.equalsIgnoreCase(strAmplifier))
			{
				return RamboConstants.MAX_POTION_AMPLIFIER;
			}
			else if (RamboConstants.MIN.equalsIgnoreCase(strAmplifier))
			{
				return RamboConstants.MIN_POTION_AMPLIFIER;
			}
			else
			{
				return -1;
			}
		}
	}

	/**
	 * Get valid enchantment level according to the selected enchantment
	 * 
	 * @param strLevel in number or "min" or "max"
	 * @param enchant
	 * @return the valid enchantment level
	 */
	public static int getValidEnchantLevel(final String strLevel, final Enchantment enchant)
	{
		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "getValidEnchantLevel(strLevel=" + strLevel + ", enchant=" + enchant + ")");
		}
		
		if (RamboStringUtils.isInteger(strLevel))
		{
			final int level = Integer.parseInt(strLevel);
			return (level > enchant.getMaxLevel()) ? enchant.getMaxLevel() : (level < enchant.getStartLevel()) ? enchant.getStartLevel() : level;
		}
		else
		{
			if (RamboConstants.MAX.equalsIgnoreCase(strLevel))
			{
				return enchant.getMaxLevel();
			}
			else if (RamboConstants.MIN.equalsIgnoreCase(strLevel))
			{
				return enchant.getStartLevel();
			}
			else
			{
				return -1;
			}
		}
	}

	/**
	 * Get a valid amount of configured player item
	 * 
	 * @param amount
	 * @param item
	 * @return the valid amount
	 */
	public static int getValidItemAmount(final String strAmount, final PlayerGear item)
	{
		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "getValidItemAmount(strAmount=" + strAmount + ", item=" + item + ")");
		}
		
		if (RamboStringUtils.isInteger(strAmount))
		{
			final int amount = Integer.parseInt(strAmount);
			return (amount > item.max) ? item.max : (amount < item.min) ? item.min : amount;
		}
		else
		{
			if (RamboConstants.MAX.equalsIgnoreCase(strAmount))
			{
				return item.max;
			}
			else if (RamboConstants.MIN.equalsIgnoreCase(strAmount))
			{
				return item.min;
			}
			else
			{
				return -1;
			}
		}
	}
}
