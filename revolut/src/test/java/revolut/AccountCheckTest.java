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
import com.revolut.money.transfer.entity.AccountDetail;
import com.revolut.money.transfer.service.AccountService;
import com.revolut.money.transfer.util.Result;

public class AccountCheckTest {

	 @Test
	 public void depositAccount() {
	       AccountService service=AccountService.getAccountService();
	       service.depositAccount(new BigDecimal(1), new BigDecimal(500));
	       Account account=service.getAccount(new BigDecimal(1));
	       System.out.println(account);
	       assertNotNull(account);
	 }
	 
	 
	 @Before
	 public void createAccounts() {
		 
		   AccountService service=AccountService.getAccountService();
	       service.depositAccount(new BigDecimal(1), new BigDecimal(500d));
	       service.depositAccount(new BigDecimal(2), new BigDecimal(1500d));
	       service.depositAccount(new BigDecimal(3), new BigDecimal(2500d));
	 }
	 
	 @Test
	 public void transferAccount() {
	       AccountService service=AccountService.getAccountService();
	       
	       Result result=service.transferToAccount(new BigDecimal(2), new BigDecimal(1), new BigDecimal(400d));
	       
	       System.out.println(result);
	       
	       Account toAccount=service.getAccount(new BigDecimal(2));
	       System.out.println("----------------------------------------------");
	       System.out.println(toAccount);
	       System.out.println("----------------------------------------------");
	       toAccount.getAccountDetails().forEach(acd->System.out.println(acd));
	       
	       System.out.println("----------------------------------------------");
	       
	       
	       Account fromAccount=service.getAccount(new BigDecimal(1));
	       System.out.println("----------------------------------------------");
	       System.out.println(fromAccount);
	       System.out.println("----------------------------------------------");
	       fromAccount.getAccountDetails().forEach(acd->System.out.println(acd));
		     
	       
	       assertTrue(toAccount.getAmount().doubleValue()==1900);
	       assertTrue(fromAccount.getAmount().doubleValue()==100);
	 }
	 
	 @After
	 public void testGetAllAccount() {
	       AccountService service=AccountService.getAccountService();
	       List<Account> accounts=service.getAllAccounts();
	       Collections.sort(accounts);
	       accounts.forEach(account->System.out.println(account));
	       assertNotNull(accounts);
	 }
}
