module es.ies.lavereda.programaprocesos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens es.ies.lavereda.programaprocesos to javafx.fxml;
    exports es.ies.lavereda.programaprocesos;
}