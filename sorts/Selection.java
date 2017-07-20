package sorts;

import java.util.Arrays;

public class Selection {
    public static void main (String [] args){
        int [] nums = {53,53,14,3,15,7,9};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int [] array){
        for (int i =0; i<array.length-1;i++){
            int min = i;
            for(int j=i+1; j<array.length;j++){
                if(array[min] > array[j]){
                    min = j;
                }
            }

            if(min != i){
                swap(array, i, min);
            }

        }
    }

    public static void swap(int [] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
