package calcHelpers

import java.lang.IllegalArgumentException
import java.util.*
import kotlin.collections.ArrayList

class RPNBuilder {
    companion object {
        private val LEFT_ASSOC : Int = 0;
        private  val RIGHT_ASSOC = 0;

        private val OPERATORS = hashMapOf(
                "-" to intArrayOf(0, LEFT_ASSOC),
                "+" to intArrayOf(0, LEFT_ASSOC),
                "*" to intArrayOf(5, LEFT_ASSOC),
                "/" to intArrayOf(5, LEFT_ASSOC)
        )

        private fun isOperator(token: String) : Boolean {
            return OPERATORS.containsKey(token);
        }

        private fun isAssociative(token: String, type: Int) : Boolean {
            if(!isOperator(token)) {
                throw IllegalArgumentException("Invalid Token: $token")
            }
            if(OPERATORS[token]?.get(1)  == type) {
                return true
            }

            return false
        }

        private fun computePrecedence(token1 : String, token2: String) : Int {
            if (!isOperator(token1) || !isOperator(token2)) {
                throw IllegalArgumentException("Invalid tokens: " + token1
                        + " " + token2);
            }

            var firstValue = OPERATORS[token1]!![0]
            var secondValue = OPERATORS[token2]!![0]
            return firstValue - secondValue;
        }

        private fun infixToRPN(inputTokens : Array<String>) : Array<String>{
            var out = ArrayList<String>()
            var stack = Stack<String>();

            inputTokens.forEach { token ->
                if(isOperator(token)) {
                    while(!stack.empty() && isOperator(stack.peek())) {
                        if((isAssociative(token, LEFT_ASSOC) && computePrecedence(token, stack.peek()) <= 0) ||
                                ((isAssociative(token, RIGHT_ASSOC) && computePrecedence(
                                        token, stack.peek()) < 0))) {
                            out.add((stack.pop()))
                            continue;
                        }
                        break;
                    }
                    stack.push(token)
                } else {
                    out.add(token)
                }
            }

            while(!stack.empty()) {
                out.add(stack.pop())
            }

            return out.toTypedArray();
        }

        fun EvaluateRPN(infixExpression : Array<String>) : Int {

            var rpnExpression = infixToRPN(infixExpression);
            var stack = Stack<Int>()
            rpnExpression.forEach { token ->
                when (token) {
                    "+" -> {
                        stack.push(stack.pop() + stack.pop())
                        println(stack)
                    }
                    "-" -> {
                        var firstPop = stack.pop()
                        var secondPop = stack.pop()
                        stack.push(secondPop - firstPop)
                        println(stack)
                    }
                    "*" -> {
                        stack.push(stack.pop() * stack.pop())
                        println(stack)
                    }
                    "/" -> {
                        var firstPop = stack.pop()
                        var secondPop = stack.pop()
                        stack.push(secondPop / firstPop)
                        println(stack)
                    }
                    else -> {
                        stack.push(token.toInt())
                        println(stack)
                    }
                }
            }

            return stack.pop();
        }
    }

}