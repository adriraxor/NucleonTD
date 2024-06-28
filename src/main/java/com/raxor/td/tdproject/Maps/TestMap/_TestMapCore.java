package com.raxor.td.tdproject.Maps.TestMap;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Arrays;
import java.util.List;

public class _TestMapCore {

    /**
     * On définis le chemin que doit suivre les enemies
     * @return
     */
    public static List<Point2D> getEnemyPathWaypoints(){
        return Arrays.asList(
                new Point2D(50, 50),
                new Point2D(FXGL.getAppWidth() - 50, 50), // →
                new Point2D(FXGL.getAppWidth() - 50,  200), // ↓
                new Point2D(50,  200), // ←
                new Point2D(50,  400), // ↓
                new Point2D(FXGL.getAppWidth() - 50,  400), // →
                new Point2D(FXGL.getAppWidth() - 50,  700) // ↓
        );
    }

    public static List<Point2D> getPathLimitWaypoints(){
        return Arrays.asList(
            new Point2D(50, -20),
            new Point2D(50, 40),
            new Point2D(50, 20)
        );
    }

    public static void displayLimitsWaypoints(){
        List<Point2D> waypoints = getPathLimitWaypoints();

        for(Point2D waypoint: waypoints){
            Circle circle = new Circle(waypoint.getX(), waypoint.getY(), 2, Color.ORANGE);
            FXGL.getGameScene().addUINode(circle);
        }
    }

    /**
     * Affichage du pathfinding
     */
    public static void displayWaypoints(){
        List<Point2D> waypoints = getEnemyPathWaypoints();

        for(Point2D waypoint: waypoints){
            Circle circle = new Circle(waypoint.getX(), waypoint.getY(), 2, Color.RED);
            FXGL.getGameScene().addUINode(circle);
        }

        for(int i=0; i< waypoints.size() - 1; i++){
            Point2D start = waypoints.get(i);
            Point2D end = waypoints.get(i + 1);

            Line line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
            line.setStroke(Color.RED);
            line.setStrokeWidth(2);

            FXGL.getGameScene().addUINode(line);
        }
    }
}
