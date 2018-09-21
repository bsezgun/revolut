package revolut;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.revolut.money.transfer.RevolutApp;
import com.revolut.money.transfer.controller.TransferController;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;

/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 *  Test
 * @since   2018-09-19
 */
public class DepositEuroAccountTest {

	@Before
	 public void setup() throws IOException {
		 RevolutApp.applicationProperties = new Properties();
		 ClassLoader classLoader = new RevolutApp().getClass().getClassLoader();
		 File file = new File(classLoader.getResource("application.properties").getFile());
		 FileInputStream in;
		 in = new FileInputStream(file);
		 RevolutApp.applicationProperties.load(in);
		 in.close();
	}
	
	
	 @Test
	 public void depositAccount() throws IOException {
		 TransferController transferController=new TransferController();
		 Result result=transferController.deposit(new BigDecimal(RevolutParams.ACCOUNT_TYPE_EURO), new BigDecimal(1), new BigDecimal(600));
		 assertTrue(result.getResultStatu().equals(RevolutParams.RESULT_STATU_SUCCESS));
	 }
}
