package dk.cngroup.hakka.timur;

import lombok.val;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

public class TimurIntervalUtil {

    public static List<TimurAssignment> mergeIntervals(List<TimurAssignment> timurAssignmentList) {
        if (isEmpty(timurAssignmentList)) {
            return emptyList();
        } else if (timurAssignmentList.size() == 1) {
            return timurAssignmentList;
        }

        val sortedAssignments = timurAssignmentList.stream()
                .sorted((a1, a2) -> a1.getStart().equals(a2.getStart()) ? a1.getEnd().compareTo(a2.getEnd()) : a1.getStart().compareTo(a2.getStart()))
                .collect(toList());

        //proudly stolen from https://fenix-ashes.ist.utl.pt/open/trunk/fenix/src/main/java/net/sourceforge/fenixedu/util/IntervalUtils.java
        List<TimurAssignment> result = new LinkedList<>();
        TimurAssignment current = null;
        for (TimurAssignment assignment : sortedAssignments) {
            if (current == null) {
                current = assignment;
            } else if (!current.overlaps(assignment) && !current.touches(assignment)) {
                result.add(current);
                current = assignment;
            } else {
                current = mergeIntervals(current, assignment);
            }
        }
        if (current != null) {
            result.add(current);
        }

        return result;

    }

    private static TimurAssignment mergeIntervals(TimurAssignment i1, TimurAssignment i2) {
        LocalDate start = i1.getStart().isBefore(i2.getStart()) ? i1.getStart() : i2.getStart();
        LocalDate end = i1.getEnd().isAfter(i2.getEnd()) ? i1.getEnd() : i2.getEnd();
        return new TimurAssignment(start, end);
    }


}

