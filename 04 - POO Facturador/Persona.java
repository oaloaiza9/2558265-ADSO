
public class Persona{

	int documento;
	String nombres;
	String apellidos;

	public Persona(int documento, String nombres, String apellidos){
		this.documento = documento;
		this.nombres = nombres;
		this.apellidos = apellidos;
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

	public void setDocumento(int documento){
		this.documento = documento;
	}

	public void setNombres(String nombres){
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}

	public void imprimirDetalle(){
		System.out.println("    => "+this.documento+" - "+this.nombres+" "+this.apellidos);
	}

}