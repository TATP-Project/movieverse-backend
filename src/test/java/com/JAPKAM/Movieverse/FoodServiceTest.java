package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Food;
import com.JAPKAM.Movieverse.repository.FoodRepository;
import com.JAPKAM.Movieverse.service.FoodService;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class FoodServiceTest {
    public static final String FOOD_1 = "food 1";
    public static final double PRICE1 = 10.0;
    public static final String FOOD_2 = "food 2";
    public static final double PRICE2 = 20.5;
    public static final String HOTDOG = "hotdog";
    public static final String DRINK = "drink";
    @Mock
    FoodRepository foodRepository;

    @InjectMocks
    FoodService foodService;

    @Test
    void should_return_all_food_when_find_all_given_a_list_of_food() {
        //given
        Binary image1 = new Binary(new byte[1]);
        Binary image2 = new Binary(new byte[1]);
        Food food1 = new Food(new ObjectId().toString(), FOOD_1, PRICE1,image1, HOTDOG);
        Food food2 = new Food(new ObjectId().toString(), FOOD_2, PRICE2,image2, DRINK);
        List<Food> foods = new ArrayList<>(Arrays.asList(food1, food2));
        given(foodRepository.findAll()).willReturn(foods);

        //when
        List<Food> actualFood = foodService.findAll();
        //then
        assertThat(actualFood, hasSize(2));
        assertThat(actualFood.get(0), equalTo(food1));
        assertThat(actualFood.get(1), equalTo(food2));
    }

    @Test
    void should_return_food_when_find_by_id_given_food() {
        //given
        String id = new ObjectId().toString();
        Binary image = new Binary(new byte[1]);
        Food food = new Food(id, FOOD_1, PRICE1,image, HOTDOG);
        given(foodRepository.findById(id)).willReturn(Optional.of(food));
        //when
        Food result = foodService.findById(id);
        //then
        verify(foodRepository).findById(id);
        assertThat(result, equalTo(food));

    }

}
