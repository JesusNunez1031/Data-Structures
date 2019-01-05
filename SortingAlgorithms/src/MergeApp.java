public class MergeApp {

    public static void merge(int[] arrayA, int sizeA, int[] arrayB, int sizeB, int[] arrayC){
        int aDex = 0, bDex = 0, cDex = 0;

        while(aDex < sizeA && bDex < sizeB){    //neither array is empty
            if(arrayA[aDex] < arrayB[bDex])
                arrayC[cDex++] = arrayA[aDex++];
            else
                arrayC[cDex++] = arrayB[bDex++];
        }

        while(aDex < sizeA) //arrayB is empty
            arrayC[cDex++] = arrayA[aDex++];    //but arrayA isnt

        while(bDex < sizeB) //arrayA is empty
            arrayC[cDex++] = arrayB[bDex++];    //but arrayB isnt
    }

    public static void display(int[] theArray, int size){
        for(int i = 0; i < size;i++){
            System.out.print(theArray[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args){
        int[] arrayA = {23, 47, 81, 95};
        int[] arrayB = {7, 14, 39, 55, 62, 74};
        int[] arrayC = new int[10];

        merge(arrayA, 4, arrayB, 6, arrayC);
        display(arrayC, arrayC.length);
    }
}
