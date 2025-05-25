import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BinomialHeap {
    private static class BinomialTree {
        int value;
        List<BinomialTree> children;
        int order;

        BinomialTree(int value) {
            this.value = value;
            this.children = new ArrayList<>();
            this.order = 0;
        }

        void addChild(BinomialTree tree) {
            children.add(tree);
            order++;
        }
    }

    private List<BinomialTree> trees;
    private int size;

    public BinomialHeap() {
        this.trees = new ArrayList<>();
        this.size = 0;
    }

    public void insert(int value) {
        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.trees.add(new BinomialTree(value));
        tempHeap.size = 1;
        merge(tempHeap);
        size++;
    }

    public boolean delete(int value) {
        if (find(value) == null) return false;

        decreaseKey(value, Integer.MIN_VALUE);
        extractMin();
        return true;
    }

    public Integer find(int value) {
        for (BinomialTree tree : trees) {
            Integer result = findInTree(tree, value);
            if (result != null) return result;
        }
        return null;
    }

    private void merge(BinomialHeap otherHeap) {
        trees.addAll(otherHeap.trees);
        size += otherHeap.size;
        consolidate();
    }

    private void consolidate() {
        trees.sort((t1, t2) -> t1.order - t2.order);

        for (int i = 0; i < trees.size() - 1; i++) {
            BinomialTree current = trees.get(i);
            BinomialTree next = trees.get(i + 1);

            if (current.order == next.order) {
                BinomialTree merged = mergeTrees(current, next);
                trees.set(i, merged);
                trees.remove(i + 1);
                i--;
            }
        }
    }

    private BinomialTree mergeTrees(BinomialTree a, BinomialTree b) {
        if (a.value > b.value) {
            b.addChild(a);
            return b;
        } else {
            a.addChild(b);
            return a;
        }
    }

    private Integer findInTree(BinomialTree tree, int value) {
        if (tree.value == value) return value;

        for (BinomialTree child : tree.children) {
            Integer result = findInTree(child, value);
            if (result != null) return result;
        }
        return null;
    }

    private void decreaseKey(int oldValue, int newValue) {
        for (BinomialTree tree : trees) {
            if (decreaseKeyInTree(tree, oldValue, newValue)) return;
        }
    }

    private boolean decreaseKeyInTree(BinomialTree tree, int oldValue, int newValue) {
        if (tree.value == oldValue) {
            tree.value = newValue;
            bubbleUp(tree);
            return true;
        }

        for (BinomialTree child : tree.children) {
            if (decreaseKeyInTree(child, oldValue, newValue)) return true;
        }
        return false;
    }

    private void bubbleUp(BinomialTree tree) {
        BinomialTree current = tree;
        while (current.children.size() > 0) {
            BinomialTree minChild = Collections.min(current.children, (t1, t2) -> t1.value - t2.value);
            if (current.value <= minChild.value) break;

            int temp = current.value;
            current.value = minChild.value;
            minChild.value = temp;

            current = minChild;
        }
    }

    public Integer extractMin() {
        if (trees.isEmpty()) return null;

        BinomialTree minTree = Collections.min(trees, (t1, t2) -> t1.value - t2.value);
        trees.remove(minTree);
        size--;

        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.trees = minTree.children;
        tempHeap.size = (1 << minTree.order) - 1;
        merge(tempHeap);

        return minTree.value;
    }

    public int size() {
        return size;
    }
    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000000);
        }
        return array;
    }
}