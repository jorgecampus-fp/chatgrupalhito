package Connection;

public class Prueba {

	public static void main(String[] args) {
		
		UsersDB u = new UsersDB();
		u.getAll().stream().forEach(us -> System.out.println(us.toString()));
		
		u.register("Paco", "1234");

	}

}
