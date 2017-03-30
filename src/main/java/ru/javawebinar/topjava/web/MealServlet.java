package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/*
 * Created by artem on 28.03.17.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LOG.debug("redirect to meals");
//        List<MealWithExceed> mealWithExceeds =
//                MealsUtil.getFilteredWithExceeded(MealsUtil.meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
//       request.setAttribute("meals", mealWithExceeds);
//       getServletConfig().getServletContext()
//                .getRequestDispatcher("/meals.jsp").forward(request,response);
//    }

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private MealDao dao;

    public MealServlet() {
        super();
        dao = new MealDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        System.out.println("action = " + action);

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.deleteMeal(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMeals());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")){
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMeals());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Meal meal = null;
//        meal.setFirstName(request.getParameter("firstName"));
//        user.setLastName(request.getParameter("lastName"));
//        try {
//            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
//            user.setDob(dob);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        user.setEmail(request.getParameter("email"));
//        String userid = request.getParameter("userid");
//        if(userid == null || userid.isEmpty())
//        {
//            dao.addUser(user);
//        }
//        else
//        {
//            user.setUserid(Integer.parseInt(userid));
//            dao.updateUser(user);
//        }
//        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
//        request.setAttribute("users", dao.getAllUsers());
//        view.forward(request, response);
    }

}
