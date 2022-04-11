package com.comerciante.galaxia.analizarcomando;

import com.comerciante.galaxia.comando.implentar.ComandoPregunta;
import com.comerciante.galaxia.comando.implentar.ComandosDesconocidos;
import com.comerciante.galaxia.comando.implentar.DeclaracionComando;
import com.comerciante.galaxia.comando.implentar.DefinicionComando;
import com.comerciante.galaxia.comando.interfaz.IComando;
import com.comerciante.galaxia.simbolos.PalabrasDiccionario;
import com.comerciante.galaxia.simbolos.Simbolos;

public class AnalizadorComandos {

	 public static final String SEPARADOR = " ";
	    public static final int INICIO_INDICE = 0;
	    
	    private String commandString;
	    
	    PalabrasDiccionario dictionario;
	    
	    public AnalizadorComandos(final String commandString, PalabrasDiccionario dictionario) {
	        this.commandString = commandString;
	        this.dictionario = dictionario;
	    }

	    public IComando toCommand(Simbolos symbolo) {
	        if(isNullOrEmpty(commandString)) return new ComandosDesconocidos();
	        return buildCommandsList(symbolo, commandString);
	    }

	    private IComando buildCommandsList(Simbolos symbols, final String commandString) {

	    	IComando mappedCommand = buscarComando(symbols, commandString);
	        
	        if(mappedCommand == null){
	        	return new ComandosDesconocidos();
	        }
	        return mappedCommand;
	    }

	    private boolean isNullOrEmpty(final String commandString) {
	        return (null == commandString || commandString.trim().length() == 0);
	    }

	    @SuppressWarnings("static-access")
		private IComando buscarComando(Simbolos symbolos, final String commandString) {
	    	if(commandString.trim().endsWith("?")){
	    		return new ComandoPregunta();
	    	}else if(commandString.trim().endsWith("CREDITS")){
	    		return new DeclaracionComando();
	    	}else if(symbolos.getMapaSimbolos().get(String.valueOf(commandString.trim().charAt(commandString.trim().length()-1))) != null){
	    		return new DefinicionComando();
	    	}
	    	return new ComandosDesconocidos();
	    }
}
