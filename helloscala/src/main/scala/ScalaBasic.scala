import java.util.Date

object ScalaBasic {

  //Scala 访问修饰符基本和Java的一样，分别有：private，protected(只有子类访问)，public(默认)

  var myVar: String = "Foo" //变量
  val myVal: String = "Foo" // 常量
  val a: Int = 10

  var str = "自动推断类型"
  var xmax, ymax = 100 //多个变量
  var tuple = (40, "Foo") //元祖，并自动推断类型 等同于 pa: (Int, String) = (40,Foo)

  def main(args: Array[String]) {
    //println(tuple)
    //println("Hello\tWorld\n");
    //位运算符 ~,&,|,^ 分别为取反，按位与，按位或，按位异或运算

    //testLoop()
    //testMethodAndFunction(1, 2)
    //testString()
    //testArray()
  }

  //https://www.runoob.com/scala/scala-arrays.html
  def testArray(): Unit = {
    //声明数组
    var arr0: Array[String] = new Array[String](3)

    val arr1 = new Array[String](3)
    arr1(0) = "A";
    arr1(1) = "B";
    arr1(4 / 2) = "C"

    var arr2 = Array(1.9, 2.9, 3.4, 3.5)

    //处理数组
    // 输出所有数组元素
    println("输出数组所有元素")
    for (x <- arr2) {
      println(x)
    }

    // 计算数组所有元素的总和
    var total = 0.0;
    for (i <- 0 to (arr2.length - 1)) {
      total += arr2(i);
    }
    println("总和为 " + total);

    // 查找数组中的最大元素
    var max = arr2(0);
    for (i <- 1 to (arr2.length - 1)) {
      if (arr2(i) > max) max = arr2(i);
    }
    println("最大值为 " + max);

    //多维数组
    val myMatrix = Array.ofDim[Int](3, 3)

    // 创建矩阵
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        myMatrix(i)(j) = j;
      }
    }

    // 打印二维阵列
    println("开始打印多维数组")
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        print(" " + myMatrix(i)(j));
      }
      println();
    }

    //合并数组
    var myList1 = Array(1.9, 2.9, 3.4, 3.5)
    var myList2 = Array(8.9, 7.9, 0.4, 1.5)
    var myList3 = Array.concat(myList1, myList2)
    println("合并数组concat")
    // 输出所有数组元素
    for (x <- myList3) {
      print(x)
    }
    println("\n合并数组结束")

    //创建区间数组
    println("创建区间数组by range，第一个步长为2")
    var rangeArr = Array.range(10, 20)
    var rangeArr2 = Array.range(10, 20, 2)
    // 输出所有数组元素
    for (x <- rangeArr2) {
      print(" " + x)
    }
    println()
    for (x <- rangeArr) {
      print(" " + x)
    }

  }

  def testString(): Unit = {
    //格式化字符串
    var floatVar = 12.456
    var intVar = 2000
    var stringVar = "菜鸟教程!"
    var fs = printf("浮点型变量为 " + "%f, 整型变量为 %d, 字符串为 " + " %s", floatVar, intVar, stringVar)
    println(fs)
  }

  /**
   * Scala 有方法与函数，二者在语义上的区别很小。Scala 方法是类的一部分，而函数是一个对象可以赋值给一个变量。换句话来说在类中定义的函数即是方法。
   * Scala 中的方法跟 Java 的类似，方法是组成类的一部分。
   * Scala 中的函数则是一个完整的对象，Scala 中的函数其实就是继承了 Trait 的类的对象。
   * Scala 中使用 val 语句可以定义函数，def 语句定义方法。
   */
  def testMethodAndFunction(a: Int = 5, b: Int = 7): Unit = {
    //下边这是一个函数
    val function = (x: Int) => x + 3
    println(function(1));

    //传值调用（call-by-value）：先计算参数表达式的值，再应用到函数内部；
    //传名调用（call-by-name）：将未计算的参数表达式直接应用到函数内部 (在变量名和变量类型使用 => 符号来设置传名调用)
    //在进入函数内部前，传值调用方式就已经将参数表达式的值计算完毕，而传名调用是在函数内部进行参数表达式的值计算的。
    //这就造成了一种现象，每次使用传名调用时，解释器都会计算一次表达式的值。
    delayed(time())

    //可变长度的参数
    printStrings("第一个参数", "第一个参数", "第一个参数")

    //高阶函数
    println(higherOrderFunction(layout, 10))

    //嵌套函数
    def nestedFunc(): Unit = {
      println("这是嵌套函数 nestedFunc")
    }

    nestedFunc()

    //匿名函数
    val inc = (x: Int) => x + 1
    println("匿名函数inc: " + inc(1))

    //偏应用函数 Partial application functions
    //偏应用函数是一种表达式，你不需要提供函数需要的所有参数，只需要提供部分，或不提供所需参数
    //(重新封装了一下某函数，为了方便。第二个参数使用下划线(_)替换缺失的参数列表)
    val date = new Date
    val logWithDateBound = log(date, _: String)
    logWithDateBound("message1")
    logWithDateBound("message2")
    logWithDateBound("message3")

    //Currying 函数柯里化
    testCurrying()
  }

  // 柯里化(Currying)指的是将原来接受两个参数的函数变成新的接受一个参数的函数的过程。
  // 新的函数返回一个以原有第二个参数为参数的函数。
  def testCurrying(): Unit = {
    //原始函数
    def add(x: Int, y: Int) = x + y

    // 柯里化函数
    // add(1)(2) 实际上是依次调用两个普通函数（非柯里化函数），第一次调用使用一个参数 x，返回一个函数类型的值，第二次使用参数y调用这个函数类型的值。
    // 实质上最先演变成这样一个方法：def add(x:Int)=(y:Int)=>x+y
    def addCurrying(x: Int)(y: Int) = x + y

    println("Currying 函数柯里化 " + addCurrying(1)(2))
  }

  def time() = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }

  //传名调用，目测相当于传入了一个函数
  def delayed(t: => Long) = {
    println("在 delayed 方法内")
    println("参数： " + t)
    t
  }

  //可变长度参数
  def printStrings(args: String*) = {
    var i: Int = 0;
    for (arg <- args) {
      println("printStrings Arg value[" + i + "] = " + arg);
      i = i + 1;
    }
  }

  // 高阶函数可以使用其他函数作为参数，或者使用函数作为输出结果
  // 函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v
  def higherOrderFunction(f: Int => String, v: Int) = f(v)

  def layout[A](x: A) = "[" + x.toString() + "]"

  def log(date: Date, message: String) = {
    println(date + "----" + message)
  }

  def testLoop(): Unit = {
    //循环 Range
    //Range 可以是一个数字区间表示 i to j ，或者 i until j。左箭头 <- 用于为变量 x 赋值。
    println("循环Range")
    for (a <- 1 to 10) {
      println("Value of a: " + a);
    }
    //    a = 0;
    //    for (a <- 1 until 10) {
    //      println("Value of a: " + a);
    //    }

    //循环集合
    println("循环集合")
    var numList = List(1, 2, 3, 4, 5, 6);
    for (a <- numList) {
      println("Value of a: " + a)
    }

    //循环过滤
    println("循环过滤掉3和8以上")
    numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (a <- numList if a != 3; if a < 8) {
      println("Value of a: " + a)
    }

    /**
     * for循环使用yield
     * 你可以将 for 循环的返回值作为一个变量存储.
     * 注意大括号中用于保存变量和条件，yieldList 是变量， 循环中的 yield 会把当前的元素记下来，保存在集合中，循环结束后将返回该集合。
     */
    println("循环使用yield")
    numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val yieldList = for {a <- numList if a != 3; if a < 8} yield a
    for (v <- yieldList) {
      println("Value of yieldList: " + v)
    }
  }

}