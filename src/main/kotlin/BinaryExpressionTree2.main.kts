#!/usr/bin/env kotlin

// This is the exact same problem as BinaryExpressionTree, but implemented in very kotlin-pedantic way

// The implementation takes advantage of sealed interfaces (Node) and providing possible implementations
// inside the interface itself (Number and Operation). The benefit of that is we no longer need to parse strings
// as before, because now there is a strict type related to each "entity". The other implementation relied on strings.

class BinaryExpressionTree(private val root: Node) {

    // Node interface that groups the two possible implementations inside,
    // This way the compiler knows that we exhausted all the options in the "when"
    // and there is no need for "else",
    sealed interface Node {
        data class Number(val value: Int) : Node // Number implements Node.
        data class Operation(
            val operator: Char,
            val left: Node, // Notice we no longer need Node? = null as in the previous impl.
            val right: Node // Notice we no longer need Node? = null as in the previous impl.
        ) : Node // Operation implements Node.
    }

    fun calc(): Int = calc(root)

    // We no longer need to parse strings, because we differentiated between Number and Operation.
    // Also, since the sealed interface declared two and only two implementations, the compiler
    // knows that all options are exhausted.
    private fun calc(node: Node): Int = when (node) {
        is Node.Number -> node.value
        is Node.Operation ->
            when (node.operator) {
                '+' -> calc(node.left) + calc(node.right)
                '-' -> calc(node.left) - calc(node.right)
                '*' -> calc(node.left) * calc(node.right)
                '/' -> calc(node.left) / calc(node.right)
                else -> error("Unknown operator: ${node.operator}")
            }
    }
}

// The usage has changed, now we fully qualify the nodes.
val tree = BinaryExpressionTree(
    BinaryExpressionTree.Node.Operation(
        '*',
        BinaryExpressionTree.Node.Operation(
            '+',
            BinaryExpressionTree.Node.Number(3),
            BinaryExpressionTree.Node.Number(2)
        ),
        BinaryExpressionTree.Node.Operation(
            '+',
            BinaryExpressionTree.Node.Number(4),
            BinaryExpressionTree.Node.Number(5)
        )
    )
)

println(tree.calc())