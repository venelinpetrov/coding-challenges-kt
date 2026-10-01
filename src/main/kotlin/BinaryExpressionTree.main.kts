#!/usr/bin/env kotlin

/*
    This problem was asked by Microsoft.

    Suppose an arithmetic expression is given as a binary tree. Each leaf is an integer and each internal node is one of '+', '−', '∗', or '/'.

    Given the root to such a tree, write a function to evaluate it.

    For example, given the following tree:

        *
       / \
      +    +
     / \  / \
    3  2  4  5

    You should return 45, as it is (3 + 2) * (4 + 5).
*/

/*
    Solution:

    evaluate(node):
        if node is a number:
            return number

        left  = evaluate(node.left)
        right = evaluate(node.right)

        return left operator right
*/

class BinaryExpressionTree(private val root: Node) {

    data class Node(
        val value: String,
        val left: Node? = null,
        val right: Node? = null
    )
    fun calc(): Int = calc(root)

    private fun calc(node: Node): Int {
        if (node.left == null || node.right == null) {
            return node.value.toInt()
        }

        return when (node.value) {
            "+" -> calc(node.left) + calc(node.right)
            "-" -> calc(node.left) - calc(node.right)
            "*" -> calc(node.left) * calc(node.right)
            "/" -> calc(node.left) / calc(node.right)
            else -> error("Unknown operator: ${node.value}")
        }
    }
}

val tree = BinaryExpressionTree(
    BinaryExpressionTree.Node(
        "*",
        BinaryExpressionTree.Node(
            "+",
            BinaryExpressionTree.Node("3"),
            BinaryExpressionTree.Node("2")
        ),
        BinaryExpressionTree.Node(
            "+",
            BinaryExpressionTree.Node("4"),
            BinaryExpressionTree.Node("5")
        )
    )
)

println(tree.calc())