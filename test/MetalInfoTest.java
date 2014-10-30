package test;

import org.junit.Assert;
import org.junit.Test;

import com.bean.MetalInfo;

public class MetalInfoTest {
	MetalInfo metalInfo=new MetalInfo();
	
	@Test
	public void testCheckWhetherTheValueSetIsReceivedBack()  {
	String name="Ruby";
	int credits=19300, quantity=9;
	metalInfo.setCredits(credits); 
	metalInfo.setMetalName(name);
	metalInfo.setQuantity(quantity);
	Assert.assertEquals(metalInfo.getMetalName(),name);
	Assert.assertEquals(metalInfo.getCredits(),credits);
	Assert.assertEquals(metalInfo.getQuantity(), quantity);
	}
}
