package objetosDeEntrada;

public enum Sugerencia {
	PROMOABS("PromocionAbsoluta"), PROMOPOR("PromocionPorcentual"), PROMOAXB("PromocionAxB"), ATRACCION("Atraccion");

	private String tipoSugerencia;

	Sugerencia(String tipo) {
		this.tipoSugerencia = tipo;
	}

	public static String tipo(String sugerencia) {
		String salida = null;
		if(sugerencia.equals(PROMOABS.tipoSugerencia))
			salida = "Promocion Absoluta";
		else if(sugerencia.equals(PROMOPOR.tipoSugerencia))
			salida = "Promocion %";
		else if(sugerencia.equals(PROMOAXB.tipoSugerencia))
			salida = "Promocion 3x2";
		else if(sugerencia.equals(ATRACCION.tipoSugerencia))
			salida = "Atraccion";
		return salida;
	}
}
