package com.raxor.td.tdproject;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.raxor.td.tdproject.Maps.TestMap.BigZombieEnemyFactory;
import com.raxor.td.tdproject.Maps.TestMap.ZombieEnemyFactory;
import com.raxor.td.tdproject.Weapons.TurretFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import static com.raxor.td.tdproject.Maps.TestMap._TestMapCore.displayLimitsWaypoints;
import static com.raxor.td.tdproject.Maps.TestMap._TestMapCore.displayWaypoints;

/**
 *
 */
public class Main extends GameApplication {
    private int mobCounter = 0;
    private int limitTurret = 5;
    private int turretPlaced = 0;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initialisation
     */
    @Override
    protected void initGame() {
        super.initGame();

        FXGL.getGameWorld().addEntityFactory(new ZombieEnemyFactory());
        FXGL.getGameWorld().addEntityFactory(new BigZombieEnemyFactory());
        FXGL.getGameWorld().addEntityFactory(new TurretFactory());

        displayWaypoints();
        displayLimitsWaypoints();

        /**
         * Evenement clic sur la scène permettant de poser une tourelle
         */
        FXGL.getInput().addAction(new UserAction("spawn_turret") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();

                if(turretPlaced < limitTurret) {
                    double x = FXGL.getInput().getMouseXWorld();
                    double y = FXGL.getInput().getMouseYWorld();

                    FXGL.spawn("classic_turret", x, y);
                }

                turretPlaced++;
            }
        }, MouseButton.PRIMARY);

        /**
         * Apparition des mobs au fil du temps
         */
        FXGL.getGameTimer().runAtInterval(() -> {

            FXGL.spawn("zombie_mob", 50, 50);

            if(mobCounter>50){
                FXGL.spawn("big_zombie_mob", 50, 50);
            }

            mobCounter++;
        }, Duration.seconds(1));
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setTitle("Project TD");
    }
}