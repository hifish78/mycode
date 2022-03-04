package flexport;
class Reader4 {
    int read4(char[] buf) {
        return 1;
    }
}

public class Read4K extends Reader4 {
        private int curIndex = -1;
        private char[] curbuf = new char[4];
        private int curLen = -1 ;
        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */
        public int read(char[] buf, int n) {
            int index = 0;
            while(n > 0 ){
                while((n > 0 ) && (curIndex < curLen)){
                    buf[index++] = curbuf[curIndex++];
                    n--;
                }
                if((curLen >=0 ) && (curLen < 4)){
                    break;
                }
                if(curIndex == curLen){
                    curLen = read4(curbuf);
                    curIndex = 0;
                }
            }
            return index;
        }

    public int read2(char[] buf, int n) {

        boolean eof = false;
        int cnt = 0;
        char[] buf4 = new char[4];

        while (!eof && cnt < n) {
            int p = read4(buf4);

            //两种情况下读文件结束： p < 4 或者剩余字符个数 < 4
            if (p < 4 || n - cnt < 4) {
                eof = true;
            }

            p = Math.min(p, n - cnt);

            for (int i = 0; i < p; i++) {
                buf[cnt++] = buf4[i];
            }
        }

        return cnt;
    }

}
