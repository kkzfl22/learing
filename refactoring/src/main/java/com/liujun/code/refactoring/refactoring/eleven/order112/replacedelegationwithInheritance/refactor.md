#Replace Delegation with Inheritance (以继承取代委托)


你在两个类这间使用委托关系，并经常为整个接口。
编写许多极简单的委托函数，让委托类继承受托类。

#原因
原则：
1，如果你并没有使用受托类的所有函数，那么就不应该使用Replace Delegation with Inheritance (以继承取代委托),因为子类总是遵循超类的接口。
2，过多的委托函数让你烦心，你有别的选择，可以通过Remove Midden Man 让客户端自己调用受托类，或者 Extract SuperClass将接口接口提练到超类中。
让两个类都继承这个新的超类，你还可以用类似的手法使用Extract Interface。

另一种需要当心的的情况：
受托对象被不止一个其他对象共享，而且受托对象是可变的。


##W做法
<ul>
    <li>1,让委托端成为受托端的一个子类。</li>
    <li>2,编译</li>
    <li>3,让受托字段设为该字段所处对象本身。</li>
    <li>4,去除所有简单的委托函数。</li>
    <li>5,编译并测试。</li>
    <li>6,交所有涉及委托关系的代码，改为调用对象本身。</li>
    <li>7,移除受托对象本身。</li>
</ul>