
public class Producto {

	String nombre;
	double precio;
	
	public Producto(String nombre, double precio){
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public double getPrecio(){
		return this.precio;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setPrecio(double precio){
		this.precio = precio;
	}
	
}