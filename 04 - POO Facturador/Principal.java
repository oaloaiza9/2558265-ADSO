import java.util.*;

public class Principal{
	public static void main(String[] args) {
	
		int opcion = 0;
		Scanner entrada_numero = new Scanner(System.in);
		Scanner entrada_texto = new Scanner(System.in);

		Factura listaFacturas [] = new Factura [100];
		Persona listaClientes [] = new Persona [100];
		Persona listaVendedores [] = new Persona [100];
		Producto listaProductos [] = new Producto [100];
		int indiceFacturas = 0;
		int indiceClientes = 0;
		int indiceVendedores = 0;
		int indiceProductos = 0;

		do{
			System.out.println("++------------------------------------++");
			System.out.println("++----   SISTEMA DE FACTURACION   ----++");
			System.out.println("++------------------------------------++");
			System.out.println("||                                    ||");
			System.out.println("||                      Clientes: "+formatoIndice(indiceClientes)+"  ||");
			System.out.println("||                    Vendedores: "+formatoIndice(indiceVendedores)+"  ||");
			System.out.println("||                     Productos: "+formatoIndice(indiceVendedores)+"  ||");
			System.out.println("||                      Facturas: "+formatoIndice(indiceProductos)+"  ||");
			System.out.println("||                                    ||");
			System.out.println("||    1. Creacion de cliente          ||");
			System.out.println("||    2. Creacion de Vendedor         ||");
			System.out.println("||    3. Creacion de Factura          ||");
			System.out.println("||    4. Ver lista de Clientes        ||");
			System.out.println("||    5. Ver lista de Vendedores      ||");
			System.out.println("||    6. Ver lista de Facturas        ||");
			System.out.println("||    7. Ver detalle de Factura       ||");
			System.out.println("||    8. Salir                        ||");
			System.out.println("||                                    ||");
			System.out.println("++------------------------------------++");
			System.out.println("++------------------------------------++");
			System.out.print("++--> Ingrese una opcion: ");
			opcion = entrada_numero.nextInt();

			if(opcion==1){
				System.out.println("++------------------------------------++");
				System.out.println("++----       CREANDO CLIENTE      ----++");
				System.out.println("++------------------------------------++");
				System.out.print("++----> Documento: ");
				int documento = entrada_numero.nextInt();

				boolean valido = true;
				for (int i=0; i<listaClientes.length; i++) {
					if (listaClientes[i]!=null && listaClientes[i].getDocumento()==documento ) {
						valido = false;
					}
				}

				if (valido) {
					System.out.print("++----> Nombres: ");
					String nombres = entrada_texto.nextLine();

					System.out.print("++----> Apellidos: ");
					String apellidos = entrada_texto.nextLine();

					listaClientes[indiceClientes]  = new Persona(documento, nombres, apellidos);
					indiceClientes++;
				}else{
					System.out.println("++-------------------------------------------------++");
					System.out.println("++----     EL DOCUMENTO INGRESADO YA EXISTE    ----++");
					System.out.println("++-------------------------------------------------++\n\n");
				}
			}else if(opcion==2){
				System.out.println("++------------------------------------++");
				System.out.println("++----     ALGORITMO PENDIENTE    ----++");
				System.out.println("++------------------------------------++\n\n");
			}else if(opcion==3){
				System.out.println("++------------------------------------++");
				System.out.println("++----     ALGORITMO PENDIENTE    ----++");
				System.out.println("++------------------------------------++\n\n");
			}else if(opcion==4){
				System.out.println("++------------------------------------++");
				System.out.println("++----      LISTA DE CLIENTES     ----++");
				System.out.println("++------------------------------------++");
				for (int i=0; i<listaClientes.length; i++) {
					if (listaClientes[i]!=null) {
						listaClientes[i].imprimirDetalle();
					}else{
						break;
					}
				}
				System.out.println("\n\n");
			}else if(opcion==5){
				System.out.println("++------------------------------------++");
				System.out.println("++----     ALGORITMO PENDIENTE    ----++");
				System.out.println("++------------------------------------++\n\n");
			}else if(opcion==6){
				System.out.println("++------------------------------------++");
				System.out.println("++----     ALGORITMO PENDIENTE    ----++");
				System.out.println("++------------------------------------++\n\n");
			}else if(opcion==7){
				System.out.println("++------------------------------------++");
				System.out.println("++----     ALGORITMO PENDIENTE    ----++");
				System.out.println("++------------------------------------++\n\n");
			}else if(opcion==8){
				System.out.println("++------------------------------------++");
				System.out.println("++----- SALIENDO DE LA APLICACION ----++");
				System.out.println("++------------------------------------++\n\n");
			}else{
				System.out.println("++------------------------------------++");
				System.out.println("++----      OPCION INCORRECTA     ----++");
				System.out.println("++------------------------------------++");
			}

		}while(opcion!=8);
	}


	public static String formatoIndice(int indice){
		return (indice<10)? "0"+indice : ""+indice;
	}

	
}