package com.raxor.td.tdproject.Weapons;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.raxor.td.tdproject.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class TurretFactory implements EntityFactory {

    @Spawns("classic_turret")
    public Entity newClassicTurret(SpawnData data) {

        Random random = new Random();

        return FXGL.entityBuilder(data)
                .viewWithBBox(new Rectangle(50, 50, Color.GREEN))
                .collidable()
                .type(EntityType.TURRET)
                .with(new TurretShootComponent())
                .build();
    }
}
