package local.soapuitest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.support.SoapUIException;
import com.eviware.soapui.support.types.StringToObjectMap;

public class SoapUIOpenSourceTest {
	// create new project
	WsdlProject project;
	WsdlTestSuite testSuite;
	WsdlTestCase testCase;
	WsdlTestCaseRunner runner;
	
	
	
	@BeforeEach
	public void setup () throws XmlException, IOException, SoapUIException {
		project = new WsdlProject("src/test/resources/REST-Test-Project-project-OK-KO.xml");
		testSuite = project.getTestSuiteByName("TestSuite 1");
	}
	
	
	@Test
	public void testKO( ) {
		
		testCase = testSuite.getTestCaseByName("Test_KO");
		
		//name of the property in SoapUI
		String propertySoap = "value";
		//get the parameter from SquashTM to pass the value to SoapUI
		String value = "202";
		//pass the value to the runner of the SoapUI test
		testCase.setPropertyValue(propertySoap, value);
		
		//another possibility to launch the runner
		//runner = new WsdlTestCaseRunner(testCase, properties)
		
		boolean async = false;
		runner = testCase.run(new PropertiesMap(), async);
		
		assertEquals(Status.FINISHED, runner.getStatus());
		
	}
	@Test
	public void testOK( ) {
		
		testCase = testSuite.getTestCaseByName("Test_OK");
		
		boolean async = false;
		runner = testCase.run(new PropertiesMap(), async);
		
		assertEquals(Status.FINISHED, runner.getStatus());
		
	}
	

}
