package karat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// The people who buy ads on our network don't have enough data about how ads are working for
//their business. They've asked us to find out which ads produce the most purchases on their website.

// Our client provided us with a list of user IDs of customers who bought something on a landing page
//after clicking one of their ads:

// # Each user completed 1 purchase.
// completed_purchase_user_ids = [
//   "3123122444","234111110", "8321125440", "99911063"]

// And our ops team provided us with some raw log data from our ad server showing every time a
//user clicked on one of our ads:
// ad_clicks = [
//  #"IP_Address,Time,Ad_Text",
//  "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
//  "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
//  "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
//  "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
//  "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
//  "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",
//]

//The client also sent over the IP addresses of all their users.

//all_user_ips = [
//  #"User_ID,IP_Address",
//   "2339985511,122.121.0.155",
//  "234111110,122.121.0.1",
//  "3123122444,92.130.6.145",
//  "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
//  "8321125440,82.1.106.8",
//  "99911063,92.130.6.144"
//]

// Write a function to parse this data, determine how many times each ad was clicked,
//then return the ad text, that ad's number of clicks, and how many of those ad clicks
//were from users who made a purchase.


// Expected output:
// Bought Clicked Ad Text
// 1 of 2  2017 Pet Mittens
// 0 of 1  The Best Hollywood Coats
// 3 of 3  Buy wool coats for your pets

//3 inputs: a list of user ids + IPs, a list of user ids who have made purchases, a list of advertisement clicks with user IPs.
//解法：过一遍ids + IPs 的list 建一个hashtable （ip->id), 过一遍ads计算clicks和purchases.
public class ConversionRate {
    public static void adsConversionRate(String[] completedPurchaseUserIds, String[] adClicks, String[] allUserIps) {
        Set<String> usersSet = new HashSet<>();
        for (String userId : completedPurchaseUserIds) {
            usersSet.add(userId);
        }

        //ip --> users
        Map<String, String> ipToUsers = new HashMap<>();
        for (String userIp : allUserIps) {
            String[] userIpArr = userIp.split(",");
            String user = userIpArr[0];
            String ip = userIpArr[1];
            ipToUsers.put(ip, user);
        }

        //ads --> new int[] {clicks,  purchase}
        Map<String, int[]> map = new HashMap<>();
        for (String adClick :adClicks) {
            String[] adClickArr = adClick.split(",");
            String adText = adClickArr[2];
            String ip = adClickArr[0];
            String user = ipToUsers.get(ip);
//            "122.121.0.1,2016-11-03 11:41:19, Buy wool coats for your pets",
            if (!map.containsKey(adText)) {
                map.put(adText, new int[2]);
            }
            int[] val = map.get(adText);
            val[0] += 1;
            if (usersSet.contains(user)){
                val[1] += 1;
            }

        }

        for (String key: map.keySet()) {
            int[] values = map.get(key);
            System.out.println(values[1] + " " + values[0] + " " + key);
        }

    }

    public static void main(String[] args) {
        String[] ad_clicks = {
                "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
                "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
                "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
                "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
                "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
                "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",
        };

        String[] completed_purchase_user_ids = {
                "3123122444", "234111110", "8321125440", "99911063"
        };

        String[] all_user_ips = {
                "2339985511,122.121.0.155",
                "234111110,122.121.0.1",
                "3123122444,92.130.6.145",
                "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
                "8321125440,82.1.106.8",
                "99911063,92.130.6.144"
        };

        ConversionRate.adsConversionRate(completed_purchase_user_ids, ad_clicks, all_user_ips);



    }
}
