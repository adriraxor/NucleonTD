package com.raxor.td.tdproject.Maps.TestMap.MobsCore;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.util.List;

public class MobEnemyMoveComponent extends Component{

    private double speed;
    private List<Point2D> enemyPathWaypoints;
    private int waypointIndex = 0;

    /**
     * Constructeur ou l'on passe la vitesse que le mob possédera
     * @param speed
     */
    public MobEnemyMoveComponent(double speed, List<Point2D> path) {
        this.speed = speed;
        this.enemyPathWaypoints = path;
    }

    @Override
    public void onUpdate(double tpf) {
        if (waypointIndex < enemyPathWaypoints.size()) {
            Point2D nextPoint = enemyPathWaypoints.get(waypointIndex);
            Point2D direction = nextPoint.subtract(entity.getPosition()).normalize();

            entity.translate(direction.multiply(this.speed * 0.040));

            if (entity.getPosition().distance(nextPoint) < 5) {
                waypointIndex++;
            }
        } else {
            entity.removeFromWorld();
        }
    }
}
