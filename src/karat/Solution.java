package karat;

/*
Another coding Q asked - Match the given word in the 2D char matrix and list the coordinates. Was told they don't care about the optimal solution. Just the execution and completeness. Can move only below and right.(row+1 and col+1)
Was given 28-30 mins to read Q, formulate answer, code, debug, execute and edge test cases.



I have the second question that I was asked, along with the solution that I had attempted.
import java.io.;
import java.util.;

/*
The people who buy ads on our network don't have enough data about how ads are working for their business. They've asked us to find out which ads produce the most purchases on their website.

Our client provided us with a list of user IDs of customers who bought something on a landing page after clicking one of their ads:

Each user completed 1 purchase.
completed_purchase_user_ids = [
"3123122444","234111110", "8321125440", "99911063"]

And our ops team provided us with some raw log data from our ad server showing every time a user clicked on one of our ads:

ad_clicks = [
#"IP_Address,Time,Ad_Text",
"122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
"96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
"122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
"82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
"92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
"122.121.0.155,2017-01-01 03:18:55,Buy wool coats for your pets",
"92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",
]

//2017 Pet Mittens [3123122444, 96.3.199.11]

The client also sent over the IP addresses of all their users.

all_user_ips = [
#"User_ID,IP_Address",
"2339985511,122.121.0.155",
"234111110,122.121.0.1",
"3123122444,92.130.6.145",
"39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
"8321125440,82.1.106.8",
"99911063,92.130.6.144"
]

Write a function to parse this data, determine how many times each ad was clicked, then return the ad text, that ad's number of clicks, and how many of those ad clicks were from users who made a purchase.

Expected output:

1 of 2 2017 Pet Mittens
0 of 1 The Best Hollywood Coats
3 of 4 Buy wool coats for your pets

purchases: number of purchase IDs
clicks: number of ad clicks
ips: number of IP addresses
*/
//public class WayFairInterview1 {
//
//    public static List<String> findPurchasedUsersData(String[] completedPurchaseUsers, String[] adClicks,
//                                                      String[] allUserIps) {
//        List<String> result = new ArrayList<String>();
//        Map<String, List<String>> map = new HashMap<String, List<String>>();
//        Map<String, String> ipUserMap = new HashMap<String, String>();
//        Set<String> usersWhoMadePurchases = new HashSet<String>();
//        for (String user : completedPurchaseUsers) {
//            usersWhoMadePurchases.add(user);
//        }
//
//        for (String ip : allUserIps) {
//            String[] splits = ip.split(",");
//            ipUserMap.put(splits[1], splits[0]);
//        }
//
//        for (String adClick : adClicks) {
//            String[] splits = adClick.split(",");
//            String iPAddress = splits[0];
//            String userID = ipUserMap.getOrDefault(iPAddress, "");
//            String adText = splits[2];
//            List<String> inner = map.getOrDefault(adText, new ArrayList<String>());
//            inner.add(userID);
//            map.put(adText, inner);
//        }
//
//        for (String key : map.keySet()) {
//            int totalClicks = map.get(key).size();
//            List<String> usersWhoClicked = map.get(key);
//            int purchasedCount = getPurchasedCount(usersWhoClicked, usersWhoMadePurchases);
//            String outputString = purchasedCount + " of " + totalClicks + "	 " + key;
//            result.add(outputString);
//        }
//
//        // System.out.println(map);
//
//        return result;
//
//    }
//
//    public static int getPurchasedCount(List<String> usersWhoClicked, Set<String> usersWhoMadePurchases) {
//        int count = 0;
//        for (String user : usersWhoClicked) {
//            if (usersWhoMadePurchases.contains(user)) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public static void main(String[] argv) {
//        String[] completedPurchaseUsers = { "3123122444", "234111110", "8321125440", "99911063" };
//
//        // "IP Address, timestamp, Ad text"
//        String[] adClicks = { "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
//                "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
//                "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
//                "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
//                "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
//                "122.121.0.155,2017-01-01 03:18:55,Buy wool coats for your pets",
//                "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens" };
//
//        // "User ID, IP address"
//        String[] allUserIps = { "2339985511,122.121.0.155", "234111110,122.121.0.1", "3123122444,92.130.6.145",
//                "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000", "8321125440,82.1.106.8",
//                "99911063,92.130.6.144" };
//
//        System.out.println(findPurchasedUsersData(completedPurchaseUsers, adClicks, allUserIps));
//
//    }
//}


public class Solution {
}
