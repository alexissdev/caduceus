package dev.alexissdev.caduceus.api.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Statistic {

    private final int value;

    public static Statistic initial() {
        return Statistic.builder().value(0).build();
    }
}
