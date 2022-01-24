//Clase main donde tengo las operaciones y llamadas principales.
import javax.swing.*;
public class main {
	public static int validarPos(Golosina[]maquina, String texto) { //Metodo para comprobar que la posicion introducida por el usuario es valida.
		String posicion=JOptionPane.showInputDialog(null,"Introduce la posicion de la golosina que deseas "+texto+".\n\n"
				+ "11-KitKat. 12-Chicles de fresa. 13-Lacasitos. 14-Palotes.\n"
				+ "21-Kinder Bueno. 22-Bolsa Haribo. 23-Chetoos. 24-Twix.\n"
				+ "31-Maiz. 32-M&M'S. 33-Papa Delta. 34-Chicles de menta\n"
				+ "41-Gusanitos. 42-Crunch. 43-Milkybar. 44-Patatas fritas");
			for(int i=0;i<maquina.length;i++) {
				if(maquina[i].getPosicion().equals(posicion)) {
					return i;
				}
			}
		JOptionPane.showMessageDialog(null, "La posicion introducida no es valida.");
		return -1;
	}
	public static float sumarDinero(Moneda[] cajon, Moneda[] canal) { //Metodo para sumar todos los beneficios del cajon y el canal.
		float suma=0;
		for(int j=0;j<canal.length;j++) {
			suma=suma+((canal[j].getCantidad()-10)*canal[j].getValor())+(cajon[j].getCantidad()*cajon[j].getValor());
		}
		suma=suma+(cajon[4].getCantidad()*cajon[4].getValor());
		return suma;
	}
	public static void main(String[]args) { //Metodo main donde defino todos los objetos que utilizo a lo largo del programa.
		Golosina[] maquina=new Golosina[] { //Array de diferentes golosinas que llamo maquina.
				new Golosina("KitKat",1.1f,5,"11"),
				new Golosina("Chicles de fresa",0.8f,5,"12"),
				new Golosina("Lacasitos",1.5f,5,"13"),
				new Golosina("Palotes",0.9f,5,"14"),
				new Golosina("Kinder Bueno",1.8f,5,"21"),
				new Golosina("Bolsa Haribo",1f,5,"22"),
				new Golosina("Cheetos",1.2f,5,"23"),
				new Golosina("Twix", 1f, 5,"24"),
				new Golosina("Maiz",0.7f,5,"31"),
				new Golosina("M&M'S",1.3f,5,"32"),
				new Golosina("Papa Delta",1.2f,5,"33"),
				new Golosina("Chicles de menta",0.8f,5,"34"),
				new Golosina("Gusanitos",1.5f,5,"41"),
				new Golosina("Crunch",1.1f,5,"42"),
				new Golosina("Milkybar",1.1f,5,"43"),
				new Golosina("Patatas fritas",1.1f,5,"44"),
		};
		Moneda[] canal=new Moneda[] { //Array de las diferentes monedas que entran en los canales.
				new Moneda(1f,10),
				new Moneda(0.5f,10),
				new Moneda(0.2f,10),
				new Moneda(0.1f,10),
		};
		Moneda[] cajon=new Moneda[] { //Mismo array del canal, pero sumandole las monedas de dos euros al cajon.
				new Moneda(2f, 0),
				new Moneda(1f,0),
				new Moneda(0.5f,0),
				new Moneda(0.2f,0),
				new Moneda(0.2f,0),
		};
		Administrador[] admin=new Administrador[] { //Array de las diferentes contraseñas establecidas para acceder a la maquina como administrador.
				new Administrador("1DAW3"),
				new Administrador("MaquinaPlaiaundi"),
		};
		boolean salir=false;
		int opcion;        
		int i;
		String texto=""; //Texto que aparecera como comprar o mostrar depende de la operacion que queramos realizar.
		
		while(!salir) { //Menu principal que aparece al ejecutar el programa.
			opcion=Integer.parseInt(JOptionPane.showInputDialog(null,"Indica la operacion que desea realizar:\n"
					+ "1. Pedir golosina.\n"
					+ "2. Mostrar golosina.\n"
					+ "3. Rellenar golosina.\n"
					+ "4. Apagar maquina."));
			switch(opcion) {
			case 1: //Al elegir la primera opcion del menu nos permite comprar una golosina siempre que la cantidad de la golosina sea mayor que 0.
				texto=" comprar";
				int pos=validarPos(maquina, texto);
					if(pos != -1) {
						if(maquina[pos].getCantidad()>0) {
							Pagos.pagar(maquina[pos],canal,cajon);
						}
						else {
							JOptionPane.showMessageDialog(null, "No nos queda este producto, elija otro.");
						}
						break;
					}
				break;
			case 2: //Al elegir la segunda opcion del menu muestra todos los nombres de las golosinas existentes y sus cantidades siempre que introduzcamos la contraseña 1DAW3 primero.
				String mostrar="";
				String contraseña1=JOptionPane.showInputDialog(null, "Introduce la contraseña de administrador.");
				if(admin[0].getContraseña().equals(contraseña1)) {
					for(i=0;i<maquina.length;i++) {
					mostrar=mostrar+maquina[i].getNombreGolosina()+" quedan "+maquina[i].getCantidad()+" unidades.\n";
					}
					JOptionPane.showMessageDialog(null, mostrar);
				}
				else {
					JOptionPane.showInternalMessageDialog(null, "La contraseña introducida no es correcta.");
				}
				break;
			case 3: //Al elegir la tercera opcion del menu nos permitira rellenar la golosina que deseemos simpre que utilicemos la contraseña MaquinaPlaiaundi.
				String contraseña2=JOptionPane.showInputDialog(null, "Introduce la contraseña de administrador.");
				texto=" rellenar";
				if(admin[1].getContraseña().equals(contraseña2)) {
					pos=validarPos(maquina, texto);
						if(pos != -1) {
							maquina[pos].setCantidad(5); // Hago que se rellene la golosina elegida a su maxima cantidad, ya que no tendria sentido rellenarlo a medias.
						}
					}
				else {
					JOptionPane.showInternalMessageDialog(null, "La contraseña introducida no es correcta.");
				}
				break;
			case 4: //Al elegir la cuarta opcion del menu nos sacara del programa despues de sacar los beneficios de la maquina.
				JOptionPane.showMessageDialog(null, "La suma total del dia de hoy es de= "+ sumarDinero(cajon, canal)+"\u20ac\n Gracias por utilizar nuestra maquina, que tenga un buen dia.");
				salir=true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Debes introducir una opcion entre 1 y 4.");
			}
		}
	}
}




