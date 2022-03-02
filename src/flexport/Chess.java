package flexport;

/**
 * 题目是有一个checker棋盘，顶上都是黑棋，最底下都是白棋， 两个玩家轮流走棋，每一步黑棋只能走左下和右下，白棋只能走左上和右上。
 * 问如果给一个现在棋盘的state （state就是棋盘上现在所有棋的位置和轮到哪个玩家）， 返回all possible moves。
 * input, output和棋盘的state自己随便选data structure。。为了方便楼主棋盘就用了个2D char array， 然后定义了一个Coordinate类用来记录棋子的位置，
 * 然后用一个List在存一个start和end coordinate来表示一个possible move，最后返回一个List<List<Coordinate>>
 * 先说了个brute force solution给他，就遍历整个棋盘。。然后我说这个应该不是很好的解法，但他直接说他不是很在意最优解，写出来比较重要。
 * 15分钟写完了之后小哥说好，follow-up来优化一下 (因为lz写了4个if，他说可以简化成两个，因为可以不需要检查是白棋还是黑棋，
 * 楼主就define了个offset = +1/-1 来表示往上走还是往下走，搜索周围的时候就直接i+offset）小哥说不错，然后第二个follow-up是现在有一个新的棋子，叫国王，国王可以走任何方向，然后lz又加了个condition.
 * 做完‍‌‌‌‍‌‌‍‍‍‌‌‌‍‍‍‍‍‌之后小哥问我有没有问题，就问了问他实习做的工作之类的，听得出他对他做的东西很感兴趣， 然后讲着讲着刚好一个小时结束。
 */
public class Chess {
}
