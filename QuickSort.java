package com.company;
import java.util.*;

public class QuickSort {
    public static int medPivot(Comparable[] array) {
        Comparable[] array1 = new Comparable[3]; //pick the median of 3 random elements from the array
        for (int i = 0; i < 3; i++) {
            int rnd = new Random().nextInt(array.length);
            array1[i] = array[rnd];
        }
        if (array1[0] == array1[1] && array1[1] == array1[2]){
            System.out.println("Pivot is: ");
            System.out.println(array1[0]);
            System.out.println("Pivot Index is: ");
            return Arrays.asList(array).indexOf(array1[0]);
        }

        else if (array1[0] == array1[1]){
            System.out.println("Pivot is: ");
            System.out.println(array1[0]);
            System.out.println("Pivot Index is: ");
            return Arrays.asList(array).indexOf(array1[0]);
        }
        else if(array1[1] == array1[2]){
            System.out.println("Pivot is: ");
            System.out.println(array1[1]);
            System.out.println("Pivot Index is: ");
            return Arrays.asList(array).indexOf(array1[1]);
        }
        else if (array1[0] == array1[2]){
            System.out.println("Pivot is: ");
            System.out.println(array1[2]);
            System.out.println("Pivot Index is: ");
            return Arrays.asList(array).indexOf(array1[2]);
        } //if 2 or more are equal, pick the mode

        else if (((array1[1].compareTo(array1[0]) > 0) && (array1[1].compareTo(array1[2]) < 0))
        || ((array1[1].compareTo(array1[2]) > 0) && (array1[1].compareTo(array1[1]) < 0))) {
            System.out.println("Pivot is: ");
            System.out.println(array1[1]);
            System.out.println("Pivot Index is: ");
            return Arrays.asList(array).indexOf(array1[1]);
        }
        else if (((array1[0].compareTo(array1[1]) > 0) && (array1[0].compareTo(array1[2]) < 0))
                || ((array1[0].compareTo(array1[2]) > 0) && (array1[0].compareTo(array1[1]) < 0))) {
            System.out.println("Pivot is: ");
            System.out.println(array1[0]);
            System.out.println("Pivot Index is: ");
            return Arrays.asList(array).indexOf(array1[0]);
        }
        else{
            System.out.println("Pivot is: ");
            System.out.println(array1[2]);
            System.out.println("Pivot Index is: ");
            return Arrays.asList(array).indexOf(array1[2]); //else we find the median through comparisons
        }
        }

        public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
        }

        public static void exch(Comparable[] array,int x,int y){
        Comparable z = array[x];
        array[x] = array[y];
        array[y] = z;
        }

        public static void insertionSort(Comparable[] array) {
            int N = array.length;
            for (int i = 1; i < N; i++) {
                for (int j = i; j > 0 && less(array[j], array[j - 1]); j--)
                    exch(array, j, j - 1);

            } //insertion sort for smaller slices
        }
    public static boolean isSorted(Comparable[] list) {
        for (int i = 1; i < list.length; i += 1) {
            if (less(list[i], list[i-1])) {
                return false;
            }
        }
        return true; //check if array is sorted
    }

        public static int partition(Comparable[] array,int lo,int hi,boolean strat) {
            if (strat == true) { //partition the elements of the array
                int i = lo, j = hi + 1;//boolean strat implements the getgetPivot method - if true, we us the first element
                Comparable v = array[lo];//otherwise we use the median via the medPivot function
                while (true) {
                    while (less(array[++i], v)) {
                        if (i == hi) {
                            break;
                        }
                    }
                    while (less(v, array[--j])) {
                        if (j == lo) {
                            break;
                        }
                    }
                    if (i >= j) {
                        break;
                    }
                    exch(array, i, j);
                }
                exch(array, lo, j);
                return j;
            } else {
                int piv = medPivot(array);
                exch(array, piv, lo);
                int i = lo, j = hi + 1;
                Comparable v = array[lo];
                while (true) {
                    while (less(array[++i], v)) {
                        if (i == hi) {
                            break;
                        }
                    }
                    while (less(v, array[--j])) {
                        if (j == lo) {
                            break;
                        }
                    }
                    if (i >= j) {
                        break;
                    }
                    exch(array, i, j);
                }
                exch(array, lo, j);
                return j;
            }
        }


        public static void qSort(Comparable[] array,int lo,int hi,int M,boolean strat){
            if (hi <= lo + M) { //M is the cutoff for the insertion sort
                insertionSort(array);
                return;
            }
            int j = partition(array, lo, hi,strat); //partition the array
            qSort(array, lo, j-1,M,strat); //recursively paritition the results of the partition
            qSort(array, j+1, hi,M,strat);
        }

        public static void main(String args[]) {
            Comparable[] r = new Comparable[100000];
            for (int i = 0; i < r.length; i++) {
                r[i] = Math.random() * 100;
            }
            long times[] = new long[31];
            for (int i = 0; i < 31; i++) {
                long startTime = System.nanoTime();
                qSort(r, 0, 8, i, true);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                times[i] = totalTime*;
            }
            for (int i = 0; i < 31; i++) {
                System.out.print(times[i]);
                System.out.print(", ");
            }
        }
    }

///Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=57137:/Applications/IntelliJ IDEA CE.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Users/rohandatta/IdeaProjects/Lab2Q2/out/production/Lab2Q2 com.company.QuickSort
//0.0217012485395629
//2.3088990125887943
//4.6781150523869
//5.058228276667165
//5.25415984654053
//5.3972268641041214
//5.53338263881985
//5.683965110528177
//6.378231922468592
//7.307136674360681
//7.352532623095009
//7.7845912000474415
//9.940086501921087
//11.449253172971552
//12.253489556291164
//12.874928267670438
//13.560249388602086
//13.725764622972658
//15.34549238678048
//15.458188937247929
//17.067635070805732
//18.22087277616188
//18.96998242710264
//20.44911277037884
//21.231712141465742
//21.464066293874197
//21.701334057008115
//21.79149399378644
//23.852816417727997
//24.01006864636388
//25.28115179538023
//26.955420382578165
//27.031587507785304
//28.188679518348092
//29.915823700895196
//30.24140067120491
//30.961213468819327
//31.26353210667291
//34.26388457805219
//34.32180667822736
//36.47964666922322
//39.515500424560635
//41.034150491312296
//41.82007542681615
//42.47200488732901
//42.87164690188378
//42.871999212524116
//47.89647654643361
//50.87971573674699
//52.336633117231614
//52.45083299807934
//53.440368077191366
//54.810090332428004
//55.240046878804485
//55.6745087484415
//55.693400520805994
//55.717900184815996
//56.34386507009452
//57.68177735951383
//57.73853731315967
//57.93985670950646
//59.201533122799766
//60.83064398887015
//60.90153985992688
//61.70363719845871
//65.17799730988327
//67.12255437150732
//68.34903715697097
//68.77042272894695
//69.72492188800189
//70.01328871807539
//71.1006613458562
//71.5807787387859
//71.58240036351671
//72.40461940415334
//73.18516080738246
//73.9199295577062
//76.47976096133175
//76.64175459552503
//76.71768274836535
//78.26059821270462
//78.3728583378658
//78.4566160229856
//79.17820297351965
//79.25751653524046
//82.76133026170913
//82.80707276950262
//83.41760072088526
//83.45077005798788
//86.20061269302359
//88.22856284176459
//88.60730758047092
//92.60687245702297
//92.82960216368691
//94.06109171830902
//94.18825983512502
//96.94696779644056
//97.87728106839208
//98.25513693758377
//99.64205503083889
