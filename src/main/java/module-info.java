module com.raxor.td.tdproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.raxor.td.tdproject to javafx.fxml;
    exports com.raxor.td.tdproject;
    exports com.raxor.td.tdproject.Maps.TestMap;
    exports com.raxor.td.tdproject.Weapons;
    exports com.raxor.td.tdproject.Maps.TestMap.MobsCore;
}