//Clase golosina donde cada golosina tiene su nombre, precio, cantidad y posicion, cada atributo tiene los metodos get y set necesarios.
public class Golosina {
	private String nombreGolosina;
	private float precioGolosina;
	private int cantidad;
	private String posicion;
	
	public String getNombreGolosina() {
		return nombreGolosina;
	}
	public float getPrecioGolosina() {
		return precioGolosina;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getPosicion() {
		return posicion;
	}
	
	public Golosina(String nombreGolosina, float precioGolosina, int cantidad, String posicion) {
		this.nombreGolosina = nombreGolosina;
		this.precioGolosina = precioGolosina;
		this.cantidad = cantidad;
		this.posicion = posicion;
	}
	
	
	
}
