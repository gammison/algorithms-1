/*
    Takes a list of integers and sorts them in ascending order
 */

package sorts;

import java.util.Arrays;

public class Insertion {
    public static void main (String [] args){
        int [] nums =  {45,76,1,6,7,2,5,14,345,24,642,54};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void sort(int [] array){
        for (int i =0; i< array.length-1;i++){
            int j = i;
            int element = array[i];
            while(j>0 && array[j-1] > element){
                array[j] = array[j-1];
                j -= 1;
            }
            array[j] = element;
        }
    }
}
