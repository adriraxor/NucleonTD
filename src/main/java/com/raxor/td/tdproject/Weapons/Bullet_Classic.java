package com.raxor.td.tdproject.Weapons;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet_Classic extends Entity {

    private int damage = 50;

    public Bullet_Classic(){
        Circle redCircle = new Circle(10, 10, 10, Color.RED);
        getViewComponent().addChild(redCircle);
    }

    public int getDamage() {
        return damage;
    }
}
