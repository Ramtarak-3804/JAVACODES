
//hello my friends
public class MinimumDiameter {
    public static void main(String[] args) {
        // Create tree1
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);
        tree1.left.left = new TreeNode(4);
        tree1.left.right = new TreeNode(5);

        // Create tree2
        TreeNode tree2 = new TreeNode(6);
        tree2.left = new TreeNode(7);
        tree2.right = new TreeNode(8);
        tree2.right.left = new TreeNode(9);

        // Merge the trees
        TreeNode mergedTree = new TreeNode(0);
        mergedTree.left = tree1;
        mergedTree.right = tree2;

        // Calculate the minimum diameter
        int minimumDiameter = calculateDiameter(mergedTree);
        System.out.println("Minimum diameter after merging two trees: " + minimumDiameter);
    }




    public static int calculateDiameter(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        int leftDiameter = calculateDiameter(node.left);
        int rightDiameter = calculateDiameter(node.right);

        int currentDiameter = leftHeight + rightHeight + 1;
        int maximumDiameter = Math.max(currentDiameter, Math.max(leftDiameter, rightDiameter));

        return maximumDiameter;
    }

    public static int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}