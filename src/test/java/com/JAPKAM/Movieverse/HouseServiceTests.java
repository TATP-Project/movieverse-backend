package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.House;
import com.JAPKAM.Movieverse.repository.HouseMongoRepository;
import com.JAPKAM.Movieverse.service.HouseService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
public class HouseServiceTests {

    @Autowired
    HouseMongoRepository houseMongoRepository;

    @Autowired
    HouseService houseService;

    @BeforeEach
    public void clearDB() {
        houseMongoRepository.deleteAll();
    }

    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 20;
    public static final int HOUSE_ONE_COL_NUMBER = 20;
    public static final String HOUSE_TWO = "HOUSE TWO";
    public static final int HOUSE_TWO_ROW_NUMBER = 5;
    public static final int HOUSE_TWO_COL_NUMBER = 10;
    public static final String HOUSE_THREE = "HOUSE THREE";
    public static final int HOUSE_THREE_ROW_NUMBER = 8;
    public static final int HOUSE_THREE_COL_NUMBER = 9;
    public static final String HOUSE_FOUR = "HOUSE FOUR";
    public static final int HOUSE_FOUR_ROW_NUMBER = 10;
    public static final int HOUSE_FOUR_COL_NUMBER = 20;

    @Test
    void should_return_all_houses_when_findAll_given_houses() throws Exception {
        //given
        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
        houseMongoRepository.save(house1);
        houseMongoRepository.save(house2);
        List<House> expectedHouses = houseMongoRepository.findAll();
        //when
        List<House> houses = houseService.findAll();
        //then
        assertThat(houses, hasSize(2));
        assertThat(houses.get(0).getId(), equalTo(expectedHouses.get(0).getId()));
        assertThat(houses.get(0).getName(), equalTo(expectedHouses.get(0).getName()));
        assertThat(houses.get(0).getNumberOfColumn(), equalTo(expectedHouses.get(0).getNumberOfColumn()));
        assertThat(houses.get(0).getNumberOfRow(), equalTo(expectedHouses.get(0).getNumberOfRow()));
        assertThat(houses.get(1).getId(), equalTo(expectedHouses.get(1).getId()));
        assertThat(houses.get(1).getName(), equalTo(expectedHouses.get(1).getName()));
        assertThat(houses.get(1).getNumberOfColumn(), equalTo(expectedHouses.get(1).getNumberOfColumn()));
        assertThat(houses.get(1).getNumberOfRow(), equalTo(expectedHouses.get(1).getNumberOfRow()));
    }

    @Test
    void should_return_house_2_when_find_by_id_given_house_id() {
        //given
        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
        houseMongoRepository.save(house1);
        houseMongoRepository.save(house2);

        //when
        House returnedHouse = houseService.findById(house2.getId());

        //then
        assertThat(returnedHouse.getId(), equalTo(house2.getId()));
        assertThat(returnedHouse.getName(), equalTo(house2.getName()));
        assertThat(returnedHouse.getNumberOfRow(), equalTo(house2.getNumberOfRow()));
        assertThat(returnedHouse.getNumberOfColumn(), equalTo(house2.getNumberOfColumn()));
    }
}
