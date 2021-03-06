
package com.semantalytics.stardog.kibble.file;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.file.Paths;

import static com.complexible.common.rdf.model.Values.*;

public class IsSymbolicLink extends AbstractFunction implements UserDefinedFunction {

    IsSymbolicLink() {
        super(1, FileVocabulary.isSymbolicLink.stringValue());
    }

    private IsSymbolicLink(final IsSymbolicLink contentType) {
        super(contentType);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String file = assertStringLiteral(values[0]).stringValue();

        return literal(Files.isSymbolicLink(Paths.get(file)));
    }

    @Override
    public Function copy() {
        return new IsSymbolicLink(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return FileVocabulary.isSymbolicLink.name();
    }
}
