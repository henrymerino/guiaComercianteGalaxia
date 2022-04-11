package com.comerciante.galaxia.comando.interfaz;

import com.comerciante.galaxia.reglas.Comerciante;

public interface IComando {
	public void ejecutarComando(final Comerciante comerciante, String comandoString);

}
