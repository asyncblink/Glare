package com.gmail.mooman219.bull;

import com.liquiddonut.CustomData;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class CDEntity extends CustomData {
    private final Entity entity;

    private CDEntity(Entity entity) {
        this.entity = entity;
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

    public Entity getEntity() {
        return entity;
    }

    public net.minecraft.server.v1_11_R1.Entity getHandle() {
        return ((CraftEntity)entity).getHandle();
    }

    public static CDEntity get(Entity entity) {
        net.minecraft.server.v1_11_R1.Entity handle = ((CraftEntity)entity).getHandle();
        if(entity instanceof LivingEntity) {
            throw new IllegalArgumentException("LivingEntities are not considered Entities.");
        } else if(handle.livePlayer == null) {
            handle.livePlayer = new CDEntity(entity);
        }
        CDEntity cdEntity = (CDEntity) handle.livePlayer;
        cdEntity.onGet();
        return cdEntity;
    }
}
