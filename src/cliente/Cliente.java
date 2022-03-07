package cliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		
		try {
			Scanner consola = new Scanner(System.in);
			Socket socket = new Socket("127.0.0.1", 5111);
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			HiloEntradaCliente hec = new HiloEntradaCliente(entrada);
			
			boolean seguir = true;
			while(seguir) {
				System.out.println("1-Iniciar sesión\n2-Registrarme\n3-Salir");
				int op = consola.nextInt();
				
				if(op==1) {
					//Iniciar sesión
					
					
					
					//UNA VEZ VALIDADO EL USUARIO
					
				}else if(op==2) {
					//Registrarse 
					
					
				}else if(op==3) {
					seguir= false;
				}else {
					System.out.println("No te he entendido bien");
				}
				
			}
			System.out.println("Hasta luego!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}