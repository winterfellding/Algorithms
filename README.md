# Algorithms

### [Assignment 1 Percolation](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)
首先实现的是类似于Quick Find的算法

    Percolation包含几个属性
    1. sites    int[]       数组长度为小块数，即n*n 数组初始值为它的idx值
    2. isOpened boolean[]   数组长度为小块数, 初始值都为false, 代表都没打开
    3. n        int         边长, 用于index和row, col的互相转换计算
    4. openNum  int         打开的小块数, 方便numberOfOpenSites调用, 类似ArrayList中的size variable  


1. open方法做了几件事
    1. 更新isOpened数组, 记录小块是打开还是关闭状态
    2. 总共打开的数字加1
    3. 先看上方块是否打开，如果打开，就从上方获取上方块的值， 再看左中右，有打开的话，就把当前小块值赋给所有与之相同的块。
2. isOpen 很好实现, 取isOpened数组中对应的value
3. isFull 因为更新值都会根据上方数字，所以如果只要该小快小于边长 < n, 就是true
4. numberOfOpenSites 取openNum就可以知道
5. percolates 最后一行有isFull的就可以判定渗透了

