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
        Scanner sc = new Scanner(System.in);
        int numero_premiado = 12966;


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



                System.out.println("numero a comprobar: " + str_numero);




                System.out.println("Ecribe resultado");
                String mensajeEnviar = sc.nextLine();

                os.write(mensajeEnviar.getBytes());


                newSocket.close();

                serverSocket.close();


            } catch
            (IOException e) {
                e.printStackTrace();
            }
        }while(true);
    }
}
