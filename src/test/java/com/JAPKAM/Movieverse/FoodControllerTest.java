package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Food;
import com.JAPKAM.Movieverse.repository.FoodRepository;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodControllerTest {
    public static final String FOOD_NAME_1 = "food 1";
    public static final double PRICE1 = 10.0;
    public static final String FOOD_NAME_2 = "food 2";
    public static final double PRICE2 = 20.5;
    public static final String HOTDOG = "hotdog";
    public static final String DRINK = "drink";
    @Autowired
    MockMvc client;

    @Autowired
    FoodRepository foodRepository;



    @Test
    void should_return_all_foods_when_find_all_given_foods() throws Exception {
        //given
        Binary image1 = new Binary(new byte[1]);
        Binary image2 = new Binary(new byte[1]);
        Food food1 = new Food(new ObjectId().toString(), FOOD_NAME_1, PRICE1,image1, HOTDOG);
        Food food2 = new Food(new ObjectId().toString(), FOOD_NAME_2, PRICE2,image2, DRINK);
        foodRepository.save(food1);
        foodRepository.save(food2);
        //when
        client.perform(MockMvcRequestBuilders.get("/foods"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(FOOD_NAME_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(FOOD_NAME_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value(HOTDOG))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value(DRINK));
    }
}
