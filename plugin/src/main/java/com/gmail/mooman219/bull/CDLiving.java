package com.gmail.mooman219.bull;

import com.liquiddonut.CustomData;
import net.minecraft.server.v1_11_R1.EntityLiving;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CDLiving extends CustomData {
    // ▀▀▀▀▀▀▀▀▀▀ Idea for mob health bar
    private final LivingEntity livingEntity;

    private CDLiving(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    /*
     * Live
     */

    /*
     * Event
     */

    /*
     * BullData
     */

    public LivingEntity getEntity() {
        return livingEntity;
    }

    public EntityLiving getHandle() {
        return ((CraftLivingEntity)livingEntity).getHandle();
    }

    public static CDLiving get(LivingEntity livingEntity) {
        EntityLiving handle = ((CraftLivingEntity)livingEntity).getHandle();
        if(livingEntity instanceof Player) {
            throw new IllegalArgumentException("Players are not considered LivingEntities.");
        } else if(handle.livePlayer == null) {
            handle.livePlayer = new CDLiving(livingEntity);
        }
        CDLiving cdLiving = (CDLiving) handle.livePlayer;
        cdLiving.onGet();
        return cdLiving;
    }

    /**
     * This method will save any of the CDLiving data then remove it from the LivingEntity object.
     * This should be called when the LivingEntity dies to prevent the data from presisting.
     */
    public static void unload(LivingEntity livingEntity) {
        EntityLiving handle = ((CraftLivingEntity)livingEntity).getHandle();
        if(livingEntity instanceof Player) {
            throw new IllegalArgumentException("Players are not considered LivingEntities.");
        } else if(handle.livePlayer != null) {
            handle.livePlayer = null;
        }
    }
}
