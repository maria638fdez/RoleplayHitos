
package interfaces;

import java.util.ArrayList;


public enum Habilidad implements Attribute<Habilidad>{
	   Forma_Fisica,
	   Combate,
	   Interaccion,
	   Percepci�n,
	   Subterfugio,
	   Cultura,
	   Profesi�n;

	@Override
	public ArrayList<String> getAllNames() {
		ArrayList<String> habilidades = new ArrayList<String>();
		for (Habilidad h : Habilidad.values()) {
			habilidades.add(h.toString());
		}
		return habilidades;
	}
	
}
