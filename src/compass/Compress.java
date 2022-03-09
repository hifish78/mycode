package compass;

public class Compress {

        //V1 only 字符
        public static String compressv1(String str){
            if(str.length() <=0){
                return str;
            }
            char pre = str.charAt(0);
            int count =1;
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i < str.length(); i++){
                if(str.charAt(i) == str.charAt(i - 1)){
                    count++;
                }else{
                    if(count > 1) {
                        sb.append(pre).append(count);
                    }else{
                        sb.append(pre);
                    }
                    pre = str.charAt(i);
                    count = 1;
                }
            }

            if(count > 1) {
                sb.append(pre).append(count);
            }else{
                sb.append(pre);
            }
            //sb.append(pre).append(count);
            return sb.toString();
        }

        public static String decompressv1(String str){
            if(str.length() < 2){
                return str;
            }
            StringBuilder sb = new StringBuilder();
            int i =0;
            while(i < str.length()){
                char ch = str.charAt(i++);
                int num = 0;
                while(i < str.length() && Character.isDigit(str.charAt(i))){
                    num = num * 10 + (str.charAt(i) - '0');
                    i++;
                }
                if(num > 0) {
                    for (int j = 0; j < num; j++) {
                        sb.append(ch);
                    }
                }else{
                    sb.append(ch);
                }
            }
            return sb.toString();

        }

        public static void main(String[] args){
            String str = "aaabbbccc";
            System.out.println("compress:" + compressv1(str));
            System.out.println("decompress:" + decompressv1(compressv1(str)));


            str = "abb";
            System.out.println("compress:" + compressv1(str));
            System.out.println("decompress:" + decompressv1(compressv1(str)));

        }



}
