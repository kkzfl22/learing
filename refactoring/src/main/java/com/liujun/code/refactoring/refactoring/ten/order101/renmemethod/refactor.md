#Rename Method(函数改名)
函数的名称未能揭示函数的用途，修改函数的名称。


##原因：
  作者推荐的一种编程风格：将复杂处理过程分解成小函数，但是如果做得不好，会使你费尽周折却弄不清楚这些小函数各自的用途，
要避免这种麻烦，关键在于给函数起一个好名字。函数的名称应该准确的表达它的用途。
给函数命名有一个好办法：首先考虑应该给这个函数写上一句怎么的注释，然后想办法将注释变成函数名称。
  生活就是如何，你常常无法第一次就给函数起一个好名称，这时候你可能会想：就这样将就着吧，毕竟只是一个名字而已。
当心，就是恶魔的召唤，是向通向混乱之路，千万别被它诱惑！如果看到一个函数不能很好地表达它的用途，应该马上加以修改。
请记住，代的代码首先是为人写的，其次才是为计算机写的。而人需要良好的名称函数，想想过去曾经浪费的时间。
如果给每一个函数都起一个良好的名称，也许你可以节约好多的时间。
起一个好名称并不容易，需要经验，想要成为一个真正的编程高手，起名的水平至关重要。

##做法
<li>1，检查函数签名是否被超类或子类实现过，如果是，则需要针对每份实现分别进行下列步骤。</li>
<li>2，声明一个新函数，将它命名为你想要的新名称。将旧函数的代码复杂到新函数中，并进行适当的调整。</li>
<li>3，编译</li>
<li>4,修改旧函数，令它将调用转发给新函数。</li>
<li>5,编译测试</li>
<li>6,找出旧函数的所有引用点，修改它们，令它们改而引用新函数，每次修改后，编译并测试</li>
<li>7,删除旧函数</li>

#src
```java
public class Telephone {

  private String officeAreaCode = String.valueOf(ThreadLocalRandom.current().nextLong());

  private String officeNumber = String.valueOf(ThreadLocalRandom.current().nextLong());;

  public String getTelephoneNumber() {
    return ("(" + officeAreaCode + ")") + officeNumber;
  }
}
```


#refactor
将函数改名为getOfficeTelephoneNumber
##1,建立一个新函数，命名为getOfficeTelephoneNumber(),将原来函数的内容复制过来，让旧函数调用新函数
```java
public class Telephone {

  private String officeAreaCode = String.valueOf(ThreadLocalRandom.current().nextLong());

  private String officeNumber = String.valueOf(ThreadLocalRandom.current().nextLong());;

  public String getOfficeTelephoneNumber() {
    return ("(" + officeAreaCode + ")") + officeNumber;
  }

  public String getTelephoneNumber() {
    return getOfficeTelephoneNumber();
  }
}

```
##2,找到旧函数的所有调用者，将它们全部改为调用新函数，全部修改完成后，就可以删除旧函数了

