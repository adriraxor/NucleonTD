package com.raxor.td.tdproject.Maps.TestMap.Mobs;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.raxor.td.tdproject.EntityType;
import com.raxor.td.tdproject.Maps.TestMap.MobsCore.MobEnemyHealthComponent;
import com.raxor.td.tdproject.Maps.TestMap.MobsCore.MobEnemyMoveComponent;
import com.raxor.td.tdproject.Maps.TestMap._TestMapCore;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class ZombieEnemyFactory implements EntityFactory {

    @Spawns("zombie_mob")
    public Entity newMob(SpawnData data) {

        Random random = new Random();

        return FXGL.entityBuilder(data)
                .viewWithBBox(new Rectangle(30, 30, Color.BLUE))
                .collidable()
                .type(EntityType.ZOMBIE)
                .with(new MobEnemyHealthComponent(100))
                .with(new MobEnemyMoveComponent(random.nextInt(20, 50), _TestMapCore.getEnemyPathWaypoints()))
                .build();
    }
}
