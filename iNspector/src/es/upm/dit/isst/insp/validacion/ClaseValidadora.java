package es.upm.dit.isst.insp.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClaseValidadora {
	
	public static void main(String[] args) {

		//compruebaEmail("yo@yo");
		//compruebaCIF("12345");
		
	}
	
	/**
	 * Metodo que comprueba si un email sigue el formato de email estándar
	 * Adaptado de: https://entredesarrolladores.com/17058/validaci%C3%B3n-e-mail-en-java
	 * @param email que se quiere comprobar 
	 * @return true cuando el email cumple el formato establecido
	 */
	public static boolean compruebaEmail(String email_a_comprobar) {
		
		//Definicion de algunos emails auxiliares para que la realizacion de pruebas sea mas sencilla
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
	
		String email = email_a_comprobar;

		Matcher matcher = pattern.matcher(email);

		if (matcher.find() == true) {
			System.out.println("El email " + email +" es valido  :)");
			return true;
		} else {
			System.out.println("El email " + email +" NO es valido");
			return false;
		}
	}
	
	
	/**
	 * Metodo que comprueba si un CIF sigue el formato de CIF
	 * Adaptado de: https://www.dropbox.com/s/ngefaf33ssfabxb/ValidadorDNI.java?dl=0
	 * @param cif que se quiere verificar
	 * @return true cuando el formato del CIF es correcto
	 */
	public static boolean compruebaCIF(String cif) {
		
		//Definicion de algunos CIFs auxiliares para que la realizacion de pruebas sea mas sencilla
		String cif_falso_1 = "e1";
		String cif_falso_2 = "e2";
		String cif_falso_3 = "e3";
		
		if (cif.equals(cif_falso_1) || cif.equals(cif_falso_2) || cif.equals(cif_falso_3)) {
			System.out.println("El CIF introducido es un CIF falso utilizado para las pruebas");
			return true;
		}
	
		String letraMayuscula = ""; //Guardaremos la letra introducida en formato mayúscula
		
		//Aqui excluimos cadenas distintas a 9 caracteres que debe tener un CIF y también si el último caracter no es una letra
		if(cif.length() != 9 ) {
			System.out.println("El CIF "+cif+" NO es valido, porque tiene un numero de caracteres no valido");
			return false;
		}
		
		if(Character.isLetter(cif.charAt(8)) == false ) {
			System.out.println("El CIF "+ cif+" NO es valido, porque el ultimo caracter no es una letra");
			return false;
		}

		if (letraCIF(cif).equals("")) {
			System.out.println("El CIF "+cif+" NO es valido, porque alguno de los 8 primeros caracteres no es un numero");
			return false;
		}
		
		letraMayuscula = (cif.substring(8)).toUpperCase();// Al superar la primera restricción, la letra la pasamos a mayúscula

		// Validamos que los 8 primeros caracteres son numeros y que la letra introducida es igual a la resultante de la ecuacion, 
		// llamando a los metodos auxiliares soloNumeros() y letraCIF()
		if(soloNumeros(cif) == true && letraCIF(cif).equals(letraMayuscula)) {
			System.out.println("El CIF " +cif+ " es valido!!  :)");
			return true;
		}else {
			System.out.println("El CIF "+cif+" NO es valido, porque la letra no es correcta");
			return false;
		}
		
	}
	
	/**
	 * Metodo auxiliar que se encarga de comprobar que todos los caracteres de la cadena que se le pasa como parametro
	 * son numeros
	 * @param cadena de caracteres a analizar
	 * @return true cuando toda la cadena esta compuesta unicamente por numeros
	 */
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
	
	/**
	 * Metodo auxiliar que calcula la letra que le corresponde a un determinado CIF
	 * @param CIF que se quiere validar
	 * @return letra correspondiente al CIF introducido como parametro
	 */
	private static String letraCIF(String cif) {
		
		String primeros_digitos = cif.substring(0,8);
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
	
	
	//comprueba que todos los caracteres de la cadena son numeros. Devuelve true cuando al menos uno de los caracteres NO es un numero
	/**
	 * Metodo auxiliar que comprueba si algun caracter de la cadena NO es un numero
	 * //Fuente: https://es.stackoverflow.com/questions/325876/comprobar-si-un-car%C3%A1cter-de-un-string-es-un-n%C3%BAmero
	 * @param cadena que queremos analizar
	 * @return true cuando alguno de los caracteres de la cadena NO es un numero
	 */
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
