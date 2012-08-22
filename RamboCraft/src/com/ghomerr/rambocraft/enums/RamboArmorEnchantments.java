package com.ghomerr.rambocraft.enums;

import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;

import com.ghomerr.rambocraft.utils.RamboStringUtils;

/**
 * Armor Enchantments
 * 
 * @author Ghomerr
 */
public enum RamboArmorEnchantments
{
	ENVIRONMENTAL("environmental", Enchantment.PROTECTION_ENVIRONMENTAL),

	EXPLOSIONS("explosions", Enchantment.PROTECTION_EXPLOSIONS),

	FALL("fall", Enchantment.PROTECTION_FALL),

	FIRE("fire", Enchantment.PROTECTION_FIRE),

	PROJECTILE("projectile", Enchantment.PROTECTION_PROJECTILE),

	UNKNOWN_ENCHANT(null, null);

	public String name;
	public Enchantment enchant;

	public static HashMap<String, RamboArmorEnchantments> enchantsList = new HashMap<String, RamboArmorEnchantments>();

	/**
	 * Constructor
	 * @param name
	 * @param enchant
	 */
	RamboArmorEnchantments(final String name, final Enchantment enchant)
	{
		this.name = name;
		this.enchant = enchant;
	}

	static
	{
		for (final RamboArmorEnchantments rae : RamboArmorEnchantments.values())
		{
			enchantsList.put(rae.name, rae);
		}
	}

	/**
	 * Get the Armor Enchantment from its name
	 * @param name
	 * @return the Armor Enchantment
	 */
	public static RamboArmorEnchantments getRamboArmorEnchantment(final String name)
	{
		if (RamboStringUtils.isNotBlank(name))
		{
			final RamboArmorEnchantments rae = enchantsList.get(name);
			if (rae == null)
			{
				return UNKNOWN_ENCHANT;
			}
			else
			{
				return rae;
			}
		}
		else
		{
			return UNKNOWN_ENCHANT;
		}
	}
}
