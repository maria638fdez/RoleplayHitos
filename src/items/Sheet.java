package items;

import java.util.ArrayList;
import java.util.EnumMap;
import app.SheetRepresentation;
import interfaces.AttributesDictionary;
import interfaces.Caracteristica;
import interfaces.Habilidad;

public class Sheet {
	
	public String name;
	protected SheetRepresentation representation;
	
	int drama;
	int currentDrama;
	int RR;
	public static AttributesDictionary<Caracteristica> caracteristicas;
	public static AttributesDictionary<Habilidad> habilidades;
	
    public int getRR() {
		return RR;
	}

	public void setRR(int rR) {
		if(RR<getResistenciaTotal() && RR>=0) {
			RR = rR;
		}
	}

	
	public Sheet(SheetRepresentation sheetRepresentation, String name, int drama) {
		
		caracteristicas = 
				new AttributesDictionary<Caracteristica>(Caracteristica.class);
		habilidades = 
				new AttributesDictionary<Habilidad>(Habilidad.class);
		
    	this.name = name;
	    this.drama = drama;
	    this.representation = sheetRepresentation;
    	SetCaracteristicas(0, 0, 0, 0);
	    SetHabilidades(0, 0, 0, 0, 0, 0, 0);

	}
	
	public int getCurrentDrama() {
		return currentDrama;
	}
	
	public int getDrama() {
		return drama;
	}
	
	public void setDrama(int drama) {
		this.drama = drama;
	}
	

    public void SetCaracteristicas(int fortaleza, int voluntad, int reflejos, int intelecto) {
    	caracteristicas.put(Caracteristica.Fortaleza, fortaleza);
    	caracteristicas.put(Caracteristica.Voluntad, voluntad);
    	caracteristicas.put(Caracteristica.Reflejos, reflejos);
    	caracteristicas.put(Caracteristica.Intelecto, intelecto);
    }
    
	public void SetHabilidades(int forma_fisica, int combate, int interaccion, int percepcion, int subterfugio, int cultura, int profesion) {
    	habilidades.put(Habilidad.Forma_Fisica, forma_fisica);
    	habilidades.put(Habilidad.Combate, combate);
    	habilidades.put(Habilidad.Interaccion, interaccion);
    	habilidades.put(Habilidad.Percepción, percepcion);
    	habilidades.put(Habilidad.Subterfugio, subterfugio);
    	habilidades.put(Habilidad.Cultura, cultura);
    	habilidades.put(Habilidad.Profesión, profesion);
    }
	


    public int GetAttribute(String s) {
        try {
            Caracteristica caracteristica = Caracteristica.valueOf(s);
            return caracteristicas.GetAttribute(caracteristica);
        	} catch (IllegalArgumentException e) {        }
        try {
            Habilidad habilidad = Habilidad.valueOf(s);
            return habilidades.GetAttribute(habilidad);
        	} catch (IllegalArgumentException e) {
        }
        return 0;	
    }
    
    

    private int divideBy(int valueup, int valuedown) {
    	return (int) Math.floor(valueup / valuedown);
    }
    
    public int GetIniciativa() {
    	int reflejos = caracteristicas.GetAttribute(Caracteristica.Reflejos);
    	int intelecto = caracteristicas.GetAttribute(Caracteristica.Intelecto);
    	return reflejos + divideBy(intelecto, 2);
    }
    
    public int getAguante() {
    	int fortaleza = caracteristicas.GetAttribute(Caracteristica.Fortaleza);
    	int voluntad = caracteristicas.GetAttribute(Caracteristica.Voluntad);
    	return fortaleza + divideBy(voluntad,2);
    }
    
    public int getResistenciaTotal(){
    	return getAguante()*3;
    }
    
    public int getDefensa(){
    	int forma_fisica = habilidades.GetAttribute(Habilidad.Forma_Fisica);
    	int combate = habilidades.GetAttribute(Habilidad.Combate);
    	return Math.max(forma_fisica, combate) + 5;
    }
    
    public int getDefensaDesprevenido(){
    	return getDefensa() - 2;
    }
    
    public int getCAC(){
    	int fortaleza = caracteristicas.GetAttribute(Caracteristica.Fortaleza);
    	int combate = habilidades.GetAttribute(Habilidad.Combate);
    	return divideBy(fortaleza+combate, 4);
    }
    
    public int getDIS(){
    	int combate = habilidades.GetAttribute(Habilidad.Combate);
    	return divideBy(combate, 4);
    }
    
    public int getSumaCaracteristicas() {
    	int sum = 0;
    	for (Caracteristica caracteristica : Caracteristica.values()) {
    		sum = sum+caracteristicas.GetAttribute(caracteristica);
    	}
    	return sum;
    }
    
    public int getSumaHabilidades() {
    	int sum = 0;
    	for (Habilidad habilidad : Habilidad.values()) {
    		sum = sum + habilidades.GetAttribute(habilidad);
    	}
    	return sum;
    }
    
    public int getMaxValue(Habilidad[] nombresHabilidades ) {
    	int max = habilidades.GetAttribute(nombresHabilidades[0]);
    	for (Habilidad h : nombresHabilidades) {
    		if (habilidades.GetAttribute(h) > max) { max=habilidades.GetAttribute(h);}
    	}
    	return max;
    }
    
    public int getMaxValue(Caracteristica[] nombresCaracteristicas ) {	
    	int max = caracteristicas.GetAttribute(nombresCaracteristicas[0]);
    	for (Caracteristica c : nombresCaracteristicas) {
    		if (caracteristicas.GetAttribute(c) > max) { max=caracteristicas.GetAttribute(c);}
    	}
    	return max;
    }
    
	public boolean validateSheet(Campaign campaign) {
		
		int sum_caracteristicas = getSumaCaracteristicas();
		int sum_habilidades = getSumaHabilidades();

		String mensaje = "Ficha correcta";
		if (campaign.getCampaign_Drama() != getDrama()) {mensaje = "Drama incorrecto"; }
		if (campaign.getCampaign_PuntosCaracteristica() != sum_caracteristicas) {mensaje = "Suma incorrecta de características"; }
		if (campaign.getCampaign_PuntosHabilidades() != sum_habilidades) {mensaje = "Suma incorrecta de habilidades"; }
		
		int cmax = getMaxValue(Caracteristica.values());
		int cmin = getMaxValue(Habilidad.values());
		
		//Más alta que no supere el max
		//Más baja que no sea 0 o menos
		
		
		//Validation message: easy to modify in case the message is not correct
		if(mensaje != "Ficha correcta") {
			System.out.println(this.name +" : "+mensaje);
			return false;
		}else {
			System.out.println(this.name +" : "+mensaje);
			return true;
		}
	}
    

}
