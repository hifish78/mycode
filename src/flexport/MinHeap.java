package flexport;

public class MinHeap {

    public void buildMinHeap(int[] heapArray, int heapSize) {
        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            minHeapify(heapArray, i, heapSize);
        }
    }

    private void minHeapify(int[] heapArray, int index, int heapSize) {
        int smallest = index;
        while ( smallest < heapSize / 2) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < heapSize && heapArray[left] < heapArray[index]) {
                smallest = left;
            }

            if (right < heapSize && heapArray[right] < heapArray[index]) {
                smallest = right;
            }

            if (smallest != index) {
                int tmp = heapArray[index];
                heapArray[index] = heapArray[smallest];
                heapArray[smallest] = tmp;
                index = smallest;
            } else {
                break;
            }
        }
    }
}
