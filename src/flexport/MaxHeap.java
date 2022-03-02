package flexport;

import java.util.Arrays;

// heaps can be implemented using Arrays.
// The contents of a heap with n nodes are stored in such a way that all the parent nodes occur in th 1st half of array (n /2)
// parent < n / 2 ;
// leftChild = 2k + 1
// rightChild = 2k + 2
//Heaps can be implemented using arrays. Initially, elements are placed in nodes in the same order as they appear in the array.
// Then a function is called over the whole Heap in a bottom-up manner, which “Max Heapifies” this Heap so that the Heap property is
// satisfied on all nodes.
//https://www.educative.io/courses/data-structures-coding-interviews-java/JPX181R2Vzv
public class MaxHeap {
   // Only 1 function
    public void buildMaxHeap2(int[] arr) {
        // 1 2 3 4
        int n = arr.length;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            int parent = i;
            while ( parent < n / 2) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;

                if (left < n && arr[left] > arr[parent]) {
                    parent = left;
                }

                if (right < n && arr[right] > arr[parent]) {
                    parent = right;
                }

                if (parent != i) {
                    int tmp = arr[i];
                    arr[i] = arr[parent];
                    arr[parent] = tmp;
                    i = parent;
                } else {
                    break;
                }
            }

        }
    }

    // use 2 functions
    public void buildMaxHeap(int[] heapArray, int heapSize) {
        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            maxHeapify(heapArray, i, heapSize);
        }
    }

    public void maxHeapify(int[] heapArray, int index, int heapSize) {
        int largest = index;
        while (largest < heapSize / 2) { // check parent nodes only
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < heapSize && heapArray[left] > heapArray[index]) {
                largest = left;
            }

            if (right < heapSize && heapArray[right] > heapArray[largest]) {
                largest = right;
            }

            if (largest != index) {
                int tmp = heapArray[index];
                heapArray[index] = heapArray[largest];
                heapArray[largest] = tmp;
                index = largest;
             } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        int[] heapArray = { 1, 4, 7, 12, 15, 14, 9, 2, 3, 16 };

        System.out.println("Before heapify: "+ Arrays.toString(heapArray));
        maxHeap.buildMaxHeap(heapArray, heapArray.length);
        System.out.println("After heapify: "+Arrays.toString(heapArray));
    }
}
