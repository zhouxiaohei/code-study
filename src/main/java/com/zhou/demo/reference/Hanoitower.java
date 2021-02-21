package com.zhou.demo.reference;

/**
 * @ClassName Hanoitower
 * @Author JackZhou
 * @Date 2020/9/4  10:44
 * @desc   汉诺塔：汉诺塔（又称河内塔）问题是源于印度一个古老传说的益智玩具。
 * 大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺序
 * 摞着64片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。
 * 并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘
 **/
public class Hanoitower {

    static int count = 0;

    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
        System.out.println("一共执行了" + count + "次");
    }

    //汉诺塔的移动的方法    10之前N平方~N立方  20以后N的4~5次方
    //使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        count++;
        //如果只有一个盘
        if(num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的一个盘 2. 上面的所有盘
            //1. 先把 最上面的所有盘 A->B， 移动过程会使用到 c
            hanoiTower(num - 1, a, c, b);
            //2. 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3. 把B塔的所有盘 从 B->C , 移动过程使用到 a塔
            hanoiTower(num - 1, b, a, c);

        }

        //一般性原理拆解 A B C都是相对位子
        //1. 先把 最上面的所有盘 A->B， 移动过程会使用到 c
        // 将n-1从A->B （A B C排列就是循环一次变成A C B）实际上是A->C； 将n-2从A->B （A C B排列就是循环一次变成A B C）实际上是A->B；
        // 一直到1，将第一次从A移动到C  可能是A->B也可能是A->C 看具体循环了几层
        //2. 把最下边的盘 A->C
        // N-1最下面的盘是N-2 ; N-3  一直到第二个盘从A->C，当然同理可能是A->C也可能是A->B
        // 3. 把B塔的所有盘 从 B->C , 移动过程使用到 a塔
        //将B塔N-1的盘，从B->C 有可能是B->C也有可能C->B ,同理从第一个开始移动；

        /**
         第1个盘从 A->C
         第2个盘从 A->B
         第1个盘从 C->B
         第3个盘从 A->C
         第1个盘从 B->A
         第2个盘从 B->C
         第1个盘从 A->C
         **/
        // 步骤拆解
        // n==2的第一次循环  1到C  2到B  1到B
        // n==3的第二次循环  3到C  1到A   2到C   1到A

        //理论步骤拆解
        // n个盘  n=2 第一次循环 1可以到C或者B  2就到B或者C  然后1到B或者C
        //  n=3 第二次循环就从3开始，因为 1 2都排列好了。3先到除了1、2以外的C或者B
        //   步骤三执行   然后1移动到A  2移动到C  1再移动到C；第二次循环完成，1-2-3个元素都到了C或者B
        // 步骤三相当于执行了一次n=2的盘移动，将1 和2移动到C
        //以此往复  最终求解   分解以后倒叙求解
    }

}
