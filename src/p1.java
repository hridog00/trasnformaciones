import java.util.Scanner;
import java.util.Stack;

public class p1{
	static Stack<Stack<String>> total;
	static Stack<String> end;
	public p1(){
		 total = new Stack<Stack<String>>();
	}
	
	public static void main (String args[]){
		total = new Stack<Stack<String>>();
		int i=0;
		int j=0;
		String Diccionario[] = new String[25143];
		String Entrada[] = new String[25143];
		String palabra1[] = new String[25143];
		String palabra2[] = new String[25143];
		String Salida[] = new String[25143];
		
		Scanner sc = new Scanner(System.in);
		do{
			
			Diccionario[i] = sc.nextLine();
			Diccionario[i] = Diccionario[i].toLowerCase();
			if(Diccionario[i].length()==0){
				break;
			}
			i++;
			
		}while((i<=25143));
		if(i==0){
			System.out.println("Diccionario vacio");
			System.exit(0);
		}
		int p=0;
		    while(sc.hasNextLine()){
       
		    String ent = sc.nextLine();
			Entrada[j] = ent;
		    
			if((Entrada[j].length()==0)&& (Entrada[j].equals(""))){
				break;
			}
			if(Entrada[j].indexOf(" ")==-1){
				j++;
			}else{
			palabra1[p] = Entrada[j].substring(0, Entrada[j].indexOf(" "));

			palabra2[p] = Entrada[j].substring(Entrada[j].indexOf(" ")+1);
			if(palabra2[p].indexOf(" ")!= -1){
					palabra2[p] = palabra2[p].substring(0, palabra2[p].indexOf(" "));
				}
            p++;
			j++;
			}
		    }
		   

		for(int x=0;x<p;x++){
			if(buscarDiccionario(palabra1[x],Diccionario)){
			
				if(buscarDiccionario(palabra2[x],Diccionario)){
					if(palabra1[x].length() == palabra2[x].length()){
						if(palabra1[x].equals(palabra2[x])){
							System.out.println("Entro 1");

							System.out.println("Sin solución.");
						}else{
							total = new Stack<Stack<String>>();
							end = new Stack<String>();
							Stack<String> lista = new Stack<String>();
							lista.push(palabra1[x]);
							String[] dicaux = palabrasMismaLongitud(palabra1[x].length(),Diccionario,palabra1[x],i);
							if(dicaux.length==0){
								System.out.println("Entro 2");

								System.out.println("Sin solución.");

							}else{
								metodo(lista,palabra1[x],palabra2[x],dicaux);
								/*if(total.size()==0){
									System.out.println("Entro 3");

									System.out.println("Sin solución.");

								}else{
									buscarSolucion(total);
								}*/
								if(end.size()==0){
									System.out.println("Entro 3");

									System.out.println("Sin solución.");

								}else{
									System.out.println("Solucion");
									while(!end.isEmpty()){
										System.out.println(end.pop());
									}
								}
							}
							
						}
						
						
						//buscarSolucion(palabra1[x],palabra2[x],Diccionario, i);
					}else{
						System.out.println("Sin solución.");
					}
				}else{
					System.out.println("Sin solución.");
				}
			}else{
				System.out.println("Sin solución.");
			}
							System.out.println("");

		}
		
	}
	public static void buscarSolucion(Stack<Stack<String>> resultado){
		Stack<String> definitiva = new Stack<String>();
		definitiva = resultado.pop();
		Stack<String> aux = new Stack<String>();
		System.out.println(definitiva.size());
		while(!resultado.isEmpty()){
			aux = resultado.pop();
			System.out.println(aux.size());
			if(aux.size()<definitiva.size()){
				definitiva=aux;
			}
		}
		System.out.println("Slucion");
		while(!definitiva.isEmpty()){
			System.out.println(definitiva.pop());
		}
	}
	
