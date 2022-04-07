package wayfair;

public class AddString {

    public String addStrings(String num1, String num2) {
        // write your code here
        String res = "";
        if (num1 == null && num2 == null) {
            return null;
        }
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }

        int i = num1.length() - 1;
        int j = num2.length() -1 ;

        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0) {
            if ( (i >= 0 && num1.charAt(i) == ',') || (j >= 0 && num2.charAt(j) == ',')) {
                i--;
                j--;
                continue;
            }

            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int total = n1 + n2 + carry;
            int val = total % 10;
            carry = total / 10;
            if (sb.length() % 4 == 3) {
                sb.append(",");
            }
            sb.append(val);
            i--;
            j--;
        }
        if (carry == 1) {
            if (sb.length() % 4 == 3) {
                sb.append(",");
            }
            sb.append(carry);

        }

        res = sb.reverse().toString();
        return res;

    }

    public static void main(String[] args) {
        AddString addString = new AddString();
//        String num1 = "19999999";
//        String num2 = "1";
        //1,023,590
//        String num1 = "123,456";
//        String num2 = "900,134";
        String num1 = "3,456";
        String num2 = "8,345";
        String res =  addString.addStrings(num1, num2);
        System.out.println(res);


    }
}

