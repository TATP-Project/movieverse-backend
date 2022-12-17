package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.House;
import com.JAPKAM.Movieverse.repository.HouseMongoRepository;
import com.JAPKAM.Movieverse.service.HouseService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class HouseServiceTests {

    @Mock
    HouseMongoRepository houseMongoRepository;

    @InjectMocks
    HouseService houseService;

    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 20;
    public static final int HOUSE_ONE_COL_NUMBER = 20;
    public static final String HOUSE_TWO = "HOUSE TWO";
    public static final int HOUSE_TWO_ROW_NUMBER = 5;
    public static final int HOUSE_TWO_COL_NUMBER = 10;

    @Test
    void should_return_all_houses_when_findAll_given_houses() throws Exception {
        //given
        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
        List<House> houses = Arrays.asList(house1, house2);
        when(houseMongoRepository.findAll()).thenReturn(houses);
        //when
        List<House> returnedHouses = houseService.findAll();
        //then
        assertThat(returnedHouses, hasSize(2));
        assertThat(returnedHouses.get(0), equalTo(house1));
        assertThat(returnedHouses.get(1), equalTo(house2));
        verify(houseMongoRepository).findAll();
    }

    @Test
    void should_return_house_2_when_find_by_id_given_house_id() {
        //given
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
        when(houseMongoRepository.findById(house2.getId())).thenReturn(Optional.of(house2));

        //when
        House returnedHouse = houseService.findById(house2.getId());

        //then
        assertThat(returnedHouse, equalTo(house2));
        verify(houseMongoRepository).findById(house2.getId());
    }
}
