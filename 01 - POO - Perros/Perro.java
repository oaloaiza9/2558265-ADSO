
public class Perro{

	// Atributos
	int id;
	String nombre;
	String raza;
	int edad;
	float tamanio;
	float peso;
	float energia;
	String color;
	String estado;

	// Metodos
	public Perro(int id, String nombre, String raza, int edad, float tamanio, float peso, String color, String estado, float energia){
		this.id = id;
		this.nombre = nombre;
		this.raza = raza;
		this.edad = edad;
		this.tamanio = tamanio;
		this.peso = peso;
		this.color = color;
		this.estado = estado;
		this.energia = energia;
	}

	public void ladrar(){
		System.out.println(" Gau! ");
	}

	public void comer(float gramos){
		this.peso += gramos;
		this.energia += (gramos*0.01);
	}

	public void dormir(float minutos){
		this.energia += (minutos*0.005);
	}

	public void correr(float minutos){
		this.energia -= (minutos*0.01);
	}

	public void popis(){
		this.peso -= (this.peso*0.01);
	}

	public void imprimirDatosPerro(){
		System.out.println("====================");
		System.out.println("== id: "+this.id);
		System.out.println("== nombre: "+this.nombre);
		System.out.println("== raza: "+this.raza);
		System.out.println("== edad: "+this.edad);
		System.out.println("== tamanio: "+this.tamanio);
		System.out.println("== peso: "+this.peso);
		System.out.println("== energia: "+this.energia);
		System.out.println("== color: "+this.color);
		System.out.println("== estado: "+this.estado);
		System.out.println("====================");
	}

}