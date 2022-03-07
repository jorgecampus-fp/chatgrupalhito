package cliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

import Connection.User;
import Connection.UsersDB;
import cifrado.Cifrado;

public class Cliente {

	public static void main(String[] args) {
		
		try {
			Scanner consola = new Scanner(System.in);
			Socket socket = new Socket("127.0.0.1", 5111);
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			HiloEntradaCliente hec = new HiloEntradaCliente(entrada);
			Cifrado ci = new Cifrado("1234");
			UsersDB udb = new UsersDB();
			
			boolean seguir = true;
			while(seguir) {
				System.out.println("1-Iniciar sesión\n2-Registrarme\n3-Salir");
				int op = consola.nextInt();
				
				if(op==1) {
					//Iniciar sesión
					
					consola.nextLine();
					System.out.println("Dime nombre: ");
					String nombre = consola.nextLine();
					System.out.println("Dime password: ");
					String pass = consola.nextLine();
					byte [] cifrado = ci.encriptar(pass);
					boolean poderEntrar = false;
					for(User u:udb.getAll()) {
						if(u.getNombre().equals(nombre)  && u.getContra().equals(new String(cifrado))) {
							poderEntrar = true;
							break;
						}
					}
					
					
					//UNA VEZ VALIDADO EL USUARIO
					if(poderEntrar) {
						hec.start();
						System.out.println("Bienvenido");
						
						while(true) {

							String msg = consola.nextLine();
							byte [] msgCifrado = ci.encriptar(msg);
							String msgDescifrado = ci.desencriptar(msgCifrado);
							salida.writeUTF(msgDescifrado);
						
						}
					}else {
						System.out.println("Usuario Incorrecto");
					}
				}else if(op==2) {
					//Registrarse 
					consola.nextLine();
					System.out.println("Dime nombre:");
					String nombre = consola.nextLine();
					System.out.println("Dime password: ");
					String pass = consola.nextLine();
					byte [] cifrado = ci.encriptar(pass);
					udb.register(nombre, new String(cifrado));
					System.out.println("Registrado");
					
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