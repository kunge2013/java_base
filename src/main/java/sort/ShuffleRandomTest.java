package sort;

/**
 * 随机数工具
 */
public class ShuffleRandomTest {
    public static void main(String[] args) {
        System.out.println(random54Pokers());
    }
    //带大小王
    public static int[] random54Pokers() {
        int[] cards = new int[54];
        int index = 0;
        for (int i = 3; i <= 6; i++) {
            for (int j = 1; j <= 10; j++) {
                cards[index++] = i * 100 + j;
            }
            cards[index++] = i * 100 + 20;
            cards[index++] = i * 100 + 30;
            cards[index++] = i * 100 + 40;
        }
        cards[index++] = SMALL_KING;
        cards[index++] = BIG_KING;
        return ShuffleRandom.shuffle(cards);
    }
    public static final int SMALL_KING = 860; //小王

    public static final int BIG_KING = 870;  //大王
}
