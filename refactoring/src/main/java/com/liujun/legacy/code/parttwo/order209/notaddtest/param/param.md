## 9.1 令人恼火的参数

### 想知道能否在测试用具中实例化一个类
>
    要想知道能否在测试用例中实例化一个类，最佳途径就是试一试。编写一个测试用例并在里面创建该类的对象，
    编译器会告诉你要令代码工作起来还需要哪些东西。
>



```java


public class CreditValidator {

  private RGHConnection connection;

  private CreditMaster master;

  private String validatorId;

  /**
   * 测试计费系统
   *
   * @param connection
   * @param master
   * @param validatorId
   */
  public CreditValidator(RGHConnection connection, CreditMaster master, String validatorId) {
    this.connection = connection;
    this.master = master;
    this.validatorId = validatorId;
  }

  /**
   * 异常信息
   *
   * @param customer
   * @return
   * @throws InvalidCredit
   */
  Certificate validateCustomer(String customer) throws InvalidCredit {

    connection.connect();

    System.out.println(customer);

    connection.disconnect();

    return new Certificate(customer);
  }
}


public class CreditMaster {

  /**
   * @param filename
   * @param isLocal
   */
  public CreditMaster(String filename, boolean isLocal) {
    System.out.println(filename);
    System.out.println(isLocal);
  }
}

public class RGHConnection {

  /** 端口 */
  private int port;

  /** 用户名 */
  private String name;

  /** 密码 */
  private String passwd;

  public RGHConnection(int port, String name, String passwd) throws IOException {
    this.port = port;
    this.name = name;
    this.passwd = passwd;
  }

  public void connect() {
    // 模拟物理连接
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void disconnect() {
    // 模拟物理关闭
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  
}

public class CreditMaster {

  /**
   * @param filename
   * @param isLocal
   */
  public CreditMaster(String filename, boolean isLocal) {
    System.out.println(filename);
    System.out.println(isLocal);
  }
}


@Getter
@Setter
@ToString
public class Certificate {

  private String name;

  public Certificate(String name) {
    this.name = name;
  }
}


public class InvalidCredit extends Exception {

  public InvalidCredit(String message) {
    super(message);
  }
}


@Getter
@Setter
@ToString
public class Certificate {

  private String name;

  public Certificate(String name) {
    this.name = name;
  }
}


```


针对为类的代码，编写单元测试当然可以
```java

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
}

```

但进行物理连接是我们所需要的吗？
此时最简单的办法，是针对连接抽取一个接口，便可轻易的创建一个FakeConnection以便测试。
```java
public interface RGHConnectionInf {

  /** 连接方法 */
  void connect();

  /** 断开连接的方法 */
  void disconnect();
}


public class RGHConnection implements RGHConnectionInf {

  /** 端口 */
  private int port;

  /** 用户名 */
  private String name;

  /** 密码 */
  private String passwd;

  public RGHConnection(int port, String name, String passwd) throws IOException {
    this.port = port;
    this.name = name;
    this.passwd = passwd;
  }

  @Override
  public void connect() {
    // 模拟物理连接
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void disconnect() {
    // 模拟物理关闭
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  
}


public class FakeConnection implements RGHConnectionInf {

  @Override
  public void connect() {
    System.out.println("测试连接");
  }

  @Override
  public void disconnect() {
    System.out.println("断开连接 ");
  }
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

```


## 关于测试代码与产品代码

>
    1. 测试代码并不需要具有产品代码那么高的品质，一般来说，如果将变量设为公有能够测试的编写更为容易的话，
    我不介意这么做，尽管破坏了封装，不过，就算是测试代码，也应该干净，易于理解和修改的。
    看看上面的测试，有一部分是重复的，应当提取出来放到一个一个公共方法中，即类所属的setup方法。
>




