package com.va3zza.mc.xandgort_powers.events;

import java.util.logging.Logger;

import com.va3zza.mc.xandgort_powers.XandgortPlugin;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class PlayerPunchEvent implements Listener {

    private Logger logger;

    public PlayerPunchEvent(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void handlePlayerPunchEvent(EntityDamageByEntityEvent event) {

        // Get both actors
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        // Ensure the entities are Player and LivingEntity
        if (damager instanceof Player && damaged instanceof LivingEntity) {

            // Determine the damaged entity type
            boolean isDamagedPlayer = damaged instanceof Player;

            // The damager must be XANDGORT
            if (damager.getUniqueId().equals(XandgortPlugin.XANDGORT_UUID)) {

                // Get the world of the damaged entity
                World world = damaged.getWorld();

                // Give fire resistance to the damaged
                ((LivingEntity) damaged)
                        .addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 7, 3, false, false));

                // Give health boost to the damager
                ((Player) damager)
                        .addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 14, 20, false, false));
                ((Player) damager)
                        .addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 14, 20, false, false));

                // Smite the damaged
                this.logger.info(String.format("%s has felt the power of nature", damaged.getName()));
                world.strikeLightning(damaged.getLocation());

                // Play extra sounds
                world.playSound(damaged.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 1.0f, 1.0f);
            }
        }
    }
}
