import java.util.Scanner;

public class Principal{
	
	public static void main(String[] args) {
		
		Scanner entrada_numero = new Scanner(System.in);
		Scanner entrada_texto = new Scanner(System.in);

		Persona arreglo [] = new Persona[99];
		int posicion = 0;
		int opcion = 0;
		
		do{
			System.out.println("\n");
			System.out.println("||----------------------------------------||");
			System.out.println("||--------------- PERSONAS ---------------||");
			System.out.println("||----------------------------------------||");
			System.out.println("||               Personas Registradas: "+((posicion<10)? "0":"")+posicion+" ||");
			System.out.println("||                                        ||");
			System.out.println("||  1. Registrar Persona.                 ||");
			System.out.println("||  2. Ver lista de Personas.             ||");
			System.out.println("||  3. Ordenar lista Personas.            ||");
			System.out.println("||  4. Salir.                             ||");
			System.out.println("||----------------------------------------||");
			System.out.print("||=> Ingrese una opcion: ");
			opcion = entrada_numero.nextInt();

			if( opcion==1 ){
				// Pedir los datos para la creacion de la persona
				System.out.print(" => Ingrese Documento: ");
				int documento = entrada_numero.nextInt();

				System.out.print(" => Ingrese Nombres: ");
				String nombres = entrada_texto.nextLine();

				System.out.print(" => Ingrese Apellidos: ");
				String apellidos = entrada_texto.nextLine();

				System.out.print(" => Ingrese Direccion: ");
				String direccion = entrada_texto.nextLine();

				System.out.print(" => Ingrese Telefono: ");
				String telefono = entrada_texto.nextLine();

				System.out.print(" => Ingrese Email: ");
				String email = entrada_texto.nextLine();

				boolean valido = true;
				for (int i=0; i<posicion; i++) {
					if(arreglo[i].getDocumento() == documento){
						valido = false;
						break;
					}
				}

				if(valido){
					// Crear un objeto Persona
					Persona temporal = new Persona(documento, nombres, apellidos, direccion, telefono, email);
					// Guardar el objeto en el Arreglo
					arreglo[posicion] = temporal;
					// aumentar el contador
					posicion++;
					System.out.println(" == REGISTRADO CON EXITO ==");
				}else{
					System.out.println(" == DOCUMENTO REPETIDO, DEBE INTENTAR DE NUEVO EL PROCESO ==");
				}

			}else if( opcion==2 ){

				System.out.println(" => LISTA DE PERSONAS: ");
				for (int i=0; i<arreglo.length; i++) {
					if(arreglo[i]!=null){
						System.out.println( "     => "+arreglo[i].toString() );
					}else{
						break;
					}
				}

			}else if( opcion==3 ){

				for (int i=0; i<posicion; i++) {
					for (int j=0; j<posicion-1; j++) {
						if(arreglo[j].getDocumento() > arreglo[j+1].getDocumento() ){
							Persona temp = arreglo[j];
							arreglo[j] = arreglo[j+1];
							arreglo[j+1] = temp;
						}
					}
				}
				System.out.println(" == ORDENADO CON EXITO ==");

			}else if( opcion==4 ){
				System.out.println(" == Saliendo ==");
			}else{
				System.out.println(" == Opcion Invalida ==");
			}

		}while( opcion!=4 );

	}

}