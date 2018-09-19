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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.money.transfer.RevolutApp;
import com.revolut.money.transfer.controller.TransferController;
import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDolar;
import com.revolut.money.transfer.service.AccountTypes;
import com.revolut.money.transfer.service.DolarAccountService;
import com.revolut.money.transfer.util.Result;
import com.revolut.money.transfer.util.RevolutParams;

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

		 AccountTypes service=new DolarAccountService();
		 service.depositAccount(new BigDecimal(1), new BigDecimal(500d));
		 service.depositAccount(new BigDecimal(2), new BigDecimal(1500d));
		 service.depositAccount(new BigDecimal(3), new BigDecimal(2500d));
	 }
	 
	 
	 @Test
	 public void depositAccount() throws IOException {
		 TransferController transferController=new TransferController();
		 String dpc=transferController.deposit(new BigDecimal(RevolutParams.ACCOUNT_TYPE_DOLAR), new BigDecimal(1), new BigDecimal(600));
		 ObjectMapper mapper = new ObjectMapper();
		 Result result=mapper.readValue(dpc, Result.class);
		 assertTrue(result.getResultStatu().equals(RevolutParams.RESULT_STATU_SUCCESS));
	 }
	 
	 
	 @Test
	 public void transferAccount() throws IOException {
		 TransferController transferController=new TransferController();
		 String tr1=transferController.transfer(new BigDecimal(RevolutParams.ACCOUNT_TYPE_DOLAR), new BigDecimal(1), new BigDecimal(2), new BigDecimal(600));
		 ObjectMapper mapper = new ObjectMapper();
		 Result result=mapper.readValue(tr1, Result.class);
		 assertTrue(result.getResultStatu().equals(RevolutParams.RESULT_STATU_SUCCESS));
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	 @After
	 public void testGetAllAccount() {
	       AccountTypes service=new DolarAccountService();
	       List<? extends Account> accounts=service.getAllAccounts();
	       Collections.sort(((List<AccountDolar>)accounts));
	       accounts.forEach(account->System.out.println(account));
	       assertNotNull(accounts);
	 }
	
}
