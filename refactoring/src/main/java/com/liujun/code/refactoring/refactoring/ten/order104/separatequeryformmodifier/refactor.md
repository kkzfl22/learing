#Separate Query from Modifier(将查询函数与修改函数分离)

某个函数妈返回对象状态值，又修改对象状态。
建立两个不同的函数，其中一个负责查询，另一个负责修改。

##原因
如果一个函数只是向你提供一个值，没有任何看得到的副作用，那么这个是很有价值的东西，你可以任意调用这个函数，
也可以把调用动作搬到函数的其他地方。简单而言之，需要操心的事情少多了。

明确表现出“有副作用”和“无副作用”两种函数之彰的差异，是个很好的想法。
下面是一个好规则：
任何返回值的函数，都不应该有看得到的副作用，有些程序员将此作为一条必须遵守的规则，
就像你对待任何东西一样，我并不绝对遵守它，不过我总是尽量遵守，而它也回报我很好的效果。

##做法：
<ul>
<li>1,新建立一个查询函数，令它返回的值与原函数相同。</li>
<li>2,修改原函数，令它调用查询函数，并返回获得的效果。</li>
<li>3,编译，测试</li>
<li>4,将调用原函数的代码修改为调用查询函数，然后在调用查询函数的那一行之前，加上对原函数的调用。每次修改后，编译并测试。</li>
<li>5,将原函数的返回值，修改为void ，并删除其中的所有的return语句。</li>
</ul>


这样一个函数，一旦有人入侵安全系统，它会告诉我入侵者的名称，并发送一个警报，如果入侵者不止一个，也只发送一条警报。
##src
```java
public class DataSecurity {

  public String foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        sendAlert();
        return "DON";
      }
      if (people[i].equals("John")) {
        sendAlert();
        return "John";
      }
    }

    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  public void checkSecurity(String[] people) {
    String found = foundMiscreant(people);
    someLaterCode(found);
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}
```

##refactor:
1,为了将查询与修改动作分开，首先建立一个适当的查询函数，使其与修改函数返回相同的值，但不造成任何副作用。
```java
public class DataSecurity {

  public String foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        sendAlert();
        return "DON";
      }
      if (people[i].equals("John")) {
        sendAlert();
        return "John";
      }
    }

    return "";
  }

  private String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        return "DON";
      }
      if (people[i].equals("John")) {
        return "John";
      }
    }
    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  public void checkSecurity(String[] people) {
    String found = foundMiscreant(people);
    someLaterCode(found);
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}
```

2,修改原函数内的return语句改为调用新的查询，每次替换后执行编译并测试。
```java

public class DataSecurity {

  public String foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        sendAlert();
        return this.foundPerson(people);
      }
      if (people[i].equals("John")) {
        sendAlert();
        return this.foundPerson(people);
      }
    }
        
    return this.foundPerson(people);
  }

  private String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        return "DON";
      }
      if (people[i].equals("John")) {
        return "John";
      }
    }
    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  public void checkSecurity(String[] people) {
    String found = foundMiscreant(people);
    someLaterCode(found);
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}
```

3,修改调用者，将原本单一的调用动作替换为两个调用，先调用修改函数，然后调用查询函数。
```java
public class DataSecurity {

  public String foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        sendAlert();
        // return语句改为调用函数
        return this.foundPerson(people);
      }
      if (people[i].equals("John")) {
        sendAlert();
        // return语句改为调用函数
        return this.foundPerson(people);
      }
    }
    // return语句改为调用函数
    return this.foundPerson(people);
  }

  private String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        return "DON";
      }
      if (people[i].equals("John")) {
        return "John";
      }
    }
    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  public void checkSecurity(String[] people) {
    foundMiscreant(people);
    //原原单一调用修改为调用两个函数
    String found = this.foundPerson(people);
    someLaterCode(found);
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}

```

4，所有调用修改完毕后，可以将修改函数的返回改为void
```java
public class DataSecurity {

  public void foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        sendAlert();
        //返回改为void
        return;
      }
      if (people[i].equals("John")) {
        sendAlert();
        //返回改为void
        return;
      }
    }
    //返回改为void
    return;
  }

  private String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        return "DON";
      }
      if (people[i].equals("John")) {
        return "John";
      }
    }
    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  public void checkSecurity(String[] people) {
    foundMiscreant(people);
    //原原单一调用修改为调用两个函数
    String found = this.foundPerson(people);
    someLaterCode(found);
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}

```

5,重构，修改foundMiscreant的函数名，可以为sendAlert
```java
public class DataSecurity {

  public void sendAlert(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        sendAlert();
        //返回改为void
        return;
      }
      if (people[i].equals("John")) {
        sendAlert();
        //返回改为void
        return;
      }
    }
    //返回改为void
    return;
  }

  private String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        return "DON";
      }
      if (people[i].equals("John")) {
        return "John";
      }
    }
    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  public void checkSecurity(String[] people) {
    sendAlert(people);
    //原原单一调用修改为调用两个函数
    String found = this.foundPerson(people);
    someLaterCode(found);
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}
```

6，代码中存在一些重复的代码，修改函数实现，让它再简洁一些
```java

public class DataSecurity {

  public void sendAlert(String[] people) {
    if (!"".equals(foundPerson(people))) {
      sendAlert();
    }
  }

  private String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
      if (people[i].equals("DON")) {
        return "DON";
      }
      if (people[i].equals("John")) {
        return "John";
      }
    }
    return "";
  }

  private void sendAlert() {
    System.out.println("alert");
  }

  public void checkSecurity(String[] people) {
    sendAlert(people);
    // 原原单一调用修改为调用两个函数
    String found = this.foundPerson(people);
    someLaterCode(found);
  }

  public void someLaterCode(String found) {
    System.out.println("found:" + found);
  }
}
```

