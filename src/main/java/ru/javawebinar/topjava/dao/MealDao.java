package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

/*
 * Created by artem on 29.03.17.
 */
public class MealDao {

    public void addMeal(Meal meal)
    {
        try
        {
            MealsUtil.meals.add(meal);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteMeal(int id)
    {
        try
        {
            for (Meal meal : MealsUtil.meals)
                if (meal.getId() == id)
                {
                    MealsUtil.meals.remove(meal);
                    break;
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateMeal(Meal meal)
    {
        try
        {
            for (Meal m : MealsUtil.meals)
                if (meal.getId() == m.getId())
                {
                    MealsUtil.meals.set(MealsUtil.meals.indexOf(m), meal);
                    break;
                }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Meal> getAllMeals()
    {
        return MealsUtil.meals;
    }

    public Meal getMealById (int mealId)
    {
        for (Meal meal : MealsUtil.meals)
            if (meal.getId() == mealId)
            {
                return meal;
            }

        return null;
    }


}
