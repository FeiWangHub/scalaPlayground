object ScalaList {

  // https://www.runoob.com/scala/scala-lists.html
  def main(args: Array[String]): Unit = {
    //testCreateList();
    //    testCreateByFillTabulate()
    testListOperation()
  }

  /**
   * Scala 列表类似于数组，它们所有元素的类型都相同，但是它们也有所不同：列表是不可变的，值一旦被定义了就不能改变
   * 其次 列表具有递归的结构（也就是链接表结构）而数组不是
   */
  def testCreateList(): Unit = {
    // 字符串列表
    val site: List[String] = List("Runoob", "Google", "Baidu")
    //val site = "Runoob" :: ("Google" :: ("Baidu" :: Nil))

    // 整型列表
    val nums: List[Int] = List(1, 2, 3, 4)
    //val nums = 1 :: (2 :: (3 :: (4 :: Nil)))

    // 空列表
    val empty: List[Nothing] = List()
    //val empty = Nil

    // 二维列表
    val dim: List[List[Int]] =
      List(
        List(1, 0, 0),
        List(0, 1, 0),
        List(0, 0, 1)
      )
    //val dim = (1 :: (0 :: (0 :: Nil))) ::
    //          (0 :: (1 :: (0 :: Nil))) ::
    //          (0 :: (0 :: (1 :: Nil))) :: Nil
  }

  /**
   * 1. 你可以使用 List.fill() 方法来创建一个指定重复数量的元素列表
   * 2. List.tabulate() 方法是通过给定的函数来创建列表。
   * 方法的第一个参数为元素的数量，可以是二维的，第二个参数为指定的函数，我们通过指定的函数计算结果并返回值插入到列表中，起始值为 0
   */
  def testCreateByFillTabulate(): Unit = {
    // 使用fill方法
    val siteFill = List.fill(3)("Runoob") // 重复 Runoob 3次
    println("siteFill : " + siteFill)

    val numFill = List.fill(10)(2) // 重复元素 2, 10 次
    println("numFill : " + numFill)

    // 使用tabulate
    // 通过给定的函数创建 5 个元素
    val squares = List.tabulate(6)(n => n * n)
    println("一维 : " + squares)

    // 创建二维列表
    val mul = List.tabulate(4, 5)(_ * _)
    println("多维 : " + mul)
  }

  /**
   * Scala列表有三个基本操作：
   * head 返回列表第一个元素
   * tail 返回一个列表，包含除了第一元素之外的其他元素
   * isEmpty 在列表为空时返回true
   * 你可以使用 ::: 运算符或 List.:::() 方法或 List.concat() 方法来连接两个或多个列表
   * List.reverse 用于将列表的顺序反转
   */
  def testListOperation(): Unit = {
    val site = "Runoob" :: ("Google" :: ("Baidu" :: Nil))
    val nums = Nil

    println("第一网站是 : " + site.head)
    println("tail网站是 : " + site.tail)
    println("查看列表 site 是否为空 : " + site.isEmpty)
    println("查看 nums 是否为空 : " + nums.isEmpty)

    // 使用 ::: 运算符连接链表
    val site2 = "Facebook" :: ("Taobao" :: Nil)
    var fruit = site ::: site2
    println("site ::: site2 连接结果 : " + fruit)

    // 使用 concat 方法
    fruit = List.concat(site, site2)
    println("List.concat(site1, site2) : " + fruit)

    // 翻转
    println("site 反转后 : " + site.reverse)
  }

}
