package es.ies.lavereda.programaprocesos;

import es.ies.lavereda.programaprocesos.udp.Cliente;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import org.controlsfx.control.NotificationPane;

import java.io.*;

public class HelloController {

    @FXML
    private Label idApache;

    @FXML
    private Label idMaria;

    @FXML
    private CheckBox checkedA;

    @FXML
    private CheckBox checkedM;

    @FXML
    NotificationPane np = new NotificationPane();

    String[] str = {"systemctl", "stop", "mariadb.service", "/etc/init.d/apache2", "stop"};

    @FXML
    ProcessBuilder pbA = new ProcessBuilder();
    ProcessBuilder pbM = new ProcessBuilder();
    ProcessBuilder borrar = new ProcessBuilder(str);

    @FXML
    Process processA;
    Process processB;

    long codA = 0;
    long codM = 0;
    @FXML
    protected void onCreateNewProcessA() {
        Cliente cas = new Cliente();
        cas.enviaMSJ("apache");
    }


    @FXML
    protected void onCreateNewProcessM() {
        Cliente cas = new Cliente();
        cas.enviaMSJ("mariadb");
    }

    @FXML
    protected void onDestroyProcess(){
        Cliente cas = new Cliente();
        cas.enviaMSJ("dead");
    }

    @FXML
    protected void onStopA(){
        Cliente cas = new Cliente();
        cas.enviaMSJ("stop apache");


    }

    @FXML
    protected void onStopM(){
        Cliente cas = new Cliente();
        cas.enviaMSJ("stop mariadb");


    }
}