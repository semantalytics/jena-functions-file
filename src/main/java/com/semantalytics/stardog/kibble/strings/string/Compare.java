package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Compare extends AbstractFunction implements StringFunction {

    protected Compare() {
        super(2, StringVocabulary.compare.stringValue());
    }

    private Compare(final Compare compare) {
        super(compare);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string1 = assertStringLiteral(values[0]).stringValue();
        final String string2 = assertIntegerLiteral(values[1]).stringValue();

        return Values.literal(StringUtils.compare(string1, string2));
    }

    @Override
    public Compare copy() {
        return new Compare(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.compare.name();
    }
}
