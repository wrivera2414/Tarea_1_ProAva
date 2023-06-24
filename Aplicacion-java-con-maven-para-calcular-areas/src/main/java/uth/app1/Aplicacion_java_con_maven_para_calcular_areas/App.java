package uth.app1.Aplicacion_java_con_maven_para_calcular_areas;

import java.util.Scanner;

/**
 MI PRIMER PROGRAMA UTILIZANDO JAVA, MAVEN, JUNIT
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String nombre = "Wencell";
    	String apellido = "Rivera";
        System.out.println( "Hola bienvenido a tu calculadora de áreas  " +nombre);
        System.out.println( "Hola bienvenido a tu calculadora de áreas  " +apellido);
        int opcion = 0;
        
        while(opcion !=5) {
        	
        	if(opcion>5)
        	{
        		System.err.println( " Esta Opcion no esta en el menu  " );
        		System.exit(0);
        	}
        	else { 
        		
        		MostrarMenu();    
                opcion = LeerEnteros("");
                Double total = null;
                Double areaC = null;
                Double areaT = null;
                Double areaR = null;
                Double areaD = null;
                
                System.out.println();
               switch (opcion) 
               {
               
               case 1: 
            	   
            	   Double Pi = 3.14;
            	   System.out.println( "***********************" );
            	   System.out.println(  "Selecciono Opcion #1: ");
            	   System.out.println( "***********************" );
            	   
            	  total = circulo(LeerDecimalesTeclado("Ingrese el diametro en Cm del circulo : "));
            	  
            	  areaC = (total*total)*Pi;
            	  
            	  System.out.println("EL Area del circulo es:  " + areaC + "cm²");
            	  
            	  break;
            	  
               case 2: 
            	   
            	   System.out.println( "*********************" );
            	   System.out.println( "Selecciono Opcion #2: ");
            	   System.out.println( "*********************" );
            	   
            	   total = cuadrado(LeerDecimalesTeclado("Ingrese Cm de  Lado A del Cuadrado: ") , LeerDecimalesTeclado("Ingrese Cm de  Lado B del Cuadrado: "));
            	   
            	   areaD = (total);
            	   
            	   System.out.println("EL Area del Cuadrado es:  " + areaD + "cm²");
            	   
            	   break;
            	   
               case 3: 
            	   System.out.println( "*********************" );
            	   System.out.println( "Selecciono Opcion #3: ");
            	   System.out.println( "*********************" );
            	   
            	   total = rectangulo(LeerDecimalesTeclado("Ingrese Cm de Largo del rectangulo: ") , LeerDecimalesTeclado("Ingrese Cm de ancho del rectangulo: "));
            	   
            	   areaR = (total);
            	   System.out.println("EL Area del Rectangulo es:  " + areaR + "cm²");
            	   break;
            	   
               case 4:
            	   System.out.println( "**********************" );
            	   System.out.println( "Selecciono Opcion #4: ");
            	   System.out.println( "**********************" );
            	   
            	   total = rectangulo(LeerDecimalesTeclado("Ingrese Cm de  Base del Triangulo: ") , LeerDecimalesTeclado("Ingrese Cm de Altura del Triangulo: "));
            	   
            	   areaT = (total/2);
            	   
            	   System.out.println(  "El area del Triangulo es:  " + areaT + "Cm2");
            	   
            	   break;
            
               case 5: 
            	   System.out.println("Gracias por usar nuestro programa");
            	   break;
            	  
               }
        	
         	  System.out.println();
         	  
        	}
        	    	
      
        }
                
        
    }
    
    
    private static void MostrarMenu() 
    {
    	System.out.println( " -----BIENVENIDO AL MENU DE SELECCION----- " );	
    	System.out.println( "1. Area de un circulo" );
    	System.out.println( "2. Area de un cuadrado" );
    	System.out.println( "3. Area de un rectangulo" );
    	System.out.println( "4. Area de un triangulo" );
    	System.out.println( "5. Salir del sistema " );
    	System.out.print( "Elija la opcion que desea operar: " );
    	
    }


    private static double LeerDecimalesTeclado(String mensaje) 
    {
    	Scanner teclado = new Scanner(System.in);
    	System.out.print(mensaje);
    	return teclado.nextDouble();
    }
    
    private static int LeerEnteros(String mensaje) 
    {
    	Scanner teclado = new Scanner(System.in);
    	System.out.print(mensaje);
    	
    	return teclado.nextInt();
    }
    
	
    public static double circulo(double Diametro) {
		
    	Double resultado = 0.0;
    	Double radio;
    	
    	if(Diametro==0) 
    	{
    		resultado= null;
    		System.out.println( "ERROR AL CALCULAR EL RADIO NO PUEDE SER 0" );
    	}
    	else 
    	{
    		radio = Diametro/2.0;	
    		resultado = radio;
    		
    	}
    	
    	
		return resultado;
	}
    
    
    public static double cuadrado(double ladoA, double ladoB) {
		
    	double AreaCuadrado=0;
    	if (ladoA != ladoB) 
    	{
    		System.err.println("Los Lados Ingresados no son iguales por lo tanto no es un cuadrado");
    		
    	}
    	else {
    	
		
    	AreaCuadrado = (ladoA*ladoB);
		
    	}
    	
    	return AreaCuadrado;
	}


	public static double rectangulo(double largo, double ancho) {
		
		double AreaRectangulo=0;
		
		if (largo == ancho) 
		{
			
			System.err.println("LARGO Y ANCHO DE LA FIGURA SON IGUALES ESTA FIGURA NO ES UN RECTANGULO");
			
		}
		else 
		{
			
			AreaRectangulo = (largo*ancho);
			
		}
		
		
		
		return AreaRectangulo;
		
	}


	public static double triangulo(double base, double altura) {
		
		Double resultado = 0.0;
		if(base <=0 || altura<=0) 
		{
		resultado = null;
			System.err.println(" INTENTANDO SACAR EL AREA DE UN TRIANGULO");
			System.err.println(" LA BASE O ALTURA NO PUEDEN SER 0 ");
		} else 
		{
			resultado = (base*altura)/2.0;
			
		}
		
		return resultado;
	}
    



	



}
