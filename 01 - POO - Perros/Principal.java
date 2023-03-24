
public class Principal{
	public static void main(String[] args) {
		
		// Instanciar
		Perro p_01 = new Perro(1, "Matias", "Pincher", 2, 20, 2000, "Negro", "Vivo", 20);
		p_01.imprimirDatosPerro();
		p_01.comer(800);
		p_01.dormir(300);
		p_01.imprimirDatosPerro();
		
	}
}