package com.liujun.legacy.code.parttwo.order209.notaddtest.param.base;

import com.liujun.legacy.code.parttwo.order209.notaddtest.param.fake.FakeConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestCreditValidator {

  private static final int DEFAULT_PORT = 8061;

  @Test
  public void testCreate() throws IOException, InvalidCredit {
    RGHConnection connection = new RGHConnection(DEFAULT_PORT, "admin", "admin.123");
    CreditMaster master = new CreditMaster("crm.mas", true);
    CreditValidator validator = new CreditValidator(connection, master, "a");

    String name = "Name";

    Certificate info = validator.validateCustomer(name);

    assertEquals(info.getName(), name);
  }


  @Test
  public void testCreateFake() throws IOException, InvalidCredit {
    RGHConnectionInf connection = new FakeConnection();
    CreditMaster master = new CreditMaster("crm.mas", true);
    CreditValidator validator = new CreditValidator(connection, master, "a");

    String name = "Name";

    Certificate info = validator.validateCustomer(name);

    assertEquals(info.getName(), name);
  }

}
