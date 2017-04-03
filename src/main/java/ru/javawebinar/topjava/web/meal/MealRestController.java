package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;

//http://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial
@Controller
public class MealRestController {
    private MealService service;

    @Autowired(required=true)
    public void setMealService(MealService ms){
        this.service = ms;
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String listMeals(Model model) {
       // model.addAttribute("meal", new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        model.addAttribute("listMeals", MealsUtil.getWithExceeded(this.service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
        return "meals";
    }

    @RequestMapping(value= "/meal", method = RequestMethod.POST)
    public String addMeal(@ModelAttribute("meal") Meal m){

        if(m.getId() == 0){
            //new person, add it
            this.service.save(m);
        }else{
            //existing person, call update
            this.service.update(m);
        }

        return "redirect:/meals";

    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        this.service.delete(id);
        return "redirect:/meals";
    }

    @RequestMapping("/edit/{id}")
    public String editMeal(@PathVariable("id") int id, Model model){
        model.addAttribute("meal", this.service.get(id));
        model.addAttribute("listMeals", this.service.getAll());
        return "meal";
    }

}