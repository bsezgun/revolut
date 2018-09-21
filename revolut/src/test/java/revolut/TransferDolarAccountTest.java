package revolut;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revolut.money.transfer.RevolutApp;
import com.revolut.money.transfer.controller.TransferController;
import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDolar;
import com.revolut.money.transfer.service.AccountService;
import com.revolut.money.transfer.service.DolarAccountService;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;

/**
 * 
 * @author bsezgun
 * @version v.1.0.1
 *  Test
 * @since   2018-09-19
 */
public class TransferDolarAccountTest {


	 
	 
	 @BeforeClass
	 public static void createAccounts() throws IOException {
		 RevolutApp.applicationProperties = new Properties();
		 ClassLoader classLoader = new RevolutApp().getClass().getClassLoader();
		 File file = new File(classLoader.getResource("application.properties").getFile());
		 FileInputStream in;
		 in = new FileInputStream(file);
		 RevolutApp.applicationProperties.load(in);
		 in.close();

		 AccountService service=new DolarAccountService();
		 service.depositAccount(new BigDecimal(1), new BigDecimal(500d));
		 service.depositAccount(new BigDecimal(2), new BigDecimal(1500d));
		 service.depositAccount(new BigDecimal(3), new BigDecimal(2500d));
	 }
	 
	 
	 @Test
	 public void depositAccount() throws IOException {
		 TransferController transferController=new TransferController();
		 Result result=transferController.deposit(new BigDecimal(RevolutParams.ACCOUNT_TYPE_DOLAR), new BigDecimal(1), new BigDecimal(600));
		 assertTrue(result.getResultStatu().equals(RevolutParams.RESULT_STATU_SUCCESS));
	 }
	 
	 /**
	  *  if current rules do not changed in the application.properties file .
	  *  than this test method have to return RevolutParams.RESULT_STATU_SUCCESS
	  *  
	  */
	 @Test
	 public void transferAccount() {
		 BigDecimal transferAmount=new BigDecimal(600d);
		 
		 TransferController transferController=new TransferController();
		 Result result=transferController.transfer(new BigDecimal(RevolutParams.ACCOUNT_TYPE_DOLAR), new BigDecimal(1), new BigDecimal(2), transferAmount);
		 
		 assertTrue(result.getResultStatu().equals(RevolutParams.RESULT_STATU_SUCCESS));
	 }
	 
	 /**
	  *  if current rules do not changed in the application.properties file .
	  *  than this test method have to return RevolutParams.RESULT_STATU_RESTRICTED
	  *  
	  */
	 @Test
	 public void transferAccountRestricted() {
		 BigDecimal transferAmount=new BigDecimal(400d);
		 
		 TransferController transferController=new TransferController();
		 Result result=transferController.transfer(new BigDecimal(RevolutParams.ACCOUNT_TYPE_DOLAR), new BigDecimal(1), new BigDecimal(2), transferAmount);
		 
		 assertTrue(result.getResultStatu().equals(RevolutParams.RESULT_STATU_RESTRICTED));
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	 @After
	 public void testGetAllAccount() {
	       AccountService service=new DolarAccountService();
	       List<? extends Account> accounts=service.getAllAccounts();
	       Collections.sort(((List<AccountDolar>)accounts));
	       accounts.forEach(account->System.out.println(account));
	       assertNotNull(accounts);
	 }
	
}
