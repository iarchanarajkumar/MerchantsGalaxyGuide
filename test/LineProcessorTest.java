package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processor.LineProcessor;

public class LineProcessorTest {
	LineProcessor lineProcessor=new LineProcessor();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	  }

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    
	}
	@Test
	public void testMethodIdentifyLineType()  {
		
		lineProcessor.identifyLineType("rush is V");
		Assert.assertEquals("",outContent.toString());
		lineProcessor.identifyLineType("mush is X");
		lineProcessor.identifyLineType("mush rush Iron is 3910 Credits");
		Assert.assertEquals("",outContent.toString());
		lineProcessor.identifyLineType("how many Credits is mush rush Iron ?");
		Assert.assertNotNull(outContent.toString());
	
	
	}
	  @Test
	    public void testMain() throws IOException {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(baos));
	        lineProcessor.identifyLineType("how many Credits is mush rush Iron ?");
	        baos.flush();
	        String whatWasPrinted = new String(baos.toByteArray());
	        String[] linesOfOutput = whatWasPrinted.split(//
	                System.getProperty("line.separator"));
	       
	        Assert.assertEquals("ANSWER:I have no idea what you are talking about", linesOfOutput[0]);
	    }
}
