package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;


public class Server {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(5111);
			Socket socket = ss.accept();
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			
			
			Scanner scn = new Scanner(System.in);
			boolean seguir = true;
			
			HiloEntradaServidor hes = new HiloEntradaServidor(entrada);
			
			while(seguir) {
				System.out.println("1-Iniciar sesión\n2-Registrarme\n3-Salir");
				int op = scn.nextInt();
				
				
				//System.out.println(new String (cifrado));
				//System.out.println(new String(ci.desencriptar(cifrado)));
				
				
				if(op == 1) {
					//Iniciar sesion
					
					//ENCRIPTACIÓN DE LA PASSWORD
					
					
					//UNA VEZ SE VALIDA EL LOGIN
					
					
				}else if(op ==2) {
					//registrarse
				}else if(op ==3) {
					seguir = false;
				}else {
					System.out.println("No es correcto");
				}
				
				
			}
			System.out.println("Hasta luego!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
