package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Timeslot;
import com.JAPKAM.Movieverse.repository.TimeslotMongoRepository;
import com.JAPKAM.Movieverse.service.TimeslotService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class TimeslotServiceTests {

    @Autowired
    TimeslotMongoRepository timeslotMongoRepository;

    @Autowired
    TimeslotService timeslotService;

    @BeforeEach
    public void clearDB(){
        timeslotMongoRepository.deleteAll();
    }

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
        timeslotMongoRepository.saveAll(Arrays.asList(timeslot1, timeslot2, timeslot3, timeslot4));
        //when

        List<Timeslot> timeslots = timeslotService.findAll();
        //then
        assertThat(timeslots, hasSize(4));
        assertThat(timeslots.get(0).getId(), equalTo(timeslot1.getId()));
        assertThat(timeslots.get(0).getStartDateTime(), equalTo(timeslot1.getStartDateTime()));
        assertThat(timeslots.get(1).getId(), equalTo(timeslot2.getId()));
        assertThat(timeslots.get(1).getStartDateTime(), equalTo(timeslot2.getStartDateTime()));
        assertThat(timeslots.get(2).getId(), equalTo(timeslot3.getId()));
        assertThat(timeslots.get(2).getStartDateTime(), equalTo(timeslot3.getStartDateTime()));
        assertThat(timeslots.get(3).getId(), equalTo(timeslot4.getId()));
        assertThat(timeslots.get(3).getStartDateTime(), equalTo(timeslot4.getStartDateTime()));

    }

    @Test
    void should_return_timeslot_3_when_find_by_id_given_id() throws Exception {
        //given
        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        Timeslot timeslot2 = new Timeslot(new ObjectId().toString(), TIMESLOT_TWO);
        Timeslot timeslot3 = new Timeslot(new ObjectId().toString(), TIMESLOT_THREE);
        Timeslot timeslot4 = new Timeslot(new ObjectId().toString(), TIMESLOT_FOUR);
        timeslotMongoRepository.saveAll(Arrays.asList(timeslot1, timeslot2, timeslot3, timeslot4));
        //when

        Timeslot returnedTimeslot = timeslotService.findById(timeslot3.getId());
        //then

        assertThat(returnedTimeslot.getId(), equalTo(timeslot3.getId()));
        assertThat(returnedTimeslot.getStartDateTime(), equalTo(timeslot3.getStartDateTime()));
    }
}
