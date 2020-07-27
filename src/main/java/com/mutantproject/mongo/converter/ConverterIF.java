package com.mutantproject.mongo.converter;

import org.bson.Document;

public interface ConverterIF<T> {
    public T convertToModel(Document document);
    public Document convertToMongoDocument(T model);
}