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
		while(true) {
			Cifrado ci = new Cifrado("1234");
			 try {
					msg = entrada.readUTF();
					if(msg.equals("IMGSND")) {
						System.out.println("Esperando...");
						String nombreArchivo = entrada.readUTF();
						String tamano = entrada.readUTF();
						byte[] fileArray = entrada.readNBytes(Integer.parseInt(tamano));
						
						byte[] fileDesencritado = ci.desencriptarArray(fileArray);
						
						
						OutputStream  fileOuputStream = new FileOutputStream("D:\\Users\\Campus FP\\Desktop\\Hitos\\HitoGrupal\\src\\cliente\\"+nombreArchivo);
						fileOuputStream.write(fileDesencritado);
						System.out.println("Ha llegado un archivo");
						fileOuputStream.close();
					}else {
						System.out.println("Server"+msg);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
	}
}
