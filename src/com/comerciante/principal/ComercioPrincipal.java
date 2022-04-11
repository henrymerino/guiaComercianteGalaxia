package com.comerciante.principal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.comerciante.galaxia.convenio.GalaxiaConvenio;
import com.comerciante.galaxia.reglas.Comerciante;
import com.comerciante.galaxia.simbolos.Simbolos;

/*
 * Clase ComercioPrincipal desde donde arranca nuestra aplicacion
 */
public class ComercioPrincipal {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// instancia de objeto GalaxiaConvenio
		GalaxiaConvenio galaxiaConvenio = new GalaxiaConvenio();
		// instancia de los simbolos, que obtiene el romano y su peso valor en algebraico
		Simbolos Simbolos = new Simbolos(galaxiaConvenio.getGalaxiaSimbos());
		// se lee el archivo con las instrucciones 
		try {
			FileInputStream file = new FileInputStream("InputFile");
			BufferedReader reader = new BufferedReader(new InputStreamReader(file));
			List<String> instrucciones = new ArrayList<String>();
			String line;
			while((line = reader.readLine()) != null){
				instrucciones.add(line);
			}
			Comerciante comerciante =  new Comerciante(Simbolos);
			comerciante.comercio(instrucciones);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
