
public class Principal{
	public static void main(String[] args) {
		
		// Instanciar
		/*Perro p_01 = new Perro(1, "Matias", "Pincher", 2, 20, 2000, "Negro", "Vivo", 20);
		p_01.imprimirDatosPerro();
		p_01.comer(800);
		p_01.dormir(300);
		p_01.imprimirDatosPerro();
		p_01.ladrar("Hola Mundo!");*/

		Perro arreglo [] = new Perro [10];

		arreglo[0] = new Perro(1, "Sam", "Labrador", 1, 30, 3000, "Blanco", "Vivo", 100);
		arreglo[0].nombre = "Fabio";

		arreglo[0].imprimirDatosPerro();
		

	}
}