package com.semantalytics.stardog.lab.function;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class CapitalizeFully extends AbstractFunction implements UserDefinedFunction {

    public CapitalizeFully() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/capitalizeFully");
    }

    public CapitalizeFully(final CapitalizeFully capitalizeFully) {
        super(capitalizeFully);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(string.toUpperCase());
    }

    @Override
    public CapitalizeFully copy() {
        return new CapitalizeFully(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "capitalizeFully";
    }
}