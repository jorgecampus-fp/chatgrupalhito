package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import Connection.User;
import Connection.UsersDB;
import cifrado.Cifrado;

public class Server {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(5111);
			Socket socket = ss.accept();
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			
			
			Scanner scn = new Scanner(System.in);
			boolean seguir = true;
			UsersDB udb = new UsersDB();
			List<User> usuarios = udb.getAll();
			HiloEntradaServidor hes = new HiloEntradaServidor(entrada);
			
			while(seguir) {
				System.out.println("1-Iniciar sesión\n2-Registrarme\n3-Salir");
				int op = scn.nextInt();
				
				Cifrado ci = new Cifrado("1234");
				
				//System.out.println(new String (cifrado));
				//System.out.println(new String(ci.desencriptar(cifrado)));
				
				
				if(op == 1) {
					scn.nextLine();
					System.out.println("Dime nombre:");
					String nombre = scn.nextLine();
					System.out.println("Dime password: ");
					String pass = scn.nextLine();
					
					//ENCRIPTACIÓN DE LA PASSWORD
					byte [] cifrado = ci.encriptar(pass);
					
					boolean poderEntrar = false;
					
					for(User u:usuarios) {
						if(u.getNombre().equals(nombre)  && u.getContra().equals(new String(cifrado))) {
							poderEntrar = true;
							break;
						}
					}
					
					//UNA VEZ SE VALIDA EL LOGIN
					if(poderEntrar) {
						
						System.out.println("Conectado, esperando para chatear...");
						
						hes.start();
						
						System.out.println("Empezando");
						
						while(true) {
							String msg = scn.nextLine();
							if(msg.contains("/")) {
								String [] separate = msg.split(" ");
								if(separate[0].toLowerCase().equals("/send")) {
									salida.writeUTF("IMGSND");
									salida.writeUTF(separate[1]);
									File file = new File("D:\\Users\\Campus FP\\Desktop\\Hitos\\HitoGrupal\\src\\Server\\ArchivosServer\\"+separate[1]);
							        byte[] fileContent = Files.readAllBytes(file.toPath());
							        
							        
							        //ENCRIPTACIÓN FILE
							        
							        
							        //CONVERSIÓN FILE ENCRIPTADO STR --> FILE ENCRIPTADO BYTES 
							        byte[] fileEncriptado = ci.encriptarArray(fileContent);
							        
							        //ENVÍO DE FILE ENCRIPTADO
							        salida.writeUTF(""+fileEncriptado.length);
							        salida.write(fileEncriptado);
							        
							        
								}else if(separate[0].toLowerCase().equals("/exit")){
									seguir = false;
									break;
									
									
								}else if(separate[0].toLowerCase().equals("/changepwd")){
									byte [] newPass = ci.encriptar(separate[1]);
									udb.update(nombre, new String(newPass));
									
									
								}else {
									byte [] msgCifrado = ci.encriptar(msg);
									String msgDescifrado = ci.desencriptar(msgCifrado);
									salida.writeUTF(msgDescifrado);
								}
								
							}else {
								byte [] msgCifrado = ci.encriptar(msg);
								String msgDescifrado = ci.desencriptar(msgCifrado);
								salida.writeUTF(msgDescifrado);
							}
						}
						
					}else {
						System.out.println("Usuario incorrecto");
					}
					
				}else if(op ==2) {
					scn.nextLine();
					System.out.println("Dime nombre:");
					String nombre = scn.nextLine();
					System.out.println("Dime password: ");
					String pass = scn.nextLine();
					byte [] passCifrada = ci.encriptar(pass);
					udb.register(nombre, new String(passCifrada));
					User u = new User(nombre, new String(passCifrada));
					usuarios.add(u);
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
