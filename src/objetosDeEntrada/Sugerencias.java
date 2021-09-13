package objetosDeEntrada;

public enum Sugerencias {
	PROMO("Promocion"), ATRACCION("Atraccion");

	private String tipoSugerencia;

	Sugerencias(String tipo) {
		this.tipoSugerencia = tipo;
	}

	// Devuelvo el tipo de sugerencia en string
	public static String tipo(Adquirible sugerencia) {
		String salida = null;
		if (sugerencia.esPromocion())
			salida = PROMO.tipoSugerencia;
		else
			salida = ATRACCION.tipoSugerencia;
		return salida;
	}
}
