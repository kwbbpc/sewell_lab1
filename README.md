

#Running the program

This project can be run straight from the command line using:

`java -cp . com.lab1.Main postfix1.prob`

- the -cp provides the classpath which here is shown as ".", meaning current directory.

- java will need the full name of your main aplication to run it (which is the package name).
Packages are represented as the directory heirarchy... i.e. com.lab1.Main = com/lab1/Main.class

- The argument provided after the main class is the filename to load.  This file must be located on the
classpath of your application (it has to be on the filepath specified by -cp above.

This command is meant to be run from the compiled code's build directory (the directory all of your class files get shoved to.).  This directory
will have all of your resource files (postfix1.*) in the top level.


#The problem.
ABC*+DE-/

You need two stacks for this problem.
1 stack should hold all of the chars of the postfix expression.
The other stack should start out empty, and it's going to be used to
store our operands as we pop them off the postfix expression stack
looking for an operator.  So your stacks look like:

<pre>
       TOP OF STACK             BOTTOM
          v                       v
Postfix: [A, B, C, *, +, D, E, -, /]
Operand: []

Postfix: [B, C, *, +, D, E, -, /]
Operand: [A]

Postfix: [C, *, +, D, E, -, /]
Operand: [B, A]


        next value = operator
          v
Postfix: [*, +, D, E, -, /]
Operand: [C, B, A]
//Since the next value is an operator, pop 2 values off the operand stack and
// apply the operator to them as second operand (operator) first operand
// i.e.   stack1 = op.pop(); stack2 = op.pop(); var = stack2 * stack1

Postfix: [+, D, E, -, /] -> current operator *
Operand: [A]  -> LD B, MUL C, ST TEMP1
//Store the temp on the stack and continue

Postfix: [+, D, E, -, /]
Operand: [TEMP1, A]

       next value = operator
          v
Postfix: [+, D, E, -, /]
Operand: [TEMP1, A]
//pop 2 values off the stack and apply the operator to them as stack[1] + stack[0]

Postfix: [+, D, E, -, /] -> current operator +
Operand: []  -> LD A, AD TEMP1, ST TEMP2
//Store the temp back on the stack

        next value = operand
          v
Postfix: [D, E, -, /]
Operand: [TEMP2]


Postfix: [E, -, /]
Operand: [D, TEMP2]

        next value = operator
          v
Postfix: [-, /]
Operand: [E, D, TEMP2]
//pop 2 values off the stack & apply, then push result back to stack


Postfix: [/] -> current operator -
Operand: [E, D, TEMP2] -> LD D, SB E, ST TEMP3


Postfix: [/]
Operand: [TEMP3, TEMP2]
//pop 2 values off the stack & apply, then push result back to stack
Postfix: []
Operand: [TEMP3, TEMP2] -> LD TEMP2, DV TEMP3, ST TEMP4

//postfix is empty so we're done!

</pre>