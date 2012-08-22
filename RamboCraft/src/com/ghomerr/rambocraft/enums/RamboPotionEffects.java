package com.ghomerr.rambocraft.enums;

import java.util.HashMap;

import net.minecraft.server.MobEffectList;

import com.ghomerr.rambocraft.utils.RamboStringUtils;

/**
 * Potion Effects applied to a Rambo Player
 * 
 * @author Ghomerr
 */
public enum RamboPotionEffects
{
	JUMP("jump", MobEffectList.JUMP),

	REGENERATION("regeneration", MobEffectList.REGENERATION),

	SPEED("speed", MobEffectList.FASTER_MOVEMENT),

	DAMAGE_RESISTANCE("damage_resistance", MobEffectList.RESISTANCE),

	INCREASE_DAMAGE("increase_damage",MobEffectList.INCREASE_DAMAGE),
	
	UNKNOWN_EFFECT(null, null);
	
	public String name;
	public MobEffectList type;
	
	public static HashMap<String, RamboPotionEffects> effectsList = new HashMap<String, RamboPotionEffects>();


	/**
	 * Constructor
	 * @param name of the potion effect in lower case
	 * @param type of the potion effect
	 */
	RamboPotionEffects(final String name, final MobEffectList type)
	{
		this.name = name;
		this.type = type;
	}

	static
	{
		for (final RamboPotionEffects rpe : RamboPotionEffects.values())
		{
			effectsList.put(rpe.name, rpe);
		}
	}
	
	public static RamboPotionEffects getRamboPotionEffect(final String name)
	{
		if (RamboStringUtils.isNotBlank(name))
		{
			final RamboPotionEffects rpe = effectsList.get(name);
			if (rpe == null)
			{
				return UNKNOWN_EFFECT;
			}
			else
			{
				return rpe;
			}
		}
		else
		{
			return UNKNOWN_EFFECT;
		}
	}
}
