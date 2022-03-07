package cliente;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import cifrado.Cifrado;

public class HiloEntradaCliente extends Thread{
	private DataInputStream entrada;
	
	

	
	
	public HiloEntradaCliente(DataInputStream entrada) {
		super();
		this.entrada = entrada;
	}




	@Override
	public void run() {
		String msg;
		Cifrado ci = new Cifrado("1234");
		while(true) {
			
			 try {
					msg = entrada.readUTF();
					
					System.out.println("Server: "+msg);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
	}
}
