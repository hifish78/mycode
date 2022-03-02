package flexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given card with cost in terms of tokens. For eg to buy some Card A, you need 3 Blue tokens and 2 Green tokens.
 * Tokens can be of Red, Green, Blue, Black or White color.
 *
 * Now there is player who is holding some tokens. For eg player has 4 Blue tokens and 2 Green tokens, then player
 * can buy above Card A. Lets say if player only has 2 Blue tokens and 2 Green tokens, then player can not buy Card A
 * above as player is short of 1 Blue token.
 *
 * Write a method that returns true if player can buy the card or false otherwise.
 *
 * More examples :
 *
 * Cost of Card : 2 White, 1 Black and 4 Blue
 * If Player has : 2 White, 2 Black and 4 Blue, method will return true
 * If Player has : 2 White, 2 Green and 4 Blue, method will return false
 *
 * Apart from above info, nothing is given and you have to come with your own implementation.
 * ==========
 * 今天的电面是一道OOD的题目.
 * Part1: 一个player有5个颜色的tokens, 比如 2个蓝色的tokens和1个绿色的tokens可以购买一个cards. 那么你现在手上已经有的tokens可不可以买cards. 因为是一个OOD,
 * tokens的数量都是自己设计, 然后购买cards的条件也是自己设计的. 然后写一个方程来判断是否可以买这个cards.
 * Part2: 和part1差不多, 只是要你打印出还剩下多少tokens 和 cards的数量
 * Part3: 和之前有些不一样了. 如果现在有2个绿色的card 然后2个绿色的tokens, 然后告诉你要3个绿色的票才能换 一个白色的card. 要你输出 你的钱包里面有哪些card和tokens. cards要先被消耗,
 * 一个cards等于一个票. 所以最后的结果就是 一个白色的card 0个绿色的card 1个绿色的tokens.
 */

// card A : 3 blue + 2 green;
// player: 4 blue + 2 green

enum Token {
    Red,
    Green,
    Blue,
    Black,
    White
}


class Person {
    Map<Card, Integer> cards;
    Map<Token, Integer> playtokens;
    Person() {
        cards = new HashMap();
        playtokens = new HashMap<>();
    }

    Map<Token, Integer> calcTokens() {
        for (Card card : cards.keySet()) {
            for (Token token : card.map.keySet()) {
                playtokens.put(token, playtokens.getOrDefault(token, 0) + card.map.get(token));
            }
        }
        return playtokens;
    }

}

class Greencard  extends Card {
    String color = "Green";
    Greencard() {
        map = new HashMap<>();
        map.put(Token.Green, 2);
    }
}

class Card {
    String color = "";
    Map<Token, Integer> map;
    Card() {
        map = new HashMap<>();
        map.put(Token.White,  2);
        map.put(Token.Black, 1);
        map.put(Token.Blue, 4);
    }
}

class WhiteCard extends Card{
    String color = "White";
    WhiteCard() {
        map = new HashMap<>();
        map.put(Token.Green, 3);
    }
}


// * Cost of Card : 2 White, 1 Black and 4 Blue
// * If Player has : 2 White, 2 Black and 4 Blue, method will return true
// * If Player has : 2 White, 2 Green and 4 Blue, method will return false
public class TokenCard {

// * If Player has : 2 White, 2 Black and 4 Blue, method will return true
    public boolean canBuy(Person person, Card card) {
        for (Token token: card.map.keySet()) {
            if (!person.playtokens.containsKey(token)) {
                return false;
            }
            if (person.playtokens.get(token) < card.map.get(token)) {
                return false;
            }
        }
        return true;
    }

    public void buy(Person person, Card card) {
        if (!canBuy(person, card)) {
            for (Token token: person.playtokens.keySet()) {
                System.out.println(token + " : " + person.playtokens.get(token));
            }
            return;
        }

        for (Token token : card.map.keySet()) {
            person.playtokens.put(token, person.playtokens.get(token) - card.map.get(token));
        }

        for (Token token: person.playtokens.keySet()) {
            System.out.println(token + " : " + person.playtokens.get(token));
        }
    }

    void changeToWhite(Person person, Card card) {
        for (Token token : card.map.keySet()) {
            if (card.map.get(token) > person.playtokens.get(token)) {
                return;
            }
            person.playtokens.put(token, person.playtokens.get(token) - card.map.get(token));
            //
            person.cards.put(card, person.cards.getOrDefault(card, 0) + 1);
        }
    }

//* Part3: 和之前有些不一样了. 如果现在有2个绿色的card 然后2个绿色的tokens,
// 然后告诉你要3个绿色的票才能换 一个白色的card. 要你输出 你的钱包里面有哪些card和tokens. cards要先被消耗,
//            * 一个cards等于一个票. 所以最后的结果就是 一个白色的card 0个绿色的card 1个绿色的tokens.

    public static void main(String[] args) {
        TokenCard tokenCard = new TokenCard();

        Person person1 = new Person();
        person1.playtokens.put(Token.White, 2);
        person1.playtokens.put(Token.Black, 2);
        person1.playtokens.put(Token.Blue, 4);

        Card card = new Card();
        System.out.println(tokenCard.canBuy(person1, card));
        tokenCard.buy(person1, card);

        Person person2 = new Person();
        person2.playtokens.put(Token.White, 2);
        person2.playtokens.put(Token.Green, 2);
        person2.playtokens.put(Token.Blue, 4);
        System.out.println(tokenCard.canBuy(person2, card));
        tokenCard.buy(person2, card);

        Card greencard = new Greencard();
        Person person3 = new Person();
        person3.cards.put(greencard, 2);
        person3.playtokens.put(Token.Green, 2);
        Map<Token, Integer> total = person3.calcTokens();

        WhiteCard whitecard = new WhiteCard();
        tokenCard.changeToWhite(person3, whitecard);

        System.out.println("bbbb");
        for (Card c : person3.cards.keySet()) {
            System.out.println("ccc");
            System.out.println(c.color + person3.cards.get(c));
        }

        for (Token t : person3.playtokens.keySet()) {
            System.out.println(t + "" + person3.playtokens.get(t));
        }



        System.out.println("aaaa");








    }






}
