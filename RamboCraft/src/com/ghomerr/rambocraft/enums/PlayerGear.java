package com.ghomerr.rambocraft.enums;

import java.util.HashMap;

import org.bukkit.Material;

import com.ghomerr.rambocraft.utils.RamboStringUtils;

/**
 * Player Gear
 * 
 * @author Ghomerr
 */
public enum PlayerGear
{
	LEATHER_HELMET("leather_helmet", Material.LEATHER_HELMET, RamboItemType.HELMET, 2, 1),

	LEATHER_CHESTPLATE("leather_chestplate", Material.LEATHER_CHESTPLATE, RamboItemType.CHESTPLATE, 2, 1),

	LEATHER_LEGGINGS("leather_leggings", Material.LEATHER_LEGGINGS, RamboItemType.LEGGINGS, 2, 1),

	LEATHER_BOOTS("leather_boots", Material.LEATHER_BOOTS, RamboItemType.BOOTS, 2, 1),

	STONE_SWORD("stone_sword", Material.STONE_SWORD, RamboItemType.WEAPON, 5, 1),
	
	BOW("bow", Material.BOW, RamboItemType.WEAPON, 2, 1),
	
	ARROWS("arrows", Material.ARROW, RamboItemType.OTHER, 5, 1),

	UNKNOWN_ITEM(null, null, null, -1, -1);

	public String name;
	public Material mat;
	public RamboItemType type;
	public int max;
	public int min;

	public static HashMap<String, PlayerGear> gearList = new HashMap<String, PlayerGear>();

	/**
	 * Constructor
	 * @param name
	 * @param mat
	 * @param type
	 * @param max
	 * @param min
	 */
	PlayerGear(final String name, final Material mat, final RamboItemType type, final int max, final int min)
	{
		this.name = name;
		this.mat = mat;
		this.type = type;
		this.max = max;
		this.min = min;
	}

	static
	{
		for (final PlayerGear pg : PlayerGear.values())
		{
			gearList.put(pg.name, pg);
		}
	}

	/**
	 * Get The RamboGear from its name
	 * 
	 * @param name
	 * @return the RamboGear
	 */
	public static PlayerGear getPlayerGear(final String name)
	{
		if (RamboStringUtils.isNotBlank(name))
		{
			final PlayerGear pg = gearList.get(name);
			if (pg == null)
			{
				return UNKNOWN_ITEM;
			}
			else
			{
				return pg;
			}
		}
		else
		{
			return UNKNOWN_ITEM;
		}
	}
}
