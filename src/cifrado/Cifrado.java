package cifrado;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {
	private Cipher cipher;
	private SecretKey key;
	
	public Cifrado(String clave) {
		try {
			this.cipher = Cipher.getInstance("AES");
			
			byte[] claveEncriptada = clave.getBytes();
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			claveEncriptada = md.digest(claveEncriptada);
			claveEncriptada = Arrays.copyOf(claveEncriptada, 16);
			this.key = new SecretKeySpec(claveEncriptada, "AES");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public byte [] encriptar(String msg) {
		byte [] mensa = null;
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
			byte [] mensajeCifrado = msg.getBytes();
			mensajeCifrado = this.cipher.doFinal(mensajeCifrado);
			mensa = mensajeCifrado;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensa;
		
	}
	
	
	public String desencriptar(byte [] cifrado) {
		String mensa = null;
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, this.key);
			byte [] descifrado = cipher.doFinal(cifrado);
			mensa = new String(descifrado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensa;
	}
	
	public byte [] encriptarArray(byte [] archivo) {
		byte [] mensa = null;
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
			byte [] mensajeCifrado = archivo;
			mensajeCifrado = this.cipher.doFinal(mensajeCifrado);
			mensa = mensajeCifrado;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensa;
		
	}
	
	
	public byte [] desencriptarArray(byte [] cifrado) {
		byte [] mensa = null;
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, this.key);
			byte [] descifrado = cipher.doFinal(cifrado);
			mensa = descifrado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensa;
	}
	
	
}