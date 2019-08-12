package impl;

import com.banktransfer.application.BankTransferApplication;
import com.banktransfer.model.Account;
import com.banktransfer.service.impl.SavingTransferFundsImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BankTransferApplication.class)
public class SavingTransferFundsImplTest {

    private static Map<Integer, Account> accounts = new HashMap<>();
    private static SavingTransferFundsImpl savingTransferFundsImpl;

    @BeforeClass
    public static void setup() {
        savingTransferFundsImpl = new SavingTransferFundsImpl();
        /**
         * Sample accounts for testing
         */
        Account account1 = new Account(12345);
        account1.setBalance(500);
        accounts.put(0, account1);

        Account account2 = new Account(12345);
        account1.setBalance(500);
        accounts.put(1, account2);
    }

    @Test
    public void testPositiveTransfer() {
        try {
            PowerMockito.mockStatic(BankTransferApplication.class);
            when(BankTransferApplication.getAccounts()).thenReturn(accounts);
            savingTransferFundsImpl.transferFunds(0, 1, 200);
            Account sampleAccount = accounts.get(0);
            Assert.assertEquals(accounts.get(0).getBalance(), 300, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = Exception.class)
    public void testNegativeTransfer() throws Exception {
        PowerMockito.mockStatic(BankTransferApplication.class);
        when(BankTransferApplication.getAccounts()).thenReturn(accounts);
        savingTransferFundsImpl.transferFunds(0, 1, 600);
        Account sampleAccount = accounts.get(0);
    }
}
