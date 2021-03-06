#Extract Hierarchy (提练继承体系)

你有某个类做了太多的工作。其中一部分以大量条件表达式完成的。
建立继承体系，以一个子类表示一种特殊的情况。


##原因
在渐进式设计过程中，常常会有这样的情况：一开始一个设计者只希望以一个类实现一个概念；
但随着设计方案的演化，最后一个类实现两个、三个及至十个不同的概念。
一开始，你建立了这个简单的类。数天或数周之后，你可能发现：你可能发现，只要加放标识和一两个测试，就可以在另外一个环境中使用这个类
一月之后，你又发现了这样的机会，一年之后，这个类就一团糟了：标识变量和条件表达式遍布各大处。




##做法
存在两个做法
###1,手法1
1. 鉴别出一种情况变化。
2. 针对这种变化的情况，新建一个子类，并对原始类实施Replace Constructor With Factory method.再修改工厂函数，令他返回子类实例。
3. 将含有条件逻辑的函数，一次一个，逐一复制到子类，然后再明确的情况下，简化这些函数。
4. 重复上述过程。将所有变化情况都分离出来，直到可以将超类声明为抽象类火止。
5. 删除超类那些被子类覆写的函数本体，并将它声明为抽象函数。


###2，手法2
在非常清楚原始类会有哪些变化的情况
1. 针对原始类的每一个变化的情况，建立一个子类。
2. 使用Replace Constructor with Factor Method将原始类的构建函数转化成工厂函数，并令它针对每一种变化的情况返回适当的子类。
3. 针对带有条件逻辑的函数，实施Replace condition with polymorphism，
如果并非整个函数的行为都有变化，而只是一部有所变化，请先运用Extract Method将变化的部分与不变的部分隔开。


