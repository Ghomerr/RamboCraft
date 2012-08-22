package com.ghomerr.rambocraft.enums;

import java.util.HashMap;

import org.bukkit.Material;

import com.ghomerr.rambocraft.utils.RamboStringUtils;

/**
 * Rambo Gear
 * 
 * @author Ghomerr
 */
public enum RamboGear
{
	GOLD_HELMET("gold_helmet", Material.GOLD_HELMET, RamboItemType.HELMET),

	GOLD_CHESTPLATE("gold_chestplate", Material.GOLD_CHESTPLATE, RamboItemType.CHESTPLATE),

	GOLD_LEGGINGS("gold_leggings", Material.GOLD_LEGGINGS, RamboItemType.LEGGINGS),

	GOLD_BOOTS("gold_boots", Material.GOLD_BOOTS, RamboItemType.BOOTS),

	UNKNOWN_ITEM(null, null, null);

	public String name;
	public Material mat;
	public RamboItemType type;

	public static HashMap<String, RamboGear> gearList = new HashMap<String, RamboGear>();

	/**
	 * Constructor
	 * @param name
	 * @param mat
	 * @param type
	 */
	RamboGear(final String name, final Material mat, final RamboItemType type)
	{
		this.name = name;
		this.mat = mat;
		this.type = type;
	}

	static
	{
		for (final RamboGear rg : RamboGear.values())
		{
			gearList.put(rg.name, rg);
		}
	}

	/**
	 * Get The RamboGear from its name
	 * @param name
	 * @return the RamboGear
	 */
	public static RamboGear getRamboGear(final String name)
	{
		if (RamboStringUtils.isNotBlank(name))
		{
			final RamboGear rg = gearList.get(name);
			if (rg == null)
			{
				return UNKNOWN_ITEM;
			}
			else
			{
				return rg;
			}
		}
		else
		{
			return UNKNOWN_ITEM;
		}
	}
}
