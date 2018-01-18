package datastructures.tree

/**
 * Sample TreeNode trees for testing purposes.
 */


/**
 * ```
 *         (1)
 *  ┌───────┴───────┐
 * (2)             (3)
 * ```
 */
val tree123 =
    TreeNode(1).apply {
        left = TreeNode(2)
        right = TreeNode(3)
    }

/**
 * ```
 *             (1)
 *      ┌───────┴───────┐
 *     (2)             (3)
 *  ┌───┴───┐
 * (4)    (5)
 * ```
 */
val tree1to5 =
    TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
        right = TreeNode(3)
    }

/**
 * ```
 *            (5)
 *     ┌───────┴───────┐
 *    (3)             (8)
 * ┌───┴───┐       ┌───┴───┐
 * (1)    (4)     (6)     (9)
 * ```
 */
val bst3LevelsFull =
    TreeNode(5).apply {
        left = TreeNode(3).apply {
            left = TreeNode(1)
            right = TreeNode(4)
        }
        right = TreeNode(8).apply {
            left = TreeNode(6)
            right = TreeNode(9)
        }
    }

/**
 * ```
 *                 (7)
 *          ┌───────┴───────┐
 *         (4)             (9)
 *      ┌───┴           ┌───┴
 *      (3)            (8)
 *   ┌───┴
 *  (1)
 * ```
 */
val bstWithNulls =
    TreeNode(7).apply {
        left = TreeNode(4).apply {
            left = TreeNode(3).apply {
                left = TreeNode(1)
            }
        }
        right = TreeNode(9).apply {
            left = TreeNode(8)
        }
    }

/**
 * ```
 *   (1)
 *    ┴───────┐
 *           (1)
 *            ┴───┐
 *               (1)
 *           ┌────┴
 *          (2)
 * ```
 *
 * Equivalent to `buildTree(1, null, 1, null, 1, 2)`
 */
val treeUnbalanced = TreeNode(1).apply {
    right = TreeNode(1).apply {
        right = TreeNode(1).apply {
            left = TreeNode(2)
        }
    }
}

/**
 * Almost linear tree with 11 nodes on right, and 1 left child at last right child.
 *
 * Equivalent to `buildTree(1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, null, 1, 2)`
 */
val treeUnbalanced2 = TreeNode(1).apply {
    right = TreeNode(1).apply {
        right = TreeNode(1).apply {
            right = TreeNode(1).apply {
                right = TreeNode(1).apply {
                    right = TreeNode(1).apply {
                        right = TreeNode(1).apply {
                            right = TreeNode(1).apply {
                                right = TreeNode(1).apply {
                                    right = TreeNode(1).apply {
                                        right = TreeNode(1).apply {
                                            left = TreeNode(2)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}