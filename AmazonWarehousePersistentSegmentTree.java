public class AmazonWarehousePersistentSegmentTree {

    static class Node {
        int sum;
        Node left, right;

        Node(int sum) {
            this.sum = sum;
        }

        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.sum = (left != null ? left.sum : 0) +
                       (right != null ? right.sum : 0);
        }
    }

    static Node build(int[] arr, int start, int end) {
        if (start == end)
            return new Node(arr[start]);

        int mid = (start + end) / 2;
        Node left = build(arr, start, mid);
        Node right = build(arr, mid + 1, end);

        return new Node(left, right);
    }

    static Node update(Node node, int start, int end,
                       int idx, int value) {

        if (start == end)
            return new Node(value);

        int mid = (start + end) / 2;

        if (idx <= mid) {
            Node left = update(node.left, start, mid, idx, value);
            return new Node(left, node.right);
        } else {
            Node right = update(node.right, mid + 1, end, idx, value);
            return new Node(node.left, right);
        }
    }

    static int query(Node node, int start, int end,
                     int l, int r) {

        if (r < start || l > end)
            return 0;

        if (l <= start && end <= r)
            return node.sum;

        int mid = (start + end) / 2;

        return query(node.left, start, mid, l, r)
                + query(node.right, mid + 1, end, l, r);
    }

    static void printVersion(int[] arr, String version) {
        System.out.print(version + " : [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        int[] v0 = {12, 7, 25, 18, 9, 14, 6, 30};

        Node root0 = build(v0, 0, 7);

        int[] v1 = v0.clone();
        v1[2] += 50; // stock[3] += 50
        Node root1 = update(root0, 0, 7, 2, v1[2]);

        int[] v2 = v1.clone();
        v2[5] -= 4; // stock[6] -= 4
        Node root2 = update(root1, 0, 7, 5, v2[5]);

        int[] v3 = v2.clone();
        v3[2] -= 12; // stock[3] -= 12
        Node root3 = update(root2, 0, 7, 2, v3[2]);

        System.out.println("===== AMAZON WAREHOUSE INVENTORY VERSIONS =====\n");

        printVersion(v0, "Version V0");
        printVersion(v1, "Version V1");
        printVersion(v2, "Version V2");
        printVersion(v3, "Version V3");

        System.out.println("\n===== RANGE SUM QUERY (Categories 3 to 6) =====");

        System.out.println("V0 Sum = " +
                query(root0, 0, 7, 2, 5));

        System.out.println("V1 Sum = " +
                query(root1, 0, 7, 2, 5));

        System.out.println("V2 Sum = " +
                query(root2, 0, 7, 2, 5));

        System.out.println("V3 Sum = " +
                query(root3, 0, 7, 2, 5));

        System.out.println("\n===== ACCOUNTING QUERY =====");
        System.out.println(
                "Total stock in categories 3..6 at 10:00 AM (Version V2) = "
                        + query(root2, 0, 7, 2, 5));
    }
}