package datastructures.tree

sealed class DFSTraversalOrder {
    object PREORDER : DFSTraversalOrder()
    object INORDER : DFSTraversalOrder()
    object POSTORDER : DFSTraversalOrder()
}