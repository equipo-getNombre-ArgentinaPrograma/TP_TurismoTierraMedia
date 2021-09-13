package generadores;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import comparadores.CompararSugerencias;

import java.util.PriorityQueue;

import objetosDeEntrada.*;

public class GenerarSugerencias {
	Usuario usuario;
	private ArrayList<Promocion> promos;
	private ArrayList<Atraccion> atracciones;
	private Map<Integer, PriorityQueue<Adquirible>> sugerenciasXprioridad;

	private Adquirible sugerencia;
	private Integer key;

	// Genera las listas con los datos leidos del archivo
	public GenerarSugerencias() {
		this.promos = GeneradorListas.dePromos();
		this.atracciones = GeneradorListas.deAtracciones();
	}

	// Devuelve el objeto usuario al cual esta ligado el sistema
	public Usuario getUsuario() {
		return usuario;
	}

	// Guarda las promociones aptas para el usuario en una lista
	public void para(Usuario usuario) {
		this.usuario = usuario;
		inicializarListas();
	}

	// Inicializa las listas y las ordena por tipo preferido en aptas y no aptas
	private void inicializarListas() {
		sugerenciasXprioridad = new TreeMap<Integer, PriorityQueue<Adquirible>>();
		agregarAmap(promos);
		agregarAmap(atracciones);
	}

	// Se agregan al TreeMap dos tipos de queues, las preferidas y las no preferidas
	private void agregarAmap(ArrayList<? extends Adquirible> listaAdquiribles) {
		for (Adquirible sugerencia : listaAdquiribles) {
			key = esPreferida(sugerencia);
			if (sugerenciasXprioridad.containsKey(key))
				sugerenciasXprioridad.get(key).offer(sugerencia);
			else {
				PriorityQueue<Adquirible> cola = new PriorityQueue<Adquirible>(new CompararSugerencias());
				cola.offer(sugerencia);
				sugerenciasXprioridad.put(key, cola);
			}
		}
	}

	// Devuelve 0 si la sugerencia es del tipo preferido y 1 si no lo es,
	// ya que es un TreeMap, las sugerencias preferidas se mostraran primero
	private Integer esPreferida(Adquirible sugerencia) {
		Integer salida;
		if (getUsuario().getTipoPreferido().equals(sugerencia.getTipoDeAtraccion()))
			salida = 0;
		else
			salida = 1;
		return salida;
	}

	// Levanto los items de la queue, si el usuario puede comprarla, devuelvo la
	// sugerencia
	public Adquirible sugerir() {
		for (Map.Entry<Integer, PriorityQueue<Adquirible>> entry : this.sugerenciasXprioridad.entrySet())
			while (entry.getValue().size() > 0) {
				sugerencia = entry.getValue().poll();
				if (getUsuario().puedeComprar(sugerencia))
					return sugerencia;
			}
		return null;
	}

	// Se acepta la promo sugerida y se agrega a la lista ligada al usuario
	public void aceptarPromocion() {
		if (getUsuario().adquirir(sugerencia)) {
			System.out.println("Adquiriste la promocion con exito.");
			sugerencia.usarCupos();
		}
	}

	// Se rechaza la promo sugerida
	public void rechazarPromocion() {
		System.out.println("Rechazaste la promocion, no volveremos a sugerirla.");

	}
}
