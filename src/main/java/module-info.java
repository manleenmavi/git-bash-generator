module me.mvdev.gitbgen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens me.mvdev.gitbgen to javafx.fxml;
//    exports me.mvdev.gitbgen;
    exports me.mvdev.gitbgen.controller;
    opens me.mvdev.gitbgen.controller to javafx.fxml;
    exports me.mvdev.gitbgen;
}