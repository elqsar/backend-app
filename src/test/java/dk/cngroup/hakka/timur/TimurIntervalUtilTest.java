package dk.cngroup.hakka.timur;

import com.google.common.collect.ImmutableList;
import lombok.val;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TimurIntervalUtilTest {

    @Test
    public void shouldReturnEmptyList_WhenInputIsEmpty() {
        val result = TimurIntervalUtil.mergeIntervals(emptyList());
        assertThat(result, is(hasSize(0)));
    }

    @Test
    public void shouldReturnOneAssignmentBack() {
        val result = merge(assignment("2010-01-01", "2010-01-02"));
        assertThat(result, is(hasSize(1)));

        val expectedAssignment = assignment("2010-01-01", "2010-01-02");
        assertThat(result.get(0), equalTo(expectedAssignment));
    }

    @Test
    public void shouldNotMergeInterval_WhenIntervalsDoNotOverlap() {
        val firstAssignment = assignment("2010-01-01", "2010-01-05");
        val secondAssignment = assignment("2010-01-07", "2010-01-10");

        val result = merge(firstAssignment, secondAssignment);
        assertThat(result, is(hasSize(2)));
    }

    @Test
    public void shouldMergeIntervals_WhenIntervalsOverlap() {
        val firstAssignment = assignment("2010-01-01", "2010-01-05");
        val secondAssignment = assignment("2010-01-04", "2010-01-10");

        val result = merge(firstAssignment, secondAssignment);
        assertThat(result, is(hasSize(1)));

        val expectedAssignment = assignment("2010-01-01", "2010-01-10");
        assertThat(result.get(0), equalTo(expectedAssignment));
    }

    @Test
    public void shouldMergeIntervals_WhenIntervals_Touch() {
        val result = merge(
                assignment("2010-02-01", "2010-02-02"),
                assignment("2010-02-03", "2010-02-04")
        );
        assertThat(result, equalTo(ImmutableList.of(
                assignment("2010-02-01", "2010-02-04")
        )));
    }


    @Test
    public void shouldMergeIntervals_WhenIntervalsOverlap_Overkill1() {
        val result = merge(
                assignment("2010-02-03", "2010-02-06"),
                assignment("2010-02-02", "2010-02-05"),
                assignment("2010-02-03", "2010-02-04")
        );
        assertThat(result, is(hasSize(1)));

        val expectedAssignment = assignment("2010-02-02", "2010-02-06");
        assertThat(result.get(0), equalTo(expectedAssignment));
    }

    @Test
    public void shouldMergeIntervals_WhenIntervalsOverlap_Overkill2() {
        val result = merge(
                assignment("2010-02-03", "2010-02-06"),
                assignment("2010-02-06", "2010-02-07"),
                assignment("2010-02-08", "2010-02-09")
        );
        assertThat(result, equalTo(ImmutableList.of(
                assignment("2010-02-03", "2010-02-09")
        )));
    }

    private static TimurAssignment assignment(String start, String end) {
        return new TimurAssignment(LocalDate.parse(start), LocalDate.parse(end));
    }

    private static List<TimurAssignment> merge(TimurAssignment... assignments) {
        return TimurIntervalUtil.mergeIntervals(Arrays.asList(assignments));
    }

}