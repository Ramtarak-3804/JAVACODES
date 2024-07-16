import java.util.*;

class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
         // Find the LCA of startValue and destValue
        TreeNode lca = findLCA(root, startValue, destValue);
        
        // Find path from LCA to startValue
        StringBuilder pathToStart = new StringBuilder();
        findPath(lca, startValue, pathToStart);
        
        // Find path from LCA to destValue
        StringBuilder pathToDest = new StringBuilder();
        findPath(lca, destValue, pathToDest);
        
        // Convert pathToStart to 'U' directions
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < pathToStart.length(); i++) {
            result.append('U');
        }
        
        // Append pathToDest to result
        result.append(pathToDest.toString());
        
        return result.toString();
    }

    private TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    // Find path from root to value
    private boolean findPath(TreeNode root, int value, StringBuilder path) {
        if (root == null) {
            return false;
        }
        if (root.val == value) {
            return true;
        }
        // Search in left subtree
        path.append('L');
        if (findPath(root.left, value, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);  // backtrack
        
        // Search in right subtree
        path.append('R');
        if (findPath(root.right, value, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);  // backtrack
        
        return false;
    
    }
}