	public static String[] buscarPosibles(String[]dic, String p){
		String[] posibles = new String[dic.length];
		int j=0;
		for(int i=0;i<dic.length;i++){
			if(dic[i]!=null){
				if(posiblePalabra(p, dic[i])){
					posibles[j]=dic[i];
					j++;
				}
			}
			
		}
		String[] def = new String[j];
		for(int i=0;i<j;i++){
			def[i] = posibles[i];
		}
		return def;
	}
	
	public static boolean posiblePalabra(String aux, String posible){
		int j=0;
		boolean res=false;
		while(j<posible.length()){
			if((posible.charAt(j)!=aux.charAt(j))){
				if(comprobarPalabra(posible, aux, j)){
					res = true;
					break;
				}else{
					j++;
				}
			}else{
				j++;
			}
		}
		return res;
	}
	public static boolean comprobarPalabra(String p_dic, String p, int pos){
		int i=0;
		boolean res = true;
		while(i<p.length()){
			if(i==pos){
				i++;
			}else{
				if(p_dic.charAt(i)==p.charAt(i)){
					res = true;
					i++;
				}else{
					res = false;
					break;
				}
			}
		}
		return res;
	}
	
	public static boolean buscarDiccionario(String palabra, String[] diccionario){
		int i=0;
		boolean res = false;
		while(i<diccionario.length){

			if (palabra.equals(diccionario[i])){
				res = true;
				break;
			}else{
				i++;
			}
		}
		return res;
		
	}
	
	private static String[] palabrasMismaLongitud(int longitud, String[] diccionario,String p1, int tam) {
		int tamaux=0;
		for(int i=0;i<tam;i++){
			if(diccionario[i].length()==longitud){
				tamaux++;
			}
		}
		String aux[] = new String [tamaux-1];
		int j=0;
		for(int i=0;i<tam;i++){
			if(!diccionario[i].equals(p1)){
			if(diccionario[i].length()==longitud){
				aux[j]=diccionario[i];
				j++;
			}
			}
		}
		return aux;
	}
	public static void metodo(Stack<String> lista, String p1, String p2, String[] dicaux){
		if(p1.equals(p2)){
			if(end.isEmpty()){
				end = lista;
			}
			if(end.size()>lista.size()){
				System.out.println("Cambio solucion");
				end = lista;
			}
			/*total.push(lista);
			System.out.println(total.size() +"  " +lista.size());*/
		}else{
			//System.out.println("Palabra 1 "+p1);
			String Posibles[] = buscarPosibles(dicaux,p1);
			if(Posibles.length>0){
				System.out.println("Hay Posibles");
				for(int i=0;i<Posibles.length;i++){
					System.out.println(Posibles[i]);
				}
				for(int i=0;i<Posibles.length;i++){
					System.out.println("P1: "+p1);
					System.out.println("Siguiente: "+Posibles[i]);
					Stack <String> aux = new Stack<String>();
					aux = duplicar(lista);
					aux.push(Posibles[i]);
					System.out.println(aux.size());
					String[] dic = eliminarPalabra(Posibles [i], dicaux);
					metodo(aux, Posibles[i], p2,dic);
				}
			}
			
			
		}
	}
	public static Stack<String> duplicar(Stack <String> or){
		Stack<String> nueva = new Stack<String>();
		Stack<String> aux = new Stack<String>();
		while(!or.isEmpty()){
			aux.push(or.pop());
		}
		while(!aux.isEmpty()){
			String auxS = aux.pop();
			nueva.push(auxS);
			or.push(auxS);
		}
		return nueva;
		
	}
	
	public static String[] eliminarPalabra(String p, String[] dic){
		String[] nuevo = new String[dic.length-1];
		int j=0;
		for(int i=0;i<dic.length;i++){
			if(dic[i]!=null){
				if(!dic[i].equals(p)){
					nuevo[j] =dic[i];
					j++;
				}
			}
			
			
		}
		return nuevo;
	}
	
	
}