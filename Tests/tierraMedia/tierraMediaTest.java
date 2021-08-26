package tierraMedia;

import static org.junit.Assert.*;
import org.junit.Test;

import objetosDeEntrada.Atraccion;
import objetosDeEntrada.PromocionAbsoluta;
import objetosDeEntrada.PromocionAxB;
import objetosDeEntrada.PromocionPorcentual;
import objetosDeEntrada.Usuario;

public class tierraMediaTest {
	int indice;
	// Se declaran los arrays
	Atraccion[] atracciones;
	Usuario[] usuarios;
	PromocionPorcentual[] promocionesPorcentuales;
	PromocionAbsoluta[] promocionesAbsolutas;
	PromocionAxB[] promocionesAxB;
	// Se inicializan clases que van a servir para crear los arrays mas adelante
	Atraccion atraccion = new Atraccion();
	Usuario usuario = new Usuario();
	PromocionPorcentual promocionPorcentual = new PromocionPorcentual(atracciones);
	PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(atracciones);
	PromocionAxB promocionAxB = new PromocionAxB(atracciones);
	
	// nada que ver con el programa pero por alguna razon se ejecuta primero
	// el array de usuarios que el de promociones

	public Atraccion[] creoUnArrayDeAtracciones() {
		// El metodo leerArchivo invoca al LectorDeArchivos que devuelve
		// un ArrayList de arrays de String que representa cada linea del
		// archivo a leer
		atraccion.leerArchivo();
		// getTamanio() devuelve la cantidad de lineas del archivo
		Atraccion[] atracciones = new Atraccion[atraccion.getTamanio()];
		// indice es la linea del archivo que esta leyendo
		indice = atraccion.getIndice();
		do {
			// siguienteAtraccion() devuelve el siguiente objeto de
			// tipo Atraccion que pueda leer del archivo
			// el cual se guarda en el array de atracciones,
			// empieza de la linea 0
			atracciones[indice] = atraccion.siguienteAtraccion();
			// imprimo nomas para mostrar el toString() y el formato
			System.out.println(atracciones[indice].toString());
			// recupero el indice que avanzo +1 despues de haberse ejecutado
			// el metodo siguienteAtraccion()
			indice = atraccion.getIndice();
		} while (indice != 0);
		// el sysout para separar del siguiente toString()
		System.out.println();

		return atracciones;
	}
	// la creacion de los demas arrays son muy parecidos, intente hacerlo lo mas
	// generico posible

	public Usuario[] creoUnArrayDeUsuarios() {
		usuario.leerArchivo();
		Usuario[] usuarios = new Usuario[usuario.getTamanio()];
		indice = usuario.getIndice();
		do {
			usuarios[indice] = usuario.siguienteUsuario();
			System.out.println(usuarios[indice].toString());
			indice = usuario.getIndice();
		} while (indice != 0);
		System.out.println();

		return usuarios;
	}

	public PromocionPorcentual[] creoUnArrayDePromocionesPorcentuales() {
		promocionPorcentual.leerArchivo();
		PromocionPorcentual[] promocionesPorcentuales = new PromocionPorcentual[promocionPorcentual.getTamanio()];
		indice = promocionPorcentual.getIndice();
		do {
			promocionesPorcentuales[indice] = promocionPorcentual.siguientePromocion();
			System.out.println(promocionesPorcentuales[indice].toString());
			indice = promocionPorcentual.getIndice();
		} while (indice != 0);
		System.out.println();
		
		return promocionesPorcentuales;
	}

	public PromocionAbsoluta[] creoUnArrayDePromocionesAbsolutas() {
		promocionAbsoluta.leerArchivo();
		PromocionAbsoluta[] promocionesAbsolutas = new PromocionAbsoluta[promocionAbsoluta.getTamanio()];
		indice = promocionAbsoluta.getIndice();
		do {
			promocionesAbsolutas[indice] = promocionAbsoluta.siguientePromocion();
			System.out.println(promocionesAbsolutas[indice].toString());
			indice = promocionAbsoluta.getIndice();
		} while (indice != 0);
		System.out.println();
		
		return promocionesAbsolutas;
	}
	
	public PromocionAxB[] creoUnArrayDePromocionesAxB() {
		promocionAxB.leerArchivo();
		PromocionAxB[] promocionesAxB = new PromocionAxB[promocionAxB.getTamanio()];
		indice = promocionAxB.getIndice();
		do {
			promocionesAxB[indice] = promocionAxB.siguientePromocion();
			System.out.println(promocionesAxB[indice].toString());
			indice = promocionAxB.getIndice();
		} while (indice != 0);
		System.out.println();
		
		return promocionesAxB;
	}

	@Test
	public void buscoValoresEnElArrayDeAtracciones() {
		atracciones = this.creoUnArrayDeAtracciones();
		assertEquals("Mordor", atracciones[3].getNombre());
		assertEquals(30, atracciones[5].getCuposXdia());
		assertEquals("Paisaje", atracciones[1].getTipoDeAtraccion());
		assertEquals(10, atracciones[0].getCostoXvisita(), 0.1);
	}

	@Test
	public void buscoValoresEnElArrayDeUsuarios() {
		usuarios = this.creoUnArrayDeUsuarios();
		assertEquals("Sam", usuarios[2].getNombre());
		assertEquals(100, usuarios[1].getPresupuesto(), 0.1);
		assertEquals("Paisaje", usuarios[3].getTipoPreferido());
		assertEquals(8, usuarios[0].getTiempoDisponible(), 0.1);
	}

	@Test
	public void buscoValoresEnElArrayDePromocionesPorcentuales() {
		promocionesPorcentuales = this.creoUnArrayDePromocionesPorcentuales();
		assertEquals("Aventura", promocionesPorcentuales[0].getTipoDeAtraccion());
		assertEquals("Moria", promocionesPorcentuales[3].getAtraccion1());
		assertEquals("Abismo de Helm", promocionesPorcentuales[1].getAtraccion2());
		assertEquals(20, promocionesPorcentuales[0].getDescuento(), 0.1);
	}
	
	@Test
	public void buscoValoresEnElArrayDePromocionesAbsolutas() {
		promocionesAbsolutas = this.creoUnArrayDePromocionesAbsolutas();
		assertEquals("Degustacion", promocionesAbsolutas[0].getTipoDeAtraccion());
		assertEquals("Lothlorien", promocionesAbsolutas[0].getAtraccion1());
		assertEquals("La Comarca", promocionesAbsolutas[0].getAtraccion2());
		assertEquals(36, promocionesAbsolutas[0].getPrecio(), 0.1);
	}
	
	@Test
	public void buscoValoresEnElArrayDePromocionesAxB() {
		promocionesAxB = this.creoUnArrayDePromocionesAxB();
		assertEquals("Paisaje", promocionesAxB[0].getTipoDeAtraccion());
		assertEquals("Minas Tirith", promocionesAxB[0].getAtraccion1());
		assertEquals("Abismo de Helm", promocionesAxB[0].getAtraccion2());
		assertEquals("Erebor", promocionesAxB[0].getAtraccionGratis());
	}


}
