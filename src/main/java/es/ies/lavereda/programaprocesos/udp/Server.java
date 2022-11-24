package es.ies.lavereda.programaprocesos.udp;
import es.ies.lavereda.programaprocesos.udp.DatagramBuilder;
import es.ies.lavereda.programaprocesos.udp.Mensaje;

import java.io.IOException;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;

import java.net.SocketException;

public class  Server {
    private final static int MAX_BYTES = 1400;

    private final static String COD_TEXTO = "UTF-8";

    public static void main(String[] args) {
        DatagramBuilder<Mensaje> mensajeDatagramBuilder = new DatagramBuilder<>("Data", 1);
        String[] str = {"systemctl", "stop", "mariadb.service", "/etc/init.d/apache2", "stop"};
        ProcessBuilder pbA = new ProcessBuilder();
        ProcessBuilder pbM = new ProcessBuilder();
        ProcessBuilder borrar = new ProcessBuilder(str);

        Process processA = null;
        Process processB = null;
        int numPuerto = 8000;

        try (DatagramSocket serverSocket = new DatagramSocket(numPuerto)) {

            System.out.printf("Creado socket de datagramas para puerto %s.\n", numPuerto);

            while (true) {

                System.out.println("Esperando datagramas.");

                byte[] datosRecibidos = new byte[MAX_BYTES];

                DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);

                serverSocket.receive(paqueteRecibido);

                Mensaje m = mensajeDatagramBuilder.deszifrar(paqueteRecibido);

                String[] options = {};

                switch (m.getMessage()){
                    case 1 :
                        options = new String[]{"systemctl", "start", "mariadb.service"};
                        pbM = new ProcessBuilder(options);
                        pbM.inheritIO();
                        try{
                            processB = pbM.start();
                            long cod = processB.pid();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        options = new String[]{"/etc/init.d/apache2", "start"};
                        pbA = new ProcessBuilder(options);
                        pbA.inheritIO();
                        try{
                            processA = pbA.start();
                            long cod = processA.pid();
                        }catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try{
                            Process dead = borrar.start();
                            int a = dead.waitFor();
                            System.out.println(a);
                            dead.destroy();
                            processB.destroy();
                            processA.destroy();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        String[] stopping = {"/etc/init.d/apache2", "stop"};
                        try{
                            ProcessBuilder temp = new ProcessBuilder(stopping);
                            Process pTemp = temp.start();

                            pTemp.waitFor();

                            pTemp.destroy();

                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        String[] stopping1 = {"systemctl", "stop", "mariadb.service"};
                        try{
                            ProcessBuilder temp = new ProcessBuilder(stopping1);
                            Process pTemp = temp.start();

                            pTemp.waitFor();

                            pTemp.destroy();

                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                }


            }

        } catch (SocketException ex) {

            System.out.println("Excepción de sockets");

            ex.printStackTrace();

        } catch (IOException ex) {

            System.out.println("Excepción de E/S");

            ex.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
