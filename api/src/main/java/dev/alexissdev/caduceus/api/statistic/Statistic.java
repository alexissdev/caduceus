package dev.alexissdev.caduceus.api.statistic;

import dev.alexissdev.storage.mongo.codec.DocumentCodec;
import dev.alexissdev.storage.mongo.codec.DocumentWriter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.Document;

@Data
@Builder
@AllArgsConstructor
public class Statistic
        implements DocumentCodec {

    private final int value;

    @Override
    public Document serialize() {
        return DocumentWriter.create()
                .write("value", value)
                .end();
    }
}
