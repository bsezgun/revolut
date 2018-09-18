package revolut;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.entity.AccountDolar;
import com.revolut.money.transfer.service.AccountTypes;
import com.revolut.money.transfer.service.DolarAccountService;
import com.revolut.money.transfer.util.Result;

public class AccountCheckTest {

	 @Test
	 public void depositAccount() {
		   AccountTypes service=new DolarAccountService();
	       service.depositAccount(new BigDecimal(1), new BigDecimal(500));
	       Account account=service.getAccount(new BigDecimal(1));
	       System.out.println(account);
	       assertNotNull(account);
	 }
	 
	 
	 @Before
	 public void createAccounts() {
		   AccountTypes service=new DolarAccountService();
	       service.depositAccount(new BigDecimal(1), new BigDecimal(500d));
	       service.depositAccount(new BigDecimal(2), new BigDecimal(1500d));
	       service.depositAccount(new BigDecimal(3), new BigDecimal(2500d));
	 }
	 
	 @Test
	 public void transferAccount() {
		   AccountTypes service=new DolarAccountService();
	       
	       Result result=service.transferToAccount(new BigDecimal(2), new BigDecimal(1), new BigDecimal(400d));
	       
	       System.out.println(result);
	       
	       AccountDolar toAccount=(AccountDolar)service.getAccount(new BigDecimal(2));
	       System.out.println("----------------------------------------------");
	       System.out.println(toAccount);
	       System.out.println("----------------------------------------------");
	       toAccount.getAccountDetails().forEach(acd->System.out.println(acd));
	       
	       System.out.println("----------------------------------------------");
	       
	       
	       AccountDolar fromAccount=(AccountDolar)service.getAccount(new BigDecimal(1));
	       System.out.println("----------------------------------------------");
	       System.out.println(fromAccount);
	       System.out.println("----------------------------------------------");
	       fromAccount.getAccountDetails().forEach(acd->System.out.println(acd));
		     
	       
	       assertTrue(toAccount.getAmount().doubleValue()>0);
	       assertTrue(fromAccount.getAmount().doubleValue()>0);
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
