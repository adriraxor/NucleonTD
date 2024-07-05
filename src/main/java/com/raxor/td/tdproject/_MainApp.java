package com.raxor.td.tdproject;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.scene.layout.Pane;

import static com.almasb.fxgl.app.GameApplication.launch;

public class _MainApp extends FXGLMenu {

    public _MainApp(MenuType type) {
        super(type);

        getContentRoot().getChildren().addAll( new Pane() );
    }


}
