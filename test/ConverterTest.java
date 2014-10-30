package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

import com.processor.Converter;



public class ConverterTest {
Converter conveter =new Converter();
	@Test
	public void testDecodeFunctionWithOneCharacter()  {
		
		
		Assert.assertTrue(conveter.decode("I")==1);
		Assert.assertTrue(conveter.decode("X")==10);
	}
	
	@Test
	public void testDecodeFunctionWithTwoCharacters()  {
	
		
		
		Assert.assertTrue(conveter.decode("IX")==9);
		Assert.assertTrue(conveter.decode("XX")==20);
		Assert.assertTrue(conveter.decode("XI")==11);
		Assert.assertTrue(conveter.decode("DD")==-1);
		Assert.assertTrue(conveter.decode("XD")==-1);
	}
	@Test
	public void testDecodeFunctionWithMoreThanTwoCharacters()  {

		
	Assert.assertTrue(conveter.decode("IXI")==10);
		Assert.assertTrue(conveter.decode("XXX")==30);
	Assert.assertTrue(conveter.decode("XIL")==-1);
	Assert.assertTrue(conveter.decode("DDD")==-1);
		Assert.assertTrue(conveter.decode("XDV")==-1);
	Assert.assertTrue(conveter.decode("IVXXXX")==-1);
	Assert.assertTrue(conveter.decode("XILXXX")==-1);
	Assert.assertTrue(conveter.decode("XIVIIX")==24);
		Assert.assertTrue(conveter.decode("IVX")==-1);
	
	}
	
	
}
