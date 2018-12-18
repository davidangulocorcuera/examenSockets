import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class LoteriaCliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);



        do {
            try {


                Socket clientSocket = new Socket();


                InetSocketAddress addr = new InetSocketAddress("localhost", 5555);

                clientSocket.connect(addr);
                InputStream is = clientSocket.getInputStream();
                OutputStream os = clientSocket.getOutputStream();
                System.out.println("Ecribe numero");

                String mensaje = sc.nextLine();

                os.write(mensaje.getBytes());
                System.out.println("numero enviado, esperando respuesta");

                byte[] mensajeRecibir = new byte[5];
                is.read(mensajeRecibir);
                String respuesta = new String(mensajeRecibir);

                System.out.println("resultado: " + respuesta);


                clientSocket.close();


            } catch
            (IOException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
