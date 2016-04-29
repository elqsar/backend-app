package dk.cngroup.hakka.timur;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimurAssignment {
    private LocalDate start;
    private LocalDate end;

    boolean overlaps(TimurAssignment interval) {
        Objects.requireNonNull(interval, "interval");
        return interval.equals(this) || this.start.compareTo(interval.end) <= 0 && interval.start.compareTo(this.end) <= 0;
    }

    boolean touches(TimurAssignment interval) {
        Objects.requireNonNull(interval, "interval");
        return this.end.plusDays(1).isEqual(interval.start) || this.start.plusDays(1).isEqual(end);
    }

}
