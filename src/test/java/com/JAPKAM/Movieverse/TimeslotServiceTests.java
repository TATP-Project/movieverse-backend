package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Timeslot;
import com.JAPKAM.Movieverse.repository.TimeslotRepository;
import com.JAPKAM.Movieverse.service.TimeslotService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TimeslotServiceTests {

    @Mock
    TimeslotRepository timeslotRepository;

    @InjectMocks
    TimeslotService timeslotService;

    public static final Date TIMESLOT_ONE = new Date(2022,12,16,16,30);
    public static final Date TIMESLOT_TWO = new Date(2022,12,17,17,30);
    public static final Date TIMESLOT_THREE = new Date(2022,12,18,18,30);
    public static final Date TIMESLOT_FOUR = new Date(2022,12,19,19,30);

    @Test
    void should_return_all_timeslots_when_findAll_given_timeslots() throws Exception {
        //given
        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        Timeslot timeslot2 = new Timeslot(new ObjectId().toString(), TIMESLOT_TWO);
        Timeslot timeslot3 = new Timeslot(new ObjectId().toString(), TIMESLOT_THREE);
        Timeslot timeslot4 = new Timeslot(new ObjectId().toString(), TIMESLOT_FOUR);

        List<Timeslot> timeslots = Arrays.asList(timeslot1, timeslot2, timeslot3, timeslot4);
        when(timeslotRepository.findAll()).thenReturn(timeslots);
        //when

        List<Timeslot> returnedTimeslots = timeslotService.findAll();
        //then
        assertThat(returnedTimeslots, hasSize(4));
        assertThat(returnedTimeslots.get(0), equalTo(timeslot1));
        assertThat(returnedTimeslots.get(1), equalTo(timeslot2));
        assertThat(returnedTimeslots.get(2), equalTo(timeslot3));
        assertThat(returnedTimeslots.get(3), equalTo(timeslot4));
        verify(timeslotRepository).findAll();

    }

    @Test
    void should_return_timeslot_3_when_find_by_id_given_id() throws Exception {
        //given

        Timeslot timeslot3 = new Timeslot(new ObjectId().toString(), TIMESLOT_THREE);

        when(timeslotRepository.findById(timeslot3.getId())).thenReturn(Optional.of(timeslot3));
        //when

        Timeslot returnedTimeslot = timeslotService.findById(timeslot3.getId());
        //then

        assertThat(returnedTimeslot,equalTo(timeslot3));
        verify(timeslotRepository).findById(timeslot3.getId());
    }
}
