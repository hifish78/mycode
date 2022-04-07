package andy;

import java.io.*;
import java.util.StringTokenizer;

public class PromotionCounting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("promote.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // read from console
        StringTokenizer st = new StringTokenizer(br.readLine());
        int b0 = Integer.parseInt(st.nextToken()); // b0 is before contest
        int b1 = Integer.parseInt(st.nextToken()); //b1 is after contest

        st = new StringTokenizer(br.readLine());
        int s0 = Integer.parseInt(st.nextToken()); // s0 is before contest
        int s1 = Integer.parseInt(st.nextToken()); // s1 is after contest

        st = new StringTokenizer(br.readLine());
        int g0 = Integer.parseInt(st.nextToken()); // g0 is before contest
        int g1 = Integer.parseInt(st.nextToken()); //g1 is after contest

        st = new StringTokenizer(br.readLine());
        int p0 = Integer.parseInt(st.nextToken()); // p0 is before contest
        int p1 = Integer.parseInt(st.nextToken()); // p1 is after contest

        int g2p = p1 - p0;
        int s2g = g1 - g0 + g2p;
        int b2s = s1 - s0 + s2g;
//        System.out.println(g2p);
//        System.out.println(s2g);
//        System.out.println(b2s);
        pw.println(g2p);
        pw.println(s2g);
        pw.println(b2s);
        pw.close();
    }
}
