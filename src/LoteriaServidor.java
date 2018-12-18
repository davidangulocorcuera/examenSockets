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
                   // 0 finaliza el servidor y el cliente
                   System.out.print("has finalizado el programa");
                   resultado_comprobacion = "0";
               }
               else if (numero_recibido == (numero_premiado + 1)){
                   System.out.print("te ha tocado el numero siguiente!");
                   // 2 es el numero siguiente
                   resultado_comprobacion = "2";
               }
               else if (numero_recibido == (numero_premiado - 1)){
                   System.out.print("te ha tocado el numero anterior!");
                   // 3 es el numero anterior
                   resultado_comprobacion = "3";
               }


                String mensajeEnviar = resultado_comprobacion;

                os.write(mensajeEnviar.getBytes());


                newSocket.close();

                serverSocket.close();

                // En caso de que toque cualquier premio finalizamos el programa
                if(resultado_comprobacion.equalsIgnoreCase("1") | resultado_comprobacion.equalsIgnoreCase("0")
                | resultado_comprobacion.equalsIgnoreCase("2") | resultado_comprobacion.equalsIgnoreCase("3")){
                    salir = true;
                }



            } catch
            (IOException e) {
                e.printStackTrace();
            }
        }while(!salir);
    }
}
