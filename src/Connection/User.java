package Connection;

public class User {
	private int id;
	private String nombre;
	private String contra;
	public User(int id, String nombre, String contra) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contra = contra;
	}
	
	public User(String nombre, String contra) {
		super();
		this.nombre = nombre;
		this.contra = contra;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", contra=" + contra + "]";
	}
	
	
	
	
}
