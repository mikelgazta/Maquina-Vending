//Clase donde defino las monedas con los atributos de valor y cantidad con sus metodos get y set.
public class Moneda {
	private float valor;
	private int cantidad;
	
	public float getValor() {
		return valor;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Moneda(float valor, int cantidad) {
		this.valor = valor;
		this.cantidad = cantidad;
	}
	
	

}
