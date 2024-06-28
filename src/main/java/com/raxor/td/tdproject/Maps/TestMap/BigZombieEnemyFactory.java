package com.raxor.td.tdproject.Maps.TestMap;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.raxor.td.tdproject.EntityType;
import com.raxor.td.tdproject.Maps.TestMap.MobsCore.MobEnemyHealthComponent;
import com.raxor.td.tdproject.Maps.TestMap.MobsCore.MobEnemyMoveComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class BigZombieEnemyFactory implements EntityFactory {

    @Spawns("big_zombie_mob")
    public Entity newMob(SpawnData data) {

        Random random = new Random();

        return FXGL.entityBuilder(data)
                .viewWithBBox(new Rectangle(30, 30, Color.ORANGE))
                .collidable()
                .type(EntityType.ZOMBIE)
                .with(new MobEnemyHealthComponent(300))
                .with(new MobEnemyMoveComponent(random.nextInt(10, 20), _TestMapCore.getEnemyPathWaypoints()))
                .build();
    }
}
