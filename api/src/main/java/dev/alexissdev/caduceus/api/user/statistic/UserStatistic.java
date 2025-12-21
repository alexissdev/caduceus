package dev.alexissdev.caduceus.api.user.statistic;

import dev.alexissdev.caduceus.api.statistic.Statistic;
import dev.alexissdev.storage.mongo.codec.DocumentCodec;
import dev.alexissdev.storage.mongo.codec.DocumentWriter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.Document;

@Data
@AllArgsConstructor
@Builder
public class UserStatistic
        implements DocumentCodec {

    private final Statistic coins;
    private final Statistic gems;

    @Override
    public Document serialize() {
        return DocumentWriter.create()
                .write("coins", coins)
                .write("gems", gems)
                .end();
    }
}
