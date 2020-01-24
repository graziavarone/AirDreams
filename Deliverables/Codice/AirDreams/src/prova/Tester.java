package prova;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//istanzio degli aeroporti
		Aeroporto ap1=new Aeroporto("AAA","aa","aaa","aaaa");
		Aeroporto ap2=new Aeroporto("BBB","bb","bbb","bbbb");
		Aeroporto ap3=new Aeroporto("CCC","cc","ccc","cccc");
		Aeroporto ap4=new Aeroporto("DDD","dd","ddd","dddd");
		Aeroporto ap5=new Aeroporto("EEE","ee","eee","eeee");
		Aeroporto ap6=new Aeroporto("FFF","ff","fff","ffff");
		Aeroporto ap7=new Aeroporto("GGG","gg","ggg","gggg");
		
		//istanzio dei voli senza scali
		DettagliVolo volo1=new DettagliVolo(1,"21/04",200,120,"9h 40min","21:40",ap1,ap2);
		DettagliVolo volo2=new DettagliVolo(2,"23/04",200,120,"9h 40min","21:40",ap2,ap3);
		DettagliVolo volo3=new DettagliVolo(3,"22/04",200,120,"9h 40min","21:40",ap1,ap5);
		DettagliVolo volo4=new DettagliVolo(4,"21/04",200,120,"9h 40min","21:40",ap5,ap7);
		DettagliVolo volo5=new DettagliVolo(5,"25/04",200,120,"9h 40min","21:40",ap7,ap1);
		DettagliVolo volo6=new DettagliVolo(6,"26/04",200,120,"9h 40min","21:40",ap4,ap2);
		DettagliVolo volo7=new DettagliVolo(7,"29/04",200,120,"9h 40min","21:40",ap2,ap5);
		DettagliVolo volo8=new DettagliVolo(8,"21/04",200,120,"9h 40min","21:40",ap6,ap7);
		DettagliVolo volo9=new DettagliVolo(9,"22/04",200,120,"9h 40min","21:40",ap5,ap4);

		//istanzio dei voli con scali
		ArrayList<DettagliVolo> listaVoli1=new ArrayList<DettagliVolo>();
		listaVoli1.add(volo1);
		listaVoli1.add(volo7);
		Volo voloConScalo1=new Volo(listaVoli1.get(0).getAeroportoPartenza(),
				listaVoli1.get(listaVoli1.size()-1).getAeroportoDestinazione(),listaVoli1,true);
	
		ArrayList<DettagliVolo> listaVoli2=new ArrayList<DettagliVolo>();
		listaVoli2.add(volo3);
		listaVoli2.add(volo9);
		listaVoli2.add(volo6);
		Volo voloConScalo2=new Volo(listaVoli2.get(0).getAeroportoPartenza(),
				listaVoli2.get(listaVoli2.size()-1).getAeroportoDestinazione(),listaVoli2,false);
	
		ArrayList<DettagliVolo> listaVoli3=new ArrayList<DettagliVolo>();
		listaVoli3.add(volo8);
		Volo voloConScalo3=new Volo(listaVoli3.get(0).getAeroportoPartenza(),
				listaVoli3.get(listaVoli3.size()-1).getAeroportoDestinazione(),listaVoli3,true);
	
		ArrayList<DettagliVolo> listaVoli4=new ArrayList<DettagliVolo>();
		listaVoli4.add(volo5);
		listaVoli4.add(volo1);
		listaVoli4.add(volo2);
		Volo voloConScalo4=new Volo(listaVoli4.get(0).getAeroportoPartenza(),
				listaVoli4.get(listaVoli4.size()-1).getAeroportoDestinazione(),listaVoli4,false);
	
		System.out.println("QUESTA NON E' UNA PROVA");
		System.out.println("RIPETO");
		System.out.println("QUESTA NON E' UNA PROVA");

		System.out.println(voloConScalo1);
		System.out.println(voloConScalo2);
		System.out.println(voloConScalo3);
		System.out.println(voloConScalo4);
	}

}
