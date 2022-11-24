package es.ies.lavereda.programaprocesos.udp;

import java.io.Serializable;

public class Mensaje implements Serializable {
    private String comando;

    public Mensaje(String comando) {
        this.comando = comando;
    }

    public int getMessage(){
        switch (comando){
            case "mariadb":
                return 1;
            case "apache":
                return 2;
            case "dead":
                return 3;
            case "stop mariadb":
                return 4;
            case "stop apache":
                return 5;
            default:
                return -1;
        }
    }
}
