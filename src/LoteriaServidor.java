import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class LoteriaServidor {
    public static void main(String[] args) {

        int numero_premiado = 12966;
        int numero_recibido;
        String resultado_comprobacion = "defecto";
        boolean salir = false;
        do{

            try {
                System.out.println("Esperando numero");
                ServerSocket serverSocket = new ServerSocket();


                InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
                serverSocket.bind(addr);


                Socket newSocket = serverSocket.accept();



                InputStream is = newSocket.getInputStream();
                OutputStream os = newSocket.getOutputStream();

                byte[] mensaje = new byte[5];
                is.read(mensaje);
                String str_numero = new String(mensaje);
                str_numero = str_numero.trim();
                numero_recibido = Integer.parseInt(str_numero);




                System.out.println("numero a comprobar: " + numero_recibido);



               if(numero_recibido == numero_premiado){
                        System.out.println("Este numero estaba premiado con el gordo");
                        // 1 es premio gordo
                        resultado_comprobacion = "1";
               }
               else if(numero_recibido == 0){
                   System.out.print("has finalizado el programa");
                   resultado_comprobacion = "0";
               }


                String mensajeEnviar = resultado_comprobacion;

                os.write(mensajeEnviar.getBytes());


                newSocket.close();

                serverSocket.close();
                if(resultado_comprobacion.equalsIgnoreCase("1") | resultado_comprobacion.equalsIgnoreCase("0")){
                    salir = true;
                }



            } catch
            (IOException e) {
                e.printStackTrace();
            }
        }while(!salir);
    }
}
