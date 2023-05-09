import java.util.*;

public class Factura{

	int id;
	Date fecha;
	Persona cliente;
	Persona vendedor;
	double total;
	ItemsFactura itemsFactura [];

	public Factura(int id, Persona cliente, Persona vendedor){
		this.id = id;
		this.cliente = cliente;
		this.vendedor = vendedor;

		fecha = new Date();
		total = 0;

		ItemsFactura temporal [] = new ItemsFactura [50];
		itemsFactura = temporal;
	}


}