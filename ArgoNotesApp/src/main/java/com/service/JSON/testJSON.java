package com.service.JSON;

import com.example.UserApp.Objects.Account;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class testJSON extends TestCase {
    public void test1() throws IOException {
        Account person = new Account(UUID.randomUUID(),"Person1","Eric","DiGioacchino", "2021-1-1","some@email.com",1);
        JSON<Account> js = new JSON<Account>(person);
        System.out.println(js.toJSON());

        JSON<Account> js2 = new JSON<Account>("{\"uid\":\"a030be4d-0aa7-4fde-858e-0b94423c9a4b\",\"alias\":\"Person1\",\"first_name\":\"Eric\",\"last_name\":\"DiGioacchino\",\"dob\":\"2021-1-1\",\"email\":\"some@email.com\",\"role\":1}", Account.class);
        Account a = js2.fromJSON();
        System.out.println(a.getAlias());
        assertTrue(true);
    }
}
