package uth.app1.Aplicacion_java_con_maven_para_calcular_areas;


/**
 * Librerias JUNIT
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;




//Pruebas Unitarias

public class AppTest 
{
	
	//Pruebas para calcular el area de un circulo
	
	@Test
	public void testAreaCirculo1() 
	{
		assertTrue(App.circulo(10.0) == 5.0);
	}
	
	@Test
	public void testAreaCirculo2() 
	{
		assertTrue(App.circulo(-6.0) == -3.00 );
	}
	
	@Test
	public void testAreaCirculo3() 
	{
		assertEquals(App.circulo(2.0), 1.0 , 0.0001);
	}
	
	//Pruebas para calcular el area de un cuadrado
	
	@Test
	public void testAreaCuadrado1() 
	{
		assertEquals(App.cuadrado(10.00, 10.00), 100.00, 0.0001);
	}
  
	@Test
	public void testAreaCuadrado2() 
	{
		assertNotNull(App.cuadrado(-5.00, -5.00));
	}
	
	@Test
	public void testAreaCuadrado3() 
	{
		assertFalse(App.cuadrado(-8.00, 5.00) == 40.00);
	}
	
	//Pruebas para calcular el area de un rectangulo
	
	@Test
	public void testAreaRectangulo1() 
	{
		assertTrue(App.rectangulo(4.00, 2.00) == 8.00);
	}
	
	@Test
	public void testAreaRectangulo2() 
	{
		assertEquals(App.rectangulo(0.0, 0.0),0.0, 0.00001);
	}
	
	@Test
	public void testAreaRectangulo3() 
	{
		assertEquals(App.rectangulo(0.0, 0.0),0.0, 0.00001);
	}
	
	//Pruebas para calcular el area de un triangulo
	
	@Test
	public void testAreaTriangulo1() 
	{
		assertEquals(App.triangulo(10.0, 20.00), 100.0, 0.00001);
	}
	
	@Test
	public void testAreaTriangulo2() 
	{
		assertTrue(App.triangulo(8.0, 6.00) == 24.0);
	}
	
	@Test
	public void testAreaTriangulo3() 
	{
		assertEquals(App.triangulo(9.0, 9.0), 40.5, 0.00001);
	}
	
	@Test
	public void testAreaTriangulo4() 
	{
		assertEquals(App.triangulo(1.0, 9.0), 4.5, 0.0001);
	}

	
}
