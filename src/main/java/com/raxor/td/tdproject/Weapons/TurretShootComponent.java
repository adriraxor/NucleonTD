package com.raxor.td.tdproject.Weapons;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.raxor.td.tdproject.EntityType;
import com.raxor.td.tdproject.Maps.TestMap.MobsCore.MobEnemyHealthComponent;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import java.util.List;

public class TurretShootComponent extends Component {
    private double shootTimer;
    private double range;
    private double shootInterval;

    /**
     * Constructeur
     */
    public TurretShootComponent(){
        this.shootInterval = 50.0; // Intervalle de tir en secondes
        this.shootTimer = 0.0;
        this.range = 200.0; // Portée de la tourelle
    }

    /**
     * Thread
     * @param tpf
     */
    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);

        //--- Récupération de tous les ennemies contenu dans la scène de type Enemy

        List<Entity> enemies = FXGL.getGameWorld().getEntitiesByType(EntityType.ZOMBIE);

        //--- Instanciation de l'entité détecté le plus proche

        Entity nearestEnemy = findNearestEnemy(enemies);

        if (nearestEnemy != null && shootTimer==0) {
            Bullet_Classic bullet_classic = new Bullet_Classic();
            FXGL.getGameWorld().addEntity(bullet_classic);

            if (entity.distance(nearestEnemy) <= range) {
                Point2D target = new Point2D(nearestEnemy.getX(), nearestEnemy.getY());

                FXGL.animationBuilder()
                        .duration(Duration.seconds(0.10)) // This duration value can be adjusted as needed
                        .onFinished(() -> isCollide(bullet_classic, nearestEnemy))
                        .interpolator(Interpolators.EXPONENTIAL.EASE_OUT()) // The interpolator can be changed to alter the motion effect
                        .translate(bullet_classic)
                        .from(entity.getPosition())
                        .to(target)
                        .buildAndPlay();

                shootTimer++;
            }
        }

        if(shootTimer>0 && shootTimer<shootInterval){
            shootTimer++;
        } else {
            shootTimer = 0.0;
        }
    }

    /**
     * Méthode appelé lorsque l'animation est terminé. Ce qui signifie qu'il y a collision entre la balle et l'ennemy.
     * @param bullet_classic
     * @param nearestEnemy
     */
    private void isCollide(Bullet_Classic bullet_classic, Entity nearestEnemy){
        System.out.println("Hit !");

        try{
            nearestEnemy.getComponent(MobEnemyHealthComponent.class).decreaseHealth(bullet_classic.getDamage());
            FXGL.getGameWorld().removeEntity(bullet_classic);
        } catch (Exception ex){
            FXGL.getGameWorld().removeEntity(bullet_classic);
        }
    }

    private Entity findNearestEnemy(List<Entity> enemies) {
        Entity nearestEnemy = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Entity enemy : enemies) {
            double distance = entity.distance(enemy);
            if (distance < range && distance < nearestDistance) {
                nearestDistance = distance;
                nearestEnemy = enemy;
            }
        }

        return nearestEnemy;
    }


}
