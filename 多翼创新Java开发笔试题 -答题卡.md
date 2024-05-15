### 笔试规则：
- 使用Java语法完成，运行环境为JDK11
- 开卷，时间不应超过一个工作日
- 建议将代码新建一个remote repo关联，并提交到 master 分支
- 推荐使用commit和markdown 记录和分享笔试过程的思考

## 1. 自定义类型实现一个二叉树，API包含插入、查询、删除操作的定义

 答案位置：test1.BinaryTree.main

## 2. 给定一个整数集合与一个目标值，找出集合中和为目标值的两个数，定义并实现该方法

  答案位置：test2.TwoSum.main
## 3. 从多个网页爬取内容，然后进行解析
有10个网页，1.txt 到 10.txt
http://dy-public.oss-cn-shenzhen.aliyuncs.com/interviewTestData/1.txt
```xml
<abc> <def>data : 1992; name : agent 1 </def></abc>
```
0. 格式有可能有区别
1. 分别实现并发、串行爬取
2. 使用正则表达式获取data后面的字段，将10个文件的data值全部获取，并相加

答案位置：test3.ReadURLContent#main（串行）
         test3.ReadURLContentConcurrent#main（并发）
## 4.最短环路算法
```
欧几里得平面坐标系XoY上有n个点（3<=n<=8),把他们连成一个环，要求这个环的边长之和是所有连接成环的方式中最短的，输出这个最短值

例如：
n=4
点1:（0, 1）
点2: (1, 0)
点3:（1, 1)
点4:（0，0）
那么 1-3-2-4-1 这种方式连成正方形是最优的解，答案为1+1+1+1=4
```
答案位置：test4.ShortestCycleLength.main