module lr10.oop_lr10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lr10.oop_lr10 to javafx.fxml;
    exports lr10.oop_lr10;
}