package walmart;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * https://leetcode.com/problems/design-hashmap/
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * https://leetcode.com/problems/longest-common-prefix/
 * https://leetcode.com/problems/number-of-digit-one/ ??
 * https://leetcode.com/submissions/detail/575565434/
 *
 * 给定一个01矩阵，把所有不和边界相连的1联通块都变成0.
 * 普通DFS就行，可能是基础不扎实写的不够顺利吧。
 *
 * coding 问 在无限重复字串里找 前n个字元中 target char 的出现次数
 * 他不喜欢我的做法，没等我写完，问我会不会 java stream，然后改规格
 * 1091
 *  介绍项目经历 + coding: 类似于蠡口幺玲玖幺，多了一个时间参数，问是否能在这个时间内到达目的地
 *
 *  VO1: 白人女生，自我介绍 + 一点简历 + 利口伞司令 (340)，口述brute force,优化成Map + sliding window
 *
 * VO2: 姻弟大哥，利口儿，讲一遍思路，十分钟做完，做的过程中发现大哥已经mute + 关摄像头了，说话完全没有feedback。。。。。 礼貌吗
 *          可怕的事情来了，问我用java多吗，我说不多，上学时候用过，工作两年多全是python，大哥说好，
 * 那你给我实现一下Java里的HashMap吧，凭借记忆聊了聊什么hash完了mod到bucket里，解决了一下hash collision和resize，讲了一下红黑树。
 * 我以为到这里就结束了，结果大哥开始问如果我改写了equals() 和 hashcode() 两个function 怎么办，我问他key是啥，他说key一样，
 * 我说key一样不就hash到同一个bucket里了么overwrite了。问到最后才明白，大哥意思是这样生成object之后hash出的key才能一样。你不早说key是object，
 * 和我说key就是id。。。 估计是想套这个壳 https://www.techiedelight.com/ho ... shmap-hashset‍‌‌‌‍‌‌‍‍‍‌‌‌‍‍‍‍‍‌-java/
 *
 * 电：利口儿溢嘶变种 写了brute force，让optimize, 写了pruning，让继续optimize，口述了KMP （校友见校友，背后插两刀。。。）
 * 昂赛1 系统设计，很简单的API，DB和一些安全相关的问题
 * 昂赛2 给两个字符串列，返回一个列表，里面是第一个列表的每个字符串是不是start with第二个列表里每个字符串，写了个brute force，优化成了trie
 * 昂赛3 BQ
 * 昂赛4 又给了店面的题，听说做过换了一道TopK，给了Heap解聊了一下时间复杂度
 *
 * 终于开了hackerrank  是lc 刘就其
 *
 * １. LC 233，解释题目搞了半天，最后用了个暴力解，没想出最优解。
 * 2. Compare binary tree，这个比较简单。
 * VO:
 * １. 一个越南人，他说他以前在加拿大做教授。问了些基础问题，然后写了个题，找match substring，我说了几种方法，他说暴力解就行。
 * ２. 老中，问了几个behavior, 然后　LC 20，但是不能用stack, 必须用recursive方法做，搞一个小时没搞出来‍‌‌‌‍‌‌‍‍‍‌‌‌‍‍‍‍‍‌。。。
 * 3. 老印，听说前面几轮写了码后，他只问了behavior.
 *
 * 3. Leetcode 中等难度题
 * 给一个数组，每次能对n-1个数字进行加一操作，问多少‍‌‌‌‍‌‌‍‍‍‌‌‌‍‍‍‍‍‌次之后能让数组的数字完全相同
 *
 * LC332 類似題，更簡單些
 * 給了幾組 [start, destination] 的pair, 叫你找出唯一可以用完所有機票的路徑
 *
 * 面試官是兩位別組的中國人，問了下簡歷，要我說說hashmap的implementation
 *     然後是兩道算法題:
 *     LC124 簡單版，path只需要找root到leaf的就行
 *     LC138 我寫的是 O(N) space的做法
 *
 * 三哥，闲聊了几句然后做题。题目比较恶心，舞丝舞。之前做过但还是有小bug，
 *
 * 1.coding
 * 题目：给一串字符like“wwwbbbww”
 * wendy和bob轮流拿走w或者b，仅当这个字母有相邻的相同字母时
 * 如果轮到w的时候，没有w可以拿了，那bob就赢了
 * 输出赢的人名
 *
 * 刷题网： 伞
 *
 * 一小时技术店面和HM 上来问了些BQ， 之后让写一个Singleton class follow up 是 how to do lazy instantiation没答出来。。
 * 原本singleton就面到了我的死穴。。后来是做了Merge K List. 做完之后又口述了下ha‍‌‌‌‍‌‌‍‍‍‌‌‌‍‍‍‍‍‌shmap的implementation。感觉第一轮电面考的还挺多的。求加米！
 *
 * 9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 * 没有用双指针 直接选择用reve‍‌‌‌‍‌‌‍‍‍‌‌‌‍‍‍‍‍‌rse integer的方法
 *
 * 国人小哥，全程中文交流，突然切换回来感觉挺亲切。 简单聊了下背景，题也很简单，但是要求用java。给定有序数组，target和k，要求找出最接近target的k个数按差值排序。如果差相同小的在前。[1,4,5,7,9], target = 8, k = 3
 * [7,9,5]
 * 说了可以binary search 可以heap，没说two pointer也没问。
 * 但是因为写的不熟练所以磕磕绊绊而且少考虑了一个边界条件。感觉这么简单的‍‌‌‌‍‌‌‍‍‍‌‌‌‍‍‍‍‍‌题没写好着实有点不妙……
 */
public class Note {
}
