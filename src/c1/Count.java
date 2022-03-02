package c1;

//给一个数字 n, 算一下包括n在内 0, 2, and 4 出现了多少次. 举例：n = 10, solution(n)=4， ； n = 22， solution(n) = 11
public class Count {

    public int getCount(int n) {
        int res = 0;
        for (int i = 0; i <= n; i++) {
            int cnt = counting(i);
            res += cnt;
        }
        return res;
    }

    private int counting(int num) {
        // 0, 2, 4
        // 22
        int cnt = 0;
        String str = String.valueOf(num);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0' || str.charAt(i) == '2' || str.charAt(i) == '4') {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Count count = new Count();
        int res1 = count.getCount(10);
        System.out.println(res1);

        int res2  = count.getCount(22);
        System.out.println(res2);
    }
}
