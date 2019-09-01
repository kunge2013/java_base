package sort;

/**
 * 桶排序
 */
public class TongPaiXu {
    public static void main(String[] args) {
        int arr[] = {3,2,7,5,3,8,1,9,19};
        int max = 19;
        int brr[] = new int[max + 1];
        for (int i = 0; i< arr.length ;i ++) {
           int val = brr[arr[i]] ++;
            System.out.println(val);
        }

        for (int j = 0;j < brr.length;j++) {
            while ((brr[j] --) > 0) {
                System.out.println(j + ",");
            }
        }
    }
}
