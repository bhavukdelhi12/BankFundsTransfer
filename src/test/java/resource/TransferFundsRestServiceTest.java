package resource;

import com.banktransfer.application.BankTransferApplication;
import com.banktransfer.model.Account;
import com.banktransfer.resource.TransferFundsRestService;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BankTransferApplication.class)
public class TransferFundsRestServiceTest {
    private static Dispatcher dispatcher;
    private static Map<Integer, Account> accounts = new HashMap<>();

    @BeforeClass
    public static void setup() {
        dispatcher = MockDispatcherFactory.createDispatcher();
        POJOResourceFactory noDefaults = new POJOResourceFactory(TransferFundsRestService.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);

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

            MockHttpRequest request = MockHttpRequest.get("/transfer?from=0&to=1&amount=100");
            MockHttpResponse response = new MockHttpResponse();
            dispatcher.invoke(request, response);
            Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNegativeTransfer() {
        try {
            PowerMockito.mockStatic(BankTransferApplication.class);
            when(BankTransferApplication.getAccounts()).thenReturn(accounts);

            MockHttpRequest request = MockHttpRequest.get("/transfer?from=2&to=3&amount=100");
            MockHttpResponse response = new MockHttpResponse();
            dispatcher.invoke(request, response);
            Assert.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }
    }
}


