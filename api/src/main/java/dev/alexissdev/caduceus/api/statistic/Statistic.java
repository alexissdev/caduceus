package dev.alexissdev.caduceus.api.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.Document;
import team.unnamed.pixel.storage.mongo.codec.DocumentCodec;
import team.unnamed.pixel.storage.mongo.codec.DocumentWriter;

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
