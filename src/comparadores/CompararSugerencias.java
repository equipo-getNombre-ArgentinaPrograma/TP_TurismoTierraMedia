package comparadores;

import java.util.Comparator;

import objetosDeEntrada.Adquirible;

public class CompararSugerencias implements Comparator<Adquirible> {
	//Ordena de mayor de menor por tipo, precio y tiempo respectivamente
	@Override
	public int compare(Adquirible s1, Adquirible s2) {
		if (s1.esPromocion() == s2.esPromocion()) {
			int comparacionPorPrecio = Double.compare(s2.getPrecio(), s1.getPrecio());
			if (comparacionPorPrecio != 0)
				return comparacionPorPrecio;
			return Double.compare(s2.getTiempoDeRealizacion(), s1.getTiempoDeRealizacion());
		} else if (s1.esPromocion() && !s2.esPromocion())
			return -1;
		return 1;
	}
}
