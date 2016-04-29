package dk.cngroup.hakka.timur;

import lombok.val;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TimurAssignmentTest {

    @Test
    public void shouldReturnTrueForTouchingAssignments() {
        val assignment1 =  assignment("2015-01-01", "2015-01-02");
        val assignment2 =  assignment("2015-01-02", "2015-01-03");
        assertTrue(assignment1.touches(assignment2));
    }


    private static TimurAssignment assignment(String start, String end) {
        return new TimurAssignment(LocalDate.parse(start), LocalDate.parse(end));
    }


}