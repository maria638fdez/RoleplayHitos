package interfaces;

import java.util.ArrayList;

public enum Caracteristica implements Attribute<Caracteristica>{
   	Fortaleza,
   	Voluntad,
   	Reflejos,
   	Intelecto;

	@Override
	public ArrayList<String> getAllNames() {
		   ArrayList<String> caracteristicas = new ArrayList<String>();
		   for (Caracteristica c : Caracteristica.values()) {
			   caracteristicas.add(c.toString());
		   }
		   return caracteristicas;
	}

}