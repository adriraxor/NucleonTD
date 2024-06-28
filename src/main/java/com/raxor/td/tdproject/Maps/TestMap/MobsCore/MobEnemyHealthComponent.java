package com.raxor.td.tdproject.Maps.TestMap.MobsCore;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.raxor.td.tdproject.Maps.TestMap._TestMapCore;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class MobEnemyHealthComponent extends Component {

    private int health;
    private Text healthText;
    private List<Point2D> enemyPathWaypoints;
    private int waypointIndex;

    public MobEnemyHealthComponent(int pHealth){
        this.health = pHealth;

        //--- on définis
        this.healthText = new Text(String.valueOf(this.health));
        this.healthText.setFill(Color.RED);
        this.healthText.setTranslateY(-20);
        this.enemyPathWaypoints = _TestMapCore.getEnemyPathWaypoints();
    }

    @Override
    public void onAdded() {
        super.onAdded();
        FXGL.getGameScene().addUINode(this.healthText);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);

        Point2D nextPoint = enemyPathWaypoints.get(waypointIndex);

        //--- je déplace le texte en fonction de la position de l'entité

        healthText.setTranslateX(entity.getX());
        healthText.setTranslateY(entity.getY() - 20);

        //--- Si plus de vie on détruit l'entité et le texte

        if (entity.getPosition().distance(nextPoint) < 5) {
            waypointIndex++;
        }

        if(waypointIndex >= enemyPathWaypoints.size()){
            FXGL.removeUINode(healthText);
        }

        if(this.health<=0){
            FXGL.removeUINode(healthText);
            entity.removeFromWorld();
        }
    }

    public void decreaseHealth(int amount) {
        this.health -= amount;
        this.healthText.setText(String.valueOf(this.health));
    }
}
