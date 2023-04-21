
public class Persona{

	// Atributos
	private int documento;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String email;

	// Metodos
	public Persona(int documento, String nombres, String apellidos, String direccion, String telefono, String email){
		this.documento = documento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}

	public String toString(){
		return this.documento+" || "+this.nombres+" "+this.apellidos;
	}

	public int getDocumento(){
		return this.documento;
	}

	public String getNombres(){
		return this.nombres;
	}

	public String getApellidos(){
		return this.apellidos;
	}

	public String getDireccion(){
		return this.direccion;
	}

	public String getTelefono(){
		return this.telefono;
	}

	public String getEmail(){
		return this.email;
	}

	public void setDocumento(int documento){
		this.documento = documento;
	}

	public void setNombres(String nombres){
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}

	public void setDireccion(String direccion){
		this.direccion = direccion;
	}

	public void setTelefono(String telefono){
		this.telefono = telefono;
	}

	public void setEmail(String email){
		this.email = email;
	}

}