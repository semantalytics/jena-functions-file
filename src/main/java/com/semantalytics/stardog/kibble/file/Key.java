package com.semantalytics.stardog.kibble.file;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import static com.complexible.common.rdf.model.Values.literal;

public final class Key extends AbstractFunction implements UserDefinedFunction {

    public Key() {
        super(1, FileVocabulary.key.stringValue());
    }

    public Key(final Key key) {
        super(key);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        assertIRI(values[0]);
        Path path = Paths.get(URI.create(values[0].stringValue()));
        BasicFileAttributes basicFileAttributes;
        try {
            basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e) {
            throw new ExpressionEvaluationException("Unable to read file attributes");
        }
        return literal(basicFileAttributes.fileKey().toString());
    }

    @Override
    public Function copy() {
        return new Key(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return FileVocabulary.key.name();
    }
}
