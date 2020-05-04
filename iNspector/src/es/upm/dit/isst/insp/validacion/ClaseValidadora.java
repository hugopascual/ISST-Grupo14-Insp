package es.upm.dit.isst.insp.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClaseValidadora {
	
	public static void main(String[] args) {

		compruebaEmail("yo@yo");
		//compruebaCIF("12345");
		
		
	}
	
	//Fuente: https://entredesarrolladores.com/17058/validaci%C3%B3n-e-mail-en-java
	public static boolean compruebaEmail(String email_a_comprobar) {
		
		String email_falso_1 = "i1";
		String email_falso_2 = "i2";
		String email_falso_3 = "c1";
		String email_falso_4 = "c2";
		
		if (email_a_comprobar.equals(email_falso_1) || email_a_comprobar.equals(email_falso_2) || email_a_comprobar.equals(email_falso_3) || email_a_comprobar.equals(email_falso_4)) {
			System.out.println("El email introducido es un email falso utilizado para las pruebas");
			return true;
		}
		
		//patron que debe cumplir el email
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
		// El email a validar
		String email = email_a_comprobar;

		Matcher mather = pattern.matcher(email);
		System.out.println("comprobacion");

		if (mather.find() == true) {
			System.out.println("El email es valido!!  :)");
			return true;
		} else {
			System.out.println("El email NO es valido!!  :(");
			return false;
		}
	}
	
	//Adaptado de: https://www.dropbox.com/s/ngefaf33ssfabxb/ValidadorDNI.java?dl=0
	// Leer (al final del articulo): https://getquipu.com/blog/diferencia-entre-el-cif-y-el-nif/#que-es-el-nif-en-espana 
	//El NIF es mucha movida, vamos a comprobar que la letra que ha puesto el usuario es la correcta
	//devuelve true cuando el cif es valido y false cuando no es valido
	
	public static boolean compruebaCIF(String cif) {
		
		//podemos poner algunos cifs falsos que nosotros utilicemos en las pruebas:
		String cif_falso_1 = "e1";
		String cif_falso_2 = "e2";
		String cif_falso_3 = "e3";
		
		if (cif.equals(cif_falso_1) || cif.equals(cif_falso_2) || cif.equals(cif_falso_3)) {
			System.out.println("El CIF introducido es un CIF falso utilizado para las pruebas");
			return true;
		}
	
		String letraMayuscula = ""; //Guardaremos la letra introducida en formato mayúscula
		
		// Aquí excluimos cadenas distintas a 9 caracteres que debe tener un dni y también si el último caracter no es una letra
		
		if(cif.length() != 9 ) {
			System.out.println("El CIF NO es valido, porque tiene un numero de caracteres no valido");
			return false;
		}
		
		if(Character.isLetter(cif.charAt(8)) == false ) {
			System.out.println("El CIF NO es valido, porque el ultimo caracter no es una letra");
			return false;
		}

		if (letraCIF(cif).equals("")) {
			System.out.println("El CIF NO es valido, porque alguno de los 8 primeros caracteres no es un numero");
			return false;
		}
		
		// Al superar la primera restricción, la letra la pasamos a mayúscula
		
		letraMayuscula = (cif.substring(8)).toUpperCase();

		// Por último validamos que sólo tengo 8 dígitos entre los 8 primeros caracteres y que la letra introducida es igual a la de la ecuación
		// Llamamos a los métodos privados de la clase soloNumeros() y letraDNI()
		
		if(soloNumeros(cif) == true && letraCIF(cif).equals(letraMayuscula)) {
			System.out.println("El CIF es valido!!  :)");
			return true;
		}else {
			System.out.println("El CIF NO es valido, porque la letra no es correcta");
			return false;
		}
		
	}
	
	private static boolean soloNumeros(String cif) {

		int i, j = 0;
		String numero = ""; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
		String miCIF = ""; // Guardamos en una cadena los números para después calcular la letra
		String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};

		for(i = 0; i < cif.length() - 1; i++) {
			numero = cif.substring(i, i+1);

			for(j = 0; j < unoNueve.length; j++) {
				if(numero.equals(unoNueve[j])) {
					miCIF += unoNueve[j];
				}
			}
		}

		if(miCIF.length() != 8) {
			return false;
			
		}
		else {
			return true;
		}
	}
	
	private static String letraCIF(String cif) {
		
		String primeros_digitos = cif.substring(0,8);
		System.out.println("Me quedo con los 8 primeros caracteres del CIF");
		boolean todo_numeros = !testCadena(primeros_digitos);
		
		if (todo_numeros) {
			// pasar miNumero a integer quedandome con los primeros 8 caracteres correspondientes a las cifras del CIF/NIF
			
			int miCIF = Integer.parseInt(primeros_digitos);
			
			int resto = 0;
			String miLetra = "";
			String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

			resto = miCIF % 23;

			miLetra = asignacionLetra[resto];

			return miLetra;
		} else {
			System.out.println("Uno de los primeros 8 caracteres no es un numero");
			return ""; 
		}
		
	}
	//Fuente: https://es.stackoverflow.com/questions/325876/comprobar-si-un-car%C3%A1cter-de-un-string-es-un-n%C3%BAmero
	//comprueba que todos los caracteres de la cadena son numeros. Devuelve true cuando al menos uno de los caracteres NO es un numero
	private static boolean testCadena(String cadena){
        
		String numCuenta = cadena;
        boolean hay_letra = false;

        for( int i = 0; i < numCuenta.length(); i++ ) {
            if( !Character.isDigit( numCuenta.charAt(i) ) ) {
            	//no es numero
                //System.out.println( numCuenta.charAt(i) + " no es numero" );
                hay_letra = true;
            } else {
            	//es numero
                //System.out.println( numCuenta.charAt(i) + " es numero" );
            }
        }
        
        return hay_letra;
    }
	
	
}
