package Server;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HiloEntradaServidor extends Thread{
	private DataInputStream entrada;
	public boolean seguir = true;
	

	
	
	public HiloEntradaServidor(DataInputStream entrada) {
		super();
		this.entrada = entrada;
	}
	
	public void pararHilo() {
		this.seguir = false;
	}




	@Override
	public void run() {
			String msg;
			while(this.seguir) {
				 try {
					msg = entrada.readUTF();
						
					System.out.println("Cliente: "+msg);
						
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			 }
		
	}
}