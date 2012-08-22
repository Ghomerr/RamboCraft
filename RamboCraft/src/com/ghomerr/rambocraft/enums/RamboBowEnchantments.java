package com.ghomerr.rambocraft.enums;

import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;

import com.ghomerr.rambocraft.utils.RamboStringUtils;

/**
 * Bow Enchantments
 * 
 * @author Ghomerr
 */
public enum RamboBowEnchantments
{
	FIRE("fire", Enchantment.ARROW_FIRE),

	DAMAGE("damage", Enchantment.ARROW_DAMAGE),

	INFINITE("infinite", Enchantment.ARROW_INFINITE),

	KNOCKBACK("knockback", Enchantment.ARROW_KNOCKBACK),

	UNKNOWN_ENCHANT(null, null);

	public String name;
	public Enchantment enchant;

	public static HashMap<String, RamboBowEnchantments> enchantsList = new HashMap<String, RamboBowEnchantments>();

	/**
	 * Constructor
	 * @param name
	 * @param enchant
	 */
	RamboBowEnchantments(final String name, final Enchantment enchant)
	{
		this.name = name;
		this.enchant = enchant;
	}

	static
	{
		for (final RamboBowEnchantments rbe : RamboBowEnchantments.values())
		{
			enchantsList.put(rbe.name, rbe);
		}
	}

	/**
	 * Get The Bow Enchantment from its name
	 * @param name
	 * @return the Bow Enchantment
	 */
	public static RamboBowEnchantments getRamboBowEnchantment(final String name)
	{
		if (RamboStringUtils.isNotBlank(name))
		{
			final RamboBowEnchantments rbe = enchantsList.get(name);
			if (rbe == null)
			{
				return UNKNOWN_ENCHANT;
			}
			else
			{
				return rbe;
			}
		}
		else
		{
			return UNKNOWN_ENCHANT;
		}
	}
}
