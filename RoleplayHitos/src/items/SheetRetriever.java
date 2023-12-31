package items;

import java.util.ArrayList;
import java.util.EnumMap;

public class SheetRetriever {
	
	String name;
	int drama;
	int currentDrama;
	
    private EnumMap<Caracteristica, Integer> caracteristicas = new EnumMap<Caracteristica, Integer>(Caracteristica.class);
    private EnumMap<Habilidad, Integer> habilidades = new EnumMap<Habilidad, Integer>(Habilidad.class);
	
	public SheetRetriever(String name, int drama) {
    	this.name = name;
    	SetCaracteristicas(0, 0, 0, 0);
	    SetHabilidades(0, 0, 0, 0, 0, 0, 0);
	    this.drama = drama;
	}
	
    public enum Caracteristica {
   	Fortaleza,
   	Voluntad,
   	Reflejos,
   	Intelecto;
   }
   
   public enum Habilidad {
   	Forma_Fisica,
   	Combate,
   	Interaccion,
   	Percepción,
   	Subterfugio,
   	Cultura,
   	Profesión;
   }
   
   public ArrayList<String> getAllCaracteristicas() {
	   ArrayList<String> caracteristicas = new ArrayList<String>();
	   for (Caracteristica c : Caracteristica.values()) {
		   caracteristicas.add(c.toString());
	   }
	   return caracteristicas;
   }
   
   public ArrayList<String> getAllHabilidades() {
	   ArrayList<String> habilidades = new ArrayList<String>();
	   for (Habilidad c : Habilidad.values()) {
		   habilidades.add(c.toString());
	   }
	   return habilidades;
   }
	
    public void SetCaracteristicas(int fortaleza, int voluntad, int reflejos, int intelecto) {
    	this.SetAttribute(Caracteristica.Fortaleza, fortaleza);
    	this.SetAttribute(Caracteristica.Voluntad, voluntad);
    	this.SetAttribute(Caracteristica.Reflejos, reflejos);
    	this.SetAttribute(Caracteristica.Intelecto, intelecto);
    }
    
	public void SetHabilidades(int forma_fisica, int combate, int interaccion, int percepcion, int subterfugio, int cultura, int profesion) {
    	this.SetAttribute(Habilidad.Forma_Fisica, forma_fisica);
    	this.SetAttribute(Habilidad.Combate, combate);
    	this.SetAttribute(Habilidad.Interaccion, interaccion);
    	this.SetAttribute(Habilidad.Percepción, percepcion);
    	this.SetAttribute(Habilidad.Subterfugio, subterfugio);
    	this.SetAttribute(Habilidad.Cultura, cultura);
    	this.SetAttribute(Habilidad.Profesión, profesion);
    }
	
    public void SetAttribute(Caracteristica c, int value) {
    	caracteristicas.put(c, value);
    }
    
    public void SetAttribute(Habilidad h, int value) {
    	habilidades.put(h, value);
    }
    
    public int GetCaracteristica(String s) {
		return GetAttribute(Caracteristica.valueOf(s));
    }
    
    public int GetAttribute(Caracteristica c) {
    	return caracteristicas.get(c);
    }
    
    public int GetAttribute(Habilidad h) {
    	return habilidades.get(h);
    }
    
    private int divideBy(int valueup, int valuedown) {
    	return (int) Math.floor(valueup / valuedown);
    }
    
    public int GetIniciativa() {
    	int reflejos = GetAttribute(Caracteristica.Reflejos);
    	int intelecto = GetAttribute(Caracteristica.Intelecto);
    	return reflejos + divideBy(intelecto, 2);
    }
    
    public int getAguante() {
    	int fortaleza = GetAttribute(Caracteristica.Fortaleza);
    	int voluntad = GetAttribute(Caracteristica.Voluntad);
    	return fortaleza + divideBy(voluntad,2);
    }
    
    public int getResistenciaTotal(){
    	return getAguante()*3;
    }
    
    public int getDefensa(){
    	int forma_fisica = GetAttribute(Habilidad.Forma_Fisica);
    	int combate = GetAttribute(Habilidad.Combate);
    	return Math.max(forma_fisica, combate) + 5;
    }
    
    public int getDefensaDesprevenido(){
    	return getDefensa() - 2;
    }
    
    public int getCAC(){
    	int fortaleza = GetAttribute(Caracteristica.Fortaleza);
    	int combate = GetAttribute(Habilidad.Combate);
    	return divideBy(fortaleza+combate, 4);
    }
    
    public int getDIS(){
    	int combate = GetAttribute(Habilidad.Combate);
    	return divideBy(combate, 4);
    }
    

}
