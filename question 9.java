package GlobalTrendProgrammingAssessment;

import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class IntervalTreeNode {
    Interval interval;
    int maxEnd;
    IntervalTreeNode left;
    IntervalTreeNode right;

    IntervalTreeNode(Interval interval) {
        this.interval = interval;
        this.maxEnd = interval.end;
        this.left = null;
        this.right = null;
    }
}

public class IntervalTree {
    private IntervalTreeNode root;

    public IntervalTree() {
        this.root = null;
    }

    public void insertInterval(int start, int end) {
        Interval interval = new Interval(start, end);
        this.root = insertHelper(root, interval);
    }

    private IntervalTreeNode insertHelper(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return new IntervalTreeNode(interval);
        }

        // Insert in BST manner
        if (interval.start < node.interval.start) {
            node.left = insertHelper(node.left, interval);
        } else {
            node.right = insertHelper(node.right, interval);
        }

        // Update maxEnd in current node
        if (node.maxEnd < interval.end) {
            node.maxEnd = interval.end;
        }

        return node;
    }

    public void deleteInterval(int start, int end) {
        this.root = deleteHelper(root, start, end);
    }

    private IntervalTreeNode deleteHelper(IntervalTreeNode node, int start, int end) {
        if (node == null) {
            return null;
        }

        if (start < node.interval.start) {
            node.left = deleteHelper(node.left, start, end);
        } else if (start > node.interval.start) {
            node.right = deleteHelper(node.right, start, end);
        } else {
            if (end == node.interval.end) {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    IntervalTreeNode successor = findMin(node.right);
                    node.interval = successor.interval;
                    node.maxEnd = successor.maxEnd;
                    node.right = deleteHelper(node.right, successor.interval.start, successor.interval.end);
                }
            } else {
                node.right = deleteHelper(node.right, start, end);
            }
        }

        // Update maxEnd in current node
        if (node != null) {
            node.maxEnd = Math.max(node.interval.end, getMaxEnd(node.right));
        }

        return node;
    }

    private IntervalTreeNode findMin(IntervalTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int getMaxEnd(IntervalTreeNode node) {
        return node == null ? Integer.MIN_VALUE : node.maxEnd;
    }

    public List<Interval> findOverlappingIntervals(int start, int end) {
        List<Interval> result = new ArrayList<>();
        findOverlappingHelper(root, start, end, result);
        return result;
    }

    private void findOverlappingHelper(IntervalTreeNode node, int start, int end, List<Interval> result) {
        if (node == null) {
            return;
        }

        // Check if node's interval overlaps with [start, end]
        if (node.interval.start <= end && node.interval.end >= start) {
            result.add(node.interval);
        }

        // Recursively search left and right subtrees if necessary
        if (node.left != null && node.left.maxEnd >= start) {
            findOverlappingHelper(node.left, start, end, result);
        }
        if (node.right != null && node.right.interval.start <= end) {
            findOverlappingHelper(node.right, start, end, result);
        }
    }

    public static void main(String[] args) {
        IntervalTree intervalTree = new IntervalTree();

        intervalTree.insertInterval(15, 20);
        intervalTree.insertInterval(10, 30);
        intervalTree.insertInterval(5, 12);
        intervalTree.insertInterval(25, 35);
        intervalTree.insertInterval(40, 50);

        List<Interval> overlaps = intervalTree.findOverlappingIntervals(14, 33);
        System.out.println("Intervals overlapping with [14, 33]:");
        for (Interval interval : overlaps) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }

        intervalTree.deleteInterval(25, 35);

        overlaps = intervalTree.findOverlappingIntervals(14, 33);
        System.out.println("\nAfter deleting [25, 35], intervals overlapping with [14, 33]:");
        for (Interval interval : overlaps) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }
}

