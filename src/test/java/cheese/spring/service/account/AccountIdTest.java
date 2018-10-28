package cheese.spring.service.account;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountIdTest {

    @Test
    public void AccountID_Account_Id가_동일하면_동일_객체() {

        final String ACCOUNT_ID = getUUID();
        AccountId accountId1 = new AccountId(ACCOUNT_ID);
        AccountId accountId2 = new AccountId(ACCOUNT_ID);


        final boolean equals = accountId1.equals(accountId2);
        assertThat(equals, is(true));
    }


    @Test
    public void AccountID_Account_Id가_동일하지않으면_동일_객체() {

        AccountId accountId1 = new AccountId(getUUID());
        AccountId accountId2 = new AccountId(getUUID());

        final boolean equals = accountId1.equals(accountId2);
        assertThat(equals, is(false));
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }
}