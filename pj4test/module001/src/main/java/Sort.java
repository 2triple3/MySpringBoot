import java.util.Arrays;

import org.apache.log4j.Logger;

public class Sort {

    private static  Logger logger = Logger.getLogger(Sort.class);
    static int[] arr =new int[]{5,3,2,4,1,6};

    /**
     * 冒泡排序
     * 提示：每一轮循环找到一个极值交换到数组头部
     *
     */
   public static void bubbleSort(int [] a){
       for(int i=0;i<a.length-1;i++){
           for(int j=i+1;j<a.length;j++){
               if(a[i]>a[j]){
                   int tmp=a[i];
                   a[i]=a[j];
                   a[j]=tmp;
               }
           }
       }

   }

    /**
     * 选择排序
     * 提示：原始数组看作前后两部分，前一部分有序，后一部分无序
     * 每一轮循环找到极值的下标，根据下标进行交换，有序部分不断壮大，无序部分不断缩小
     *
     */
    public static void selectSort(int[] a){
       for(int i=0;i<a.length-1;i++){
           int index=i;
           for(int j=i+1;j<a.length;j++){
               if(a[index]>a[j]){
                   index = j;
               }
           }
           int tmp=a[i];
           a[i]=a[index];
           a[index]=tmp;
       }

    }

    /**
     * 插入排序
     * 提示：比如循环进行到i的时候，a[0]至a[i-1]都是有序的,
     * 先把a[i]暂存为tmp(必须暂存，否则如果后面挪位置的时候就会改变其值)，
     * 依次从a[i-1]往前取数和tmp比较，相当于直到a[j-1]<=tmp那个i之后的元素整体向后挪了一位,从而把目标位置空出来
     * 然后把tmp赋值给a[j]就完成了插入
     */
    public static void insertSort(int[] a){

//        //优化前,其实没体明显现插入
//        for(int i=1; i<a.length; i++){
//            for(int j=i; j>0; j--){
//                if(a[j]<a[j-1]){
//                    int temp = a[j-1];
//                    a[j-1] = a[j];
//                    a[j] = temp;
//                }else{
//                    break;
//                }
//            }
//        }

        for(int i=1;i<a.length;i++){
            if(a[i]<a[i-1]){
                int tmp=a[i];
                for(int j=i;j>=0;j--){
                   if(j>0&&a[j-1]>tmp){
                       a[j]=a[j-1];
                   }else{
                       a[j]=tmp;
                       break;
                   }
                }
            }
        }

    }

    /**
     * 快速排序
     * @param a        待排序列
     * @param leftIndex  待排序列起始位置
     * @param rightIndex 待排序列结束位置
     */
    private static void quickSort(int[] a, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int left = leftIndex;
        int right = rightIndex;
        //待排序的第一个元素作为基准值
        int key = a[right];

        //从左右两边交替扫描，直到left = right
        while (left < right) {
            while (left < right && a[left] <= key) {
                //从左往右扫描，找到第一个比基准值大的元素
                left++;
            }
            //找到这种元素将arr[left]放入arr[right]中
            a[right] = a[left];
            while (right > left && a[right] >= key) {
                //从右往左扫描，找到第一个比基准值小的元素
                right--;
            }
            //找到这种元素将arr[right]放入arr[left]中
            a[left] = a[right];
        }
        //基准值归位,一遍下来基准值左边都是比其小的，右边都是比其大的
        a[left] = key;
        //对基准值左边的元素进行递归排序
        quickSort(a, leftIndex, left - 1);
        //对基准值右边的元素进行递归排序。
        quickSort(a, right + 1, rightIndex);
    }

    public static void main(String[] args) {
        //bubbleSort(arr);
        //selectSort(arr);
        //insertSort(arr);
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        logger.debug("log666");
    }
}
