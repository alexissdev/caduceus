package dev.alexissdev.caduceus.api.user;

import dev.alexissdev.caduceus.api.user.statistic.UserStatistic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.Document;
import team.unnamed.pixel.storage.model.Model;
import team.unnamed.pixel.storage.mongo.codec.DocumentCodec;
import team.unnamed.pixel.storage.mongo.codec.DocumentWriter;

@Data
@AllArgsConstructor
@Builder
public class User implements Model,
                             DocumentCodec {

    private final String id;
    private final String name;
    private final UserStatistic statistic;
    private String language;

    @Override
    public Document serialize() {
        return DocumentWriter.create()
                .write("_id", id)
                .write("name", name)
                .write("statistic", statistic)
                .write("language", language)
                .end();
    }
}
