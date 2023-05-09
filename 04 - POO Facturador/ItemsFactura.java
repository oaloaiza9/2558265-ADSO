
public class ItemsFactura{

	Producto producto;
	int cantidad;
	double subtotal;

	public ItemsFactura(Producto producto, int cantidad, double subtotal){
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public Producto getProducto(){
		return this.producto;
	}

	public int getCantidad(){
		return this.cantidad;
	}

	public double getSubtotal(){
		return this.subtotal;
	}

	public void setProducto(Producto producto){
		this.producto = producto;
	}

	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}

	public void setSubtotal(double subtotal){
		this.subtotal = subtotal;
	}


}