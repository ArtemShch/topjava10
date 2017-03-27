package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> userMealWithExceeds = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        
        for (UserMealWithExceed exceed : userMealWithExceeds)
            System.out.println("exceed = " + exceed);

    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // группируем по дням
        Map<LocalDate, List<UserMeal>> dateListMap = mealList.stream()
                .collect(Collectors.groupingBy(u -> u.getDateTime().toLocalDate()));

        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();

        for (Map.Entry<LocalDate, List<UserMeal>> entry : dateListMap.entrySet())
        {
            int caloriesPerDayCount = 0;
            for (UserMeal meal: entry.getValue())
            {
                caloriesPerDayCount += meal.getCalories();
            }

            List<UserMeal> userMealsFiltred = entry.getValue().stream().filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime)).collect(Collectors.toList());

            if (caloriesPerDayCount > caloriesPerDay)
                for (UserMeal meal : userMealsFiltred)
                    userMealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),true));
            else
                for (UserMeal meal : userMealsFiltred)
                    userMealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),false));
        }


        return userMealWithExceeds;
    }
}
