package compass;

import java.util.*;

/***
 * 给一个List<List<String>>  里面有地址的信息 比如说  
 * {{"20" "Broadway" "New York" "NY" "11120" }, {"200" "Broadway" "New York" "NY" "11120" }}
 * 还有另外一个input 是 {"100" "Broadway" "New York" "NY" } 返回这个地址的zip 是多少，
 * 应该返回 11120 因为他的街号码实在 20 和 200 之间，用了一个hashmap 搞定，follow up 是如何提高 runtime time
 */
public class Zipcode {
    public String getZipcode(List<List<String>> address, List<String> location) {
        Map<String, TreeMap<Integer, String>> map = new HashMap<>();
        String loc = location.get(1) + " " + location.get(2) + " " + location.get(3);
        int val = Integer.valueOf(location.get(0));
        for (List<String> add : address) {
            //Broadway + New York + NY
            String info = add.get(1) + " " + add.get(2) + " " + add.get(3);
            if (!map.containsKey(info)) {
                map.put(info, new TreeMap<>());
            }
            int number = Integer.valueOf(add.get(0));
            String zip = add.get(4);
            TreeMap<Integer, String> treemap = map.get(info);
            treemap.put(number, zip);
        }

        for (String key : map.keySet()) {
            if (key.equals(loc)) {
               TreeMap<Integer, String> dataSet = map.get(loc);
               Integer minVal = dataSet.floorKey(val);
               Integer maxVal = dataSet.ceilingKey(val);
               if (minVal == null || maxVal == null) {
                   return "Don't know";
               }

               System.out.println(minVal);
               System.out.println(maxVal);

               if (dataSet.get(minVal) == dataSet.get(maxVal)) {
                   return dataSet.get(minVal);
               }

            }
        }

        return "don't know";
    }

    public static void main(String[] args) {
        Zipcode zipcode = new Zipcode();
//        String[][] address = {
//                {"20", "Broadway",  "New York",  "NY" ,"11120" },
//                {"200", "Broadway",  "New York", "NY", "11120" },
//        };
        List<List<String>> inputs = new ArrayList<>();
        inputs.add(Arrays.asList(new String[] {"20", "Broadway",  "New York",  "NY" ,"11120" }));
        inputs.add(Arrays.asList(new String[] {"110", "Broadway",  "New York",  "NY" ,"11120" }));
        inputs.add(Arrays.asList(new String[] {"200", "Broadway",  "New York",  "NY" ,"11120" }));
        inputs.add(Arrays.asList(new String[] {"90", "Broadway",  "New York",  "NY" ,"11120" }));

        List<String> location = Arrays.asList(new String[]{"300", "Broadway", "New York", "NY"});
        String res = zipcode.getZipcode(inputs, location);
        System.out.println(res);


    }
}
