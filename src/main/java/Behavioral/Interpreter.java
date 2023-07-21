package Behavioral;

/*
 解释器模式
 给定一个语言，定义它的文法表示，并定义一个解释器，这个解释器使用该标识来解释语言中的句子。
 */
public class Interpreter {

    public static Expression getMaleExpression() {
        Expression male = new TerminalExpression("male");
        Expression man = new TerminalExpression("man");
        return new OrExpression(male, man);
    }

    public static Expression getMarriedFemaleExpression() {
        Expression female = new TerminalExpression("female");
        Expression women = new TerminalExpression("women");
        Expression married = new TerminalExpression("married");
        return new AndExpression(married, new OrExpression(female, women));
    }


    public static void main(String[] args) {
        Expression maleExpression = getMaleExpression();
        Expression marriedFemaleExpression = getMarriedFemaleExpression();
        String s1 = "women married";
        String s2 = "male";
        String s3 = "female married";
        System.out.println(s2 + "：" + maleExpression.interpret(s2));
        System.out.println(s1 + "：" + marriedFemaleExpression.interpret(s1));
        System.out.println(s3 + "：" + marriedFemaleExpression.interpret(s3));
    }
}

interface Expression {
    boolean interpret(String context);
}

class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}

class OrExpression implements Expression {
    private Expression expression1;
    private Expression expression2;

    public OrExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) || expression2.interpret(context);
    }
}

class AndExpression implements Expression {
    private Expression expression1;
    private Expression expression2;

    public AndExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) && expression2.interpret(context);
    }
}