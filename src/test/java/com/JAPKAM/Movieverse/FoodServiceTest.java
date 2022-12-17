package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.repository.FoodRepository;
import com.JAPKAM.Movieverse.service.FoodService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class FoodServiceTest {
    @Mock
    FoodRepository foodRepository;

    @InjectMocks
    FoodService foodService;
}
