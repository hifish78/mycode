package flexport;

import java.util.List;

//get the cross product of the sequential input edge a, b as tmp, then:
//
//        if tmp == 0, a -> b 180° on the same line;
//        elif tmp > 0, a -> b clockwise;
//        else tmp < 0, a -> anticlockwise;


public class Convex {
    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        long pre = 0;
        long cur = 0;
        for (int i = 0; i < n; i++) {
            int dx1 = points.get((i + 1) % n).get(0) - points.get(i).get(0);
            int dx2 = points.get((i + 2) % n).get(0) - points.get(i).get(0);
            int dy1 = points.get((i + 1) % n).get(1) - points.get(i).get(1);
            int dy2 = points.get((i + 2) % n).get(1) - points.get(i).get(1);

            cur = dx1 * dy2 - dx2 * dy1;
            if (cur != 0) {
                if (cur * pre < 0) {
                    return false;
                } else {
                    pre = cur;
                }
            }
        }
        return true;
    }
}
