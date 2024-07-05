package com.raxor.td.tdproject.Maps.TestMap;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.raxor.td.tdproject.Maps.TestMap.Mobs.BigZombieEnemyFactory;
import com.raxor.td.tdproject.Maps.TestMap.Mobs.ZombieEnemyFactory;
import com.raxor.td.tdproject.Weapons.TurretFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import static com.raxor.td.tdproject.Maps.TestMap._TestMapCore.displayLimitsWaypoints;
import static com.raxor.td.tdproject.Maps.TestMap._TestMapCore.displayWaypoints;

public class MainTestMap extends GameApplication {
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

        /**
         * Ajout des factories
         */
        FXGL.getGameWorld().addEntityFactory(new ZombieEnemyFactory());
        FXGL.getGameWorld().addEntityFactory(new BigZombieEnemyFactory());
        FXGL.getGameWorld().addEntityFactory(new TurretFactory());

        /**
         * Affichage des waypoints
         */
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

            mobCounter++;
        }, Duration.seconds(1));

        /**
         * Apparition du mob BigZombie au bout de 50 mobs classiques qui apparaissent toutes les 10 secondes
         */
        FXGL.getGameTimer().runAtInterval(() -> {

            if(mobCounter >= 10) {
                FXGL.spawn("big_zombie_mob", 50, 50);
            }

        }, Duration.seconds(10));


    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setTitle("Project TD");
    }
}