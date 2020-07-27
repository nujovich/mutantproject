package com.mutantproject.mongo.codec;

import com.mutantproject.model.Dna;
import com.mutantproject.mongo.converter.DnaConverter;

import org.bson.BsonObjectId;
import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;;

public class DnaCodec implements CollectibleCodec<Dna> {

    private Codec<Document> documentCodec;

    @Autowired
    private DnaConverter dnaConverter;
    

    public DnaCodec(Codec<Document> documentCodec) {
        this.documentCodec = documentCodec;
    }

    @Override
    public void encode(BsonWriter writer, Dna value, EncoderContext encoderContext) {
        Document document = dnaConverter.convertToMongoDocument(value);
        documentCodec.encode(writer, document, encoderContext);
    }

    @Override
    public Class<Dna> getEncoderClass() {
        return Dna.class;
    }

    @Override
    public Dna decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        Dna dna = dnaConverter.convertToModel(document);
        return dna;
    }

    @Override
    public Dna generateIdIfAbsentFromDocument(Dna dna) {
        if (!documentHasId(dna)) {
            dna.setId(new ObjectId());
        }
        return dna;
    }

    @Override
    public boolean documentHasId(Dna dna) {
        return (dna.getId() != null);
    }

    @Override
    public BsonValue getDocumentId(Dna dna) {
        if (!documentHasId(dna)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonObjectId(dna.getId());
    }
    
}