package es.ies.lavereda.programaprocesos.udp;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;

import java.net.SocketException;

public class Cliente {

    private final static int MAX_BYTES = 1400;

    private final static String COD_TEXTO = "UTF-8";

    public void enviaMSJ(String arg) {

        String nomHost = "127.0.0.1";

        int numPuerto = 8000;

        try (DatagramSocket clientSocket = new DatagramSocket();

             InputStreamReader isrStdIn = new InputStreamReader(System.in, COD_TEXTO);) {

            System.out.println("Introducir líneas. Línea vacía para terminar.");

            System.out.print("Línea>");

            DatagramBuilder<Mensaje> dbm = new DatagramBuilder<Mensaje>("Server", 2);

            InetAddress IPServidor = InetAddress.getByName(nomHost);

            DatagramPacket paqueteEnviado = dbm.cifrar(new Mensaje(arg));
            paqueteEnviado.setAddress(IPServidor);
            paqueteEnviado.setPort(numPuerto);

            clientSocket.connect(IPServidor, numPuerto);

            clientSocket.send(paqueteEnviado);


        } catch (SocketException ex) {

            System.out.println("Excepción de sockets");

            ex.printStackTrace();

        } catch (IOException ex) {

            System.out.println("Excepción de E/S");

            ex.printStackTrace();

        }

    }
}
