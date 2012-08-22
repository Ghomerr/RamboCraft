package com.ghomerr.rambocraft.utils;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.MobEffect;

import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.ghomerr.rambocraft.constants.RamboConstants;
import com.ghomerr.rambocraft.enums.PlayerGear;
import com.ghomerr.rambocraft.enums.RamboArmorEnchantments;
import com.ghomerr.rambocraft.enums.RamboBowEnchantments;
import com.ghomerr.rambocraft.enums.RamboGear;
import com.ghomerr.rambocraft.enums.RamboItemType;
import com.ghomerr.rambocraft.enums.RamboPotionEffects;
import com.ghomerr.rambocraft.objects.RamboConfigurationContainer;

/**
 * Utils for Players
 * 
 * @author Ghomerr
 */
public class RamboPlayerUtils
{
	private static final Logger _LOGGER = Logger.getLogger(RamboConstants.MINECRAFT);

	public static boolean isDebugEnabled = false;
	public static boolean displayTestDebugMessages = true;

	/**
	 * Add Potions Effects on a Player to make him a Rambo
	 * 
	 * @param container configuration
	 * @param player to apply potions effects
	 * @param durationInMinutes of the potions effects
	 * @param isInfinite to use default infinite value
	 * @return true if it succeeded
	 */
	public static boolean addRamboPotionsEffects(final RamboConfigurationContainer container, final Player player, final int durationInMinutes,
			final boolean isInfinite)
	{
		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "addRamboPotionsEffects(player=" + player + ")");
		}

		boolean result = true;

		for (final RamboPotionEffects potionEffect : container.potionEffectsConfiguration.keySet())
		{
			if (isDebugEnabled && displayTestDebugMessages)
			{
				_LOGGER.info(RamboConstants.DEBUG_TAG + "adding effect " + potionEffect.name);
			}

			final int duration = (isInfinite) ? RamboConstants.INIFITE_POTION_EFFECT_DURATION : durationInMinutes * 1200;

			try
			{
				final int amplifier = container.potionEffectsConfiguration.get(potionEffect).intValue();
				if (isDebugEnabled)
				{
					_LOGGER.info(RamboConstants.DEBUG_TAG + "duration=" + duration + ", amplifier=" + amplifier);
				}

				((CraftPlayer) player).getHandle().addEffect(new MobEffect(potionEffect.type.getId(), duration, amplifier));
			}
			catch (final Throwable th)
			{
				_LOGGER.severe(RamboConstants.PLUGIN_TAG + " Error while adding potion effect " + potionEffect.name);
				th.printStackTrace();
				result = false;
				break;
			}
		}

		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "addRamboPotionsEffects() -> " + result);
		}

		return result;
	}

	/**
	 * Add Potions Effects on a Player to make him a Rambo using the infinite duration
	 * 
	 * @param plugin
	 * @param player to apply potions effects
	 * @return true if it succeeded
	 */
	public static boolean addRamboPotionsEffects(final RamboConfigurationContainer container, final Player player)
	{
		return addRamboPotionsEffects(container, player, RamboConstants.INIFITE_POTION_EFFECT_DURATION, true);
	}

	/**
	 * Remove the Potions Effects on a Player
	 * 
	 * @param player
	 * @return true if it succeeded
	 */
	public static boolean removeRamboPotionsEffects(final RamboConfigurationContainer container, final Player player)
	{
		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "removeRamboPotionsEffects(player=" + player + ")");
		}

		boolean result = true;

		if (isDebugEnabled && displayTestDebugMessages)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + container.potionEffectsConfiguration.size() + " potions effects to remove");
		}

		final EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();

		for (final RamboPotionEffects potionEffect : container.potionEffectsConfiguration.keySet())
		{

			if (isDebugEnabled && displayTestDebugMessages)
			{
				_LOGGER.info(RamboConstants.DEBUG_TAG + "removing effect " + potionEffect.name);
			}

			if (entityPlayer.hasEffect(potionEffect.type))
			{
				try
				{
					final int amplifier = entityPlayer.getEffect(potionEffect.type).getAmplifier() + 1;
					entityPlayer.addEffect(new MobEffect(potionEffect.type.getId(), -1, amplifier));
				}
				catch (final Throwable th)
				{
					_LOGGER.severe(RamboConstants.PLUGIN_TAG + " Error while removing potion effect " + potionEffect.name);
					th.printStackTrace();
					result = false;
					break;
				}
			}
			else
			{
				if (isDebugEnabled && displayTestDebugMessages)
				{
					_LOGGER.info(RamboConstants.DEBUG_TAG + player.getName() + " has not the effect " + potionEffect.name);
				}
			}
		}

		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "removeRamboPotionsEffects() -> " + result);
		}

		return result;
	}

	/**
	 * Equip the Rambo Gear configured
	 * @param container
	 * @param player
	 * @return true if everything has worked
	 */
	public static boolean addRamboGear(final RamboConfigurationContainer container, final Player player)
	{
		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "addRamboGear(player=" + player + ")");
		}

		boolean result = true;

		clearFullInventory(player);
		final PlayerInventory inventory = player.getInventory();

		// Rambo Bow
		final ItemStack ramboBow = new ItemStack(Material.BOW, 1);
		for (final RamboBowEnchantments rbe : container.bowEnchantsConfiguration.keySet())
		{
			if (isDebugEnabled && displayTestDebugMessages)
			{
				_LOGGER.info(RamboConstants.DEBUG_TAG + "adding bow enchant " + rbe.name);
			}

			final int level = container.bowEnchantsConfiguration.get(rbe).intValue();

			ramboBow.addEnchantment(Enchantment.ARROW_FIRE, level);
		}
		inventory.addItem(ramboBow);

		// Rambo Gear + Enchants
		final RamboGear[] gear = (RamboGear[]) container.ramboGearConfiguration.keySet().toArray();
		int i = 0;
		for (final RamboArmorEnchantments rae : container.armorEnchantsConfiguration.keySet())
		{
			container.ramboGearConfiguration.get(gear[i]).addEnchantment(rae.enchant, container.armorEnchantsConfiguration.get(rae).intValue());
			i = (i < gear.length) ? i + 1 : 0;
		}

		HashMap<Integer, ItemStack> refusedItems = new HashMap<Integer, ItemStack>();
		
		// Equip Armor
		for (final RamboGear ramboGear : container.ramboGearConfiguration.keySet())
		{
			final ItemStack item = container.ramboGearConfiguration.get(ramboGear);
			refusedItems.putAll(equipItem(item, ramboGear.type, inventory));
		}
		
		if (!refusedItems.isEmpty())
		{
			_LOGGER.warning(RamboConstants.PLUGIN_TAG + refusedItems.size() + " parts of Rambo Gear failed to be added.");
			result = false;
		}

		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "addRamboGear() -> " + result);
		}

		return result;
	}
	
	/**
	 * Equipe the Player Gear configured
	 * @param container
	 * @param player
	 * @return true if everything has worked
	 */
	public static boolean addPlayerGear(final RamboConfigurationContainer container, final Player player)
	{
		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "addPlayerGear(player=" + player + ")");
		}

		boolean result = true;

		clearFullInventory(player);
		final PlayerInventory inventory = player.getInventory();

		HashMap<Integer, ItemStack> refusedItems = new HashMap<Integer, ItemStack>();
		
		// Equip Player Gear
		for (final PlayerGear playerGear : container.playerGearConfiguration.keySet())
		{
			final List<ItemStack> stacks = container.playerGearConfiguration.get(playerGear);
			for (final ItemStack item : stacks)
			{
				refusedItems.putAll(equipItem(item, playerGear.type, inventory));
			}
		}
		
		if (!refusedItems.isEmpty())
		{
			_LOGGER.warning(RamboConstants.PLUGIN_TAG + refusedItems.size() + " parts of Player Gear failed to be added.");
			result = false;
		}

		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "addRamboGear() -> " + result);
		}

		return result;
	}

	/**
	 * Clear all player's inventory (armors and items)
	 * 
	 * @param player
	 */
	public static void clearFullInventory(final Player player)
	{
		final PlayerInventory inventory = player.getInventory();

		inventory.setArmorContents(null);
		inventory.clear();
	}
	
	/**
	 * Equip Player or Rambo gear
	 * @param itemToAdd
	 * @param type
	 * @param inventory
	 * @return refused items
	 */
	public static HashMap<Integer, ItemStack> equipItem(final ItemStack itemToAdd, final RamboItemType type, final PlayerInventory inventory)
	{
		if (isDebugEnabled)
		{
			_LOGGER.info(RamboConstants.DEBUG_TAG + "equipItem(item=" + itemToAdd + ", type=" + type + ", inventory=" + inventory + ")");
		}
		
		ItemStack item = null;
		
		HashMap<Integer, ItemStack> refusedItems = new HashMap<Integer, ItemStack>();
		
		switch(type)
		{
			case HELMET:
				item = inventory.getHelmet();
				if (item == null || item.getType() == Material.AIR)
				{
					inventory.setHelmet(itemToAdd);
				}
				else
				{
					refusedItems.putAll(inventory.addItem(itemToAdd));
				}
				break;
			case CHESTPLATE:
				item = inventory.getChestplate();
				if (item == null || item.getType() == Material.AIR)
				{
					inventory.setChestplate(itemToAdd);
				}
				else
				{
					refusedItems.putAll(inventory.addItem(itemToAdd));
				}
				break;
			case LEGGINGS:
				item = inventory.getLeggings();
				if (item == null || item.getType() == Material.AIR)
				{
					inventory.setLeggings(itemToAdd);
				}
				else
				{
					refusedItems.putAll(inventory.addItem(itemToAdd));
				}
				break;
			case BOOTS:
				item = inventory.getBoots();
				if (item == null || item.getType() == Material.AIR)
				{
					inventory.setBoots(itemToAdd);
				}
				else
				{
					refusedItems.putAll(inventory.addItem(itemToAdd));
				}
				break;
			case WEAPON:
				item = inventory.getItemInHand();
				if (item == null || item.getType() == Material.AIR)
				{
					inventory.setItemInHand(itemToAdd);
					break;
				}
			default:
				refusedItems.putAll(inventory.addItem(itemToAdd));
		}
		
		return refusedItems;
	}
}
