package fr.formation.api;

public interface Views {
	
	public static interface Common { }
	
	public static interface Animal extends Common { }
	public static interface AnimalDetail extends Animal { }

    public static interface Parcours extends Common {}
	public static interface ParcoursDetail extends Parcours{}
	
}
