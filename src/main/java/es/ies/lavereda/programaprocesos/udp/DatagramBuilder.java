package es.ies.lavereda.programaprocesos.udp;

import java.io.*;
import java.net.DatagramPacket;

public class DatagramBuilder<T>{

    private String nombre;
    private int cuenta;

    public DatagramBuilder(String nombre, int cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    public DatagramPacket cifrar(T obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return new DatagramPacket(b.toByteArray(), b.toByteArray().length) ;
    }

    public T deszifrar(DatagramPacket dp) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(dp.getData());
        ObjectInputStream o = new ObjectInputStream(b);
        try{
            return (T) o.readObject();
        }catch (Exception e){
            return null;
        }
    }

}
