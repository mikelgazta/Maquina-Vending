//Clase donde tengo las diferentes operaciones realizadas con el dinero.
import javax.swing.JOptionPane;
public class Pagos {
	public static float formato(float f){ //Metodo para redondear los resultados float.
        return Float.parseFloat(String.format("%.1f", f).replace(',', '.'));
      }
	public static void guardarMonedas(Moneda[] pago, Moneda[] canal, Moneda[] cajon) { //Metodo para guardar las monedas en el canal o en el cajon si no esta lleno.
		cajon[0].setCantidad(cajon[0].getCantidad()+pago[0].getCantidad());
		for(int i=1;i<5;i++) {
			canal[i-1].setCantidad(canal[i-1].getCantidad()+pago[i].getCantidad());
			if(canal[i-1].getCantidad()>10) {
				cajon[i].setCantidad(cajon[i].getCantidad()+(canal[i-1].getCantidad()-10));
				canal[i-1].setCantidad(10);
			}
		}
	}
	public static void vuelta(float cambio, Moneda[]canal) { //Metodo para calcular el cambio dependiendo del dinero que introduce el usuario.
		Moneda[] vuelta=new Moneda[] { //Diferentes monedas utilizadas para sacar el cambio. Las de dos euros se introducen directamente al cajon.
				new Moneda(1f,0),
				new Moneda(0.5f,0),
				new Moneda(0.2f,0),
				new Moneda(0.2f,0),
		};
		while(cambio>0) { //Bucle que elige que moneda devolver, siempre devolvera la mas grande posible hasta que llegue a 0. Elige solo una posibilidad por bucle.
			if(cambio>=1) {
				cambio=cambio-1;
				vuelta[0].setCantidad(vuelta[0].getCantidad()+1);
				canal[0].setCantidad(canal[0].getCantidad()-1);
			}
			else if(cambio>=0.5f) {
				cambio=cambio-0.5f;
				vuelta[1].setCantidad(vuelta[1].getCantidad()+1);
				canal[1].setCantidad(canal[1].getCantidad()-1);
			}
			else if(cambio>=0.2f) {
				cambio=cambio-0.2f;
				vuelta[2].setCantidad(vuelta[2].getCantidad()+1);
				canal[2].setCantidad(canal[2].getCantidad()-1);
			}
			else if(cambio>=0.1f) {
				cambio=cambio-0.1f;
				vuelta[3].setCantidad(vuelta[3].getCantidad()+1);
				canal[3].setCantidad(canal[3].getCantidad()-1);
			}
			cambio=formato(cambio); //Aplico el metodo para redondear el cambio.
		}
		JOptionPane.showMessageDialog(null, "Tus vueltas son: \n"  //Imprimo las diferentes monedas que se devolveran y la cantidad de cada una.
				+vuelta[0].getCantidad()+" monedas de 1\u20ac \n"
				+vuelta[1].getCantidad()+" monedas de 0.5\u20ac \n"
				+vuelta[2].getCantidad()+" monedas de 0.2\u20ac \n"
				+vuelta[3].getCantidad()+" monedas de 0.1\u20ac \n");
	}
	public static void pagar(Golosina producto, Moneda[] canal, Moneda[]cajon) { //Metodo para pagar las golosinas.
		int i=0;
		float restante=producto.getPrecioGolosina(); //Dinero que falta por introducir para sacar la golosina.
		Moneda[] pago=new Moneda[] { //Array de las monedas que se pueden utilizar para pagar las golosinas.
				new Moneda(2f, 0),
				new Moneda(1f,0),
				new Moneda(0.5f,0),
				new Moneda(0.2f,0),
				new Moneda(0.2f,0),
		};
		JOptionPane.showMessageDialog(null,producto.getPrecioGolosina()+"\u20ac, por favor."); 

		while((i<5) && (restante>0)) { //Bucle que me permite meter maximo cinco monedas diferentes para pagar, en caso de no introducir el importe necesario con menos de cinco monedas se cancelara la transaccion.
			String[] opciones = {"2\u20ac","1\u20ac", "0.5\u20ac","0.2\u20ac","0.1\u20ac"}; //Menu con las diferentes opciones de pago.
			int eleccion = JOptionPane.showOptionDialog(null, "Elige las monedas que vas a utilizar.\n Te faltan "+ restante+"\u20ac", "",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opciones,opciones[0]);
			switch(eleccion) {
			case 0:
				restante=restante-2f;
				pago[0].setCantidad(pago[0].getCantidad()+1);
				break;
			case 1:
				restante=restante-1f;
				pago[1].setCantidad(pago[1].getCantidad()+1);
				break;
			case 2:
				restante=restante-0.5f;
				pago[2].setCantidad(pago[2].getCantidad()+1);
				break;
			case 3:
				restante=restante-0.2f;
				pago[3].setCantidad(pago[3].getCantidad()+1);
				break;
			case 4:
				restante= restante-0.1f;
				pago[4].setCantidad(pago[4].getCantidad()+1);
				break;
			}
			i++;
			restante=formato(restante);
		}
		if(i==5) {
			JOptionPane.showMessageDialog(null,"Maximo 5 monedas por persona.");
		}else { //Esta parte solo se ejecutara si se ha cerrado el pago sin problemas.
			float cambio=-restante; //En  caso de que lo que hemos pagado sea superior a su precio, este valor se convertira en el cambio.
			JOptionPane.showMessageDialog(null, "Tu cambio es "+cambio+"\u20ac");
			guardarMonedas(pago, canal, cajon); //LLamada al metodo guardarMonedas.
			vuelta(cambio, canal); //Llamada al metodo vuelta.
			producto.setCantidad(producto.getCantidad()-1);	//Quito una golosina de la cantidad total.
		}
	}

}
