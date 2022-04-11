package com.comerciante.galaxia.comando.implentar;

import com.comerciante.galaxia.comando.interfaz.IComando;
import com.comerciante.galaxia.reglas.Comerciante;

public class ComandosDesconocidos implements IComando {

	@Override
	public void ejecutarComando(Comerciante comerciante, String comandoString) {
		comerciante.informeComandoDesconocido();
	}

}
