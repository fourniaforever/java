package DAO;

import Entities.*;
import Entities.Process;
import SessionFactory.SessionFactoryUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UsersDAO implements IUsersDAO {

    private Scanner in;
    private SessionFactory sessionFactory;
    private IDishDAO dishDAO;
    private ICountryDAO countryDAO;
    private IGroupOfDishesDAO groupOfDishesDAO;
    private IHighRateDAO highRateDAO;
    private IProcessDAO processDAO;
    private IProductDAO productDAO;
    private IRateDAO rateDAO;

    public UsersDAO() {
        in = new Scanner(System.in);
        sessionFactory = SessionFactoryUtil.getSessionFactory();
        dishDAO = new DishDAO();
        countryDAO = new CountryDAO();
        groupOfDishesDAO = new GroupOfDishesDAO();
        highRateDAO = new HighRateDAO();
        processDAO = new ProcessDAO();
        productDAO = new ProductsDAO();
        rateDAO = new RateDAO();
    }

    public void authorize() throws JsonProcessingException, ParseException {
        System.out.println("Login:");
        String login = in.nextLine();
        System.out.println("Password:");
        int passwordHashCode = in.nextLine().hashCode();
        if (getNeedUsers(login, passwordHashCode).size() > 0) {
            menu();
        } else {
            System.out.println("Wrong login or/and password.");
            authorize();
        }
    }

    public void registrate() throws JsonProcessingException, ParseException {
        System.out.println("Create login:");
        String login = in.nextLine();
        System.out.println("Create password:");
        int passwordHashCode = in.nextLine().hashCode();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new Users(login, passwordHashCode));
        transaction.commit();
        session.close();
        menu();
    }

    public List<Users> getNeedUsers(String login, int password) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Users.class).add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        List<Users> usersList = criteria.list();
        return usersList;
    }

    public void start() throws JsonProcessingException, ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose:");
        System.out.println("1 - Sign In");
        System.out.println("2 - Sign up");
        System.out.println("3 - Sign out");
        try {
            switch (in.nextInt()) {
                case 1: {
                    authorize();
                }
                break;
                case 2: {
                    registrate();
                }
                break;
                case 3: {
                    System.exit(0);
                }
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong parameter");
        }
    }


    public void menuDish() throws JsonProcessingException, ParseException {
        System.out.println("Choose 1 of actions below:");
        System.out.println("1 - Add dish");
        System.out.println("2 - Delete dish");
        System.out.println("3 - Look at the list of dishes");
        System.out.println("4 - Search dish");
        System.out.println("5 - Back to main menu");
        try {
            switch (in.nextInt()) {
                case 1: {
                    System.out.println("Name of the dish:");
                    in.nextLine();
                    String name = in.nextLine();
                    System.out.println("Weight of the dish:");
                    double weight = in.nextDouble();
                    System.out.println("Callories of the dish:");
                    double callories = in.nextDouble();
                    System.out.println("Is this dish vegan? If yes - enter 1,else - 0:");
                    int isvegan = in.nextInt();
                    System.out.println("Price of dish:");
                    double price = in.nextDouble();
                    System.out.println("EXP of dish:");
                    int exp = in.nextInt();
                    dishDAO.addDish(new Dish(name, weight, callories, isvegan, price, exp));
                    menuDish();
                }
                break;
                case 2: {
                    System.out.println("ID of dish:");
                    int id = in.nextInt();
                    System.out.println("Do you really want to delete dish?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    switch (in.nextInt()) {
                        case 1: {
                            if (dishDAO.searchDishId(id).size() > 0) {
                                dishDAO.deleteDish(id);
                            } else {
                                System.out.println("Dish with that id does not exist");
                            }
                            menuDish();
                        }
                        break;
                        case 2: {
                            menuDish();
                        }
                        break;
                    }
                }
                break;

                case 3: {
                    List<Dish> dishList = dishDAO.getAllDish();
                    if (dishList.size() > 0) {
                        for (Dish dish : dishList) {
                            String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(dish);
                            System.out.println(json);
                        }
                        menuDish();
                    }  else {
                        System.out.println("List of dishes is empty.");
                    }
                    menuDish();
                }
                break;
                case 4: {
                    System.out.println("Variation of search:");
                    System.out.println("1 - Id");
                    System.out.println("2 - Name");
                    switch (in.nextInt()) {
                        case 1: {
                            System.out.println("Id of dish:");
                            int id = in.nextInt();
                            List<Dish> dishesList = dishDAO.searchDishId(id);
                            if (dishesList.size() > 0) {
                                for (Dish item : dishesList) {
                                    String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                    System.out.println(json);
                                }
                            } else {
                                System.out.println("No results.");
                            }
                        }
                        break;
                        case 2: {
                            System.out.println("Name of the dish:");
                            in.nextLine();
                            String name = in.nextLine();
                            List<Dish> dishesList = dishDAO.searchDishByName(name);
                            if (dishesList.size() > 0) {
                                for (Dish item : dishesList) {
                                    String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                    System.out.println(json);
                                }
                                menuDish();
                            } else {
                                System.out.println("No results.");
                                menuDish();
                            }
                        }
                        break;
                    }
                }
                break;
                case 5: {
                    menu();
                }
                break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong parameter");
        }
    }


    public void menuCountries() throws JsonProcessingException, ParseException {
        System.out.println("Choose 1 of actions below:");
        System.out.println("1 - Add country");
        System.out.println("2 - Delete country");
        System.out.println("3 - Look at the list of countries");
        System.out.println("4 - Search country");
        System.out.println("5 - Back to main menu");
        try {
            switch (in.nextInt()) {
                case 1: {
                    System.out.println("Name of the country:");
                    in.nextLine();
                    String name = in.nextLine();
                    countryDAO.addCountry(new Countries(name));
                    menuCountries();
                }
                break;
                case 2: {
                    System.out.println("ID of country:");
                    int id = in.nextInt();
                    System.out.println("Do you really want to delete country?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    switch (in.nextInt()) {
                        case 1: {
                            if (countryDAO.searchCountryId(id).size() > 0) {
                                countryDAO.deleteCountry(id);
                            } else {
                                System.out.println("Country with that id does not exist");
                            }
                            menuCountries();
                        }
                        break;
                        case 2: {
                            menuCountries();
                        }
                        break;
                    }
                }
                break;
                case 3: {
                    List<Countries> countriesList = countryDAO.getAllCountries();
                    if (countriesList.size() > 0) {
                        for (Countries country : countriesList) {
                            String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(country);
                            System.out.println(json);
                        }
                        menuCountries();
                    } else {
                        System.out.println("List of  countries is empty.");
                        menuCountries();
                    }
                }
                break;
                case 4: {
                    System.out.println("Variation of search:");
                    System.out.println("1 - Id");
                    System.out.println("2 - Name");
                    switch (in.nextInt()) {
                        case 1: {
                            System.out.println("Id of country:");
                            int id = in.nextInt();
                            List<Countries> countriesList = countryDAO.searchCountryId(id);
                            if (countriesList.size() > 0) {
                                for (Countries item : countriesList) {
                                    String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                    System.out.println(json);
                                }
                                menuCountries();
                            } else {
                                System.out.println("No results.");
                                menuCountries();
                            }
                        }
                        break;
                        case 2: {
                            System.out.println("Name of the country:");
                            in.nextLine();
                            String name = in.nextLine();
                            List<Countries> countriesList = countryDAO.searchCountriesByName(name);
                            if (countriesList.size() > 0) {
                                for (Countries item : countriesList) {
                                    String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                    System.out.println(json);
                                }
                                menuCountries();
                            } else {
                                System.out.println("No results.");
                                menuCountries();
                            }
                        }
                        break;
                    }
                }
                break;
                case 5: {
                    menu();
                }
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong parameter");
        }
    }

    public void menuGroupOfDishes() throws JsonProcessingException, ParseException {
        System.out.println("Choose 1 of actions below:");
        System.out.println("1 - Add group of dishes");
        System.out.println("2 - Delete group of dishes");
        System.out.println("3 - Look at the list of groups of dishes");
        System.out.println("4 - Search group of dishes");
        System.out.println("5 - Back to main menu");
       try{
           switch (in.nextInt()) {
            case 1: {
                System.out.println("Name of the group of dishes:");
                in.nextLine();
                String name = in.nextLine();
                groupOfDishesDAO.addGroupOfDishes(new GroupOfDishes(name));
                menuGroupOfDishes();
            }
            break;
            case 2: {
                System.out.println("ID of group of dishes:");
                int id = in.nextInt();
                System.out.println("Do you really want to delete group of dishes?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                switch (in.nextInt()) {
                    case 1: {
                        if (groupOfDishesDAO.searchGroupOfDishesById(id).size() > 0) {
                            groupOfDishesDAO.deleteGroupOfDishes(id);
                        } else {
                            System.out.println("Group of dishes with that id does not exist");
                        }
                        menuGroupOfDishes();
                    }
                    break;
                    case 2: {
                        menuGroupOfDishes();
                    }
                    break;
                }
            }
            break;

            case 3: {
                List<GroupOfDishes> groupOfDishesList = groupOfDishesDAO.getAllGroups();
                if (groupOfDishesList.size() > 0) {
                    for (GroupOfDishes groupOfDishes : groupOfDishesList) {
                        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(groupOfDishes);
                        System.out.println(json);
                    }
                    menuGroupOfDishes();
                } else {
                    System.out.println("List of groups is empty.");
                    menuGroupOfDishes();
                }
            }
            break;
            case 4: {
                System.out.println("Variation of search:");
                System.out.println("1 - Id");
                System.out.println("2 - Name");
                switch (in.nextInt()) {
                    case 1: {
                        System.out.println("Id of group of dishes:");
                        int id = in.nextInt();
                        List<GroupOfDishes> groupOfDishesList = groupOfDishesDAO.searchGroupOfDishesById(id);
                        if (groupOfDishesList.size() > 0) {
                            for (GroupOfDishes item : groupOfDishesList) {
                                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                System.out.println(json);
                            }
                            menuGroupOfDishes();
                        } else {
                            System.out.println("No results.");
                            menuGroupOfDishes();
                        }
                    }
                    break;
                    case 2: {
                        System.out.println("Name of the group:");
                        in.nextLine();
                        String name = in.nextLine();
                        List<GroupOfDishes> groupOfDishesList = groupOfDishesDAO.searchGroupOfDishesByName(name);
                        if (groupOfDishesList.size() > 0) {
                            for (GroupOfDishes item : groupOfDishesList) {
                                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                System.out.println(json);
                            }
                            menuGroupOfDishes();
                        } else {
                            System.out.println("No results.");
                            menuGroupOfDishes();
                        }
                    }
                    break;
                }
            }
            break;
            case 5: {
                menu();
            }
            break;
        }
    }
        catch (InputMismatchException e) {
            System.out.println("Wrong parameter");
        }
    }
    public void menuProducts() throws JsonProcessingException, ParseException {
        System.out.println("Choose 1 of actions below:");
        System.out.println("1 - Add product");
        System.out.println("2 - Delete product");
        System.out.println("3 - Look at the list of products");
        System.out.println("4 - Search product");
        System.out.println("5 - Back to main menu");
        try {
            switch (in.nextInt()) {
            case 1: {
                System.out.println("Name of the product:");
                in.nextLine();
                String name = in.nextLine();
                System.out.println("Weight of the product:");
                double weight = in.nextDouble();
                System.out.println("Callories of the product:");
                double callories = in.nextDouble();
                System.out.println("Is this product vegan? If yes - enter 1,else - 0:");
                int isvegan = in.nextInt();
                System.out.println("Price of product:");
                double price = in.nextDouble();
                System.out.println("EXP of product:");
                int exp = in.nextInt();
                productDAO.addProduct(new Products(name, weight, callories, isvegan, price, exp));
                menuProducts();
            }
            break;
            case 2: {
                System.out.println("ID of group of product:");
                int id = in.nextInt();
                System.out.println("Do you really want to delete product?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                switch (in.nextInt()) {
                    case 1: {
                        if (productDAO.searchProductsById(id).size() > 0) {
                            productDAO.deleteProduct(id);
                        } else {
                            System.out.println("Product with that id does not exist");
                        }
                        menuProducts();
                    }
                    break;
                    case 2: {
                        menuProducts();
                    }
                    break;
                }
            }
            break;

            case 3: {
                List<Products> productList = productDAO.getAllProducts();
                if (productList.size() > 0) {
                    for (Products product : productList) {
                        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(product);
                        System.out.println(json);
                    }
                    menuProducts();
                } else {
                    System.out.println("List of products is empty.");
                    menuProducts();
                }
            }
            break;
            case 4: {
                System.out.println("Variation of search:");
                System.out.println("1 - Id");
                System.out.println("2 - Name");
                switch (in.nextInt()) {
                    case 1: {
                        System.out.println("Id of product:");
                        int id = in.nextInt();
                        List<Products> productList = productDAO.searchProductsById(id);
                        if (productList.size() > 0) {
                            for (Products item : productList) {
                                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                System.out.println(json);
                            }
                            menuProducts();
                        } else {
                            System.out.println("No results.");
                            menuProducts();
                        }
                    }
                    break;
                    case 2: {
                        System.out.println("Name of the product:");
                        in.nextLine();
                        String name = in.nextLine();
                        List<Products> productList = productDAO.searchProductsByName(name);
                        if (productList.size() > 0) {
                            for (Products item :productList) {
                                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                System.out.println(json);
                            }
                            menuProducts();
                        } else {
                            System.out.println("No results.");
                            menuProducts();
                        }
                    }
                    break;
                }
            }
            break;
            case 5:{
                menu();
            }
            break;
        }
    }
        catch (InputMismatchException e) {
        System.out.println("Wrong parameter.");
        }
     }
    public void menuProcess() throws JsonProcessingException, ParseException {
        System.out.println("Choose 1 of actions below:");
        System.out.println("1 - Add process");
        System.out.println("2 - Delete process");
        System.out.println("3 - Look at the list of processes");
        System.out.println("4 - Search process");
        System.out.println("5 - Back to main menu");
        try {
            switch (in.nextInt()) {
                case 1: {
                    System.out.println("Name of the process:");
                    in.nextLine();
                    String name = in.nextLine();
                    processDAO.addProcess(new Process(name));
                    menuProcess();
                }
                break;
                case 2: {
                    System.out.println("ID of group of process:");
                    int id = in.nextInt();
                    System.out.println("Do you really want to delete process?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    switch (in.nextInt()) {
                        case 1: {
                            if (processDAO.searchProcessById(id).size() > 0) {
                                processDAO.deleteProcess(id);
                            } else {
                                System.out.println("Process with that id does not exist");
                            }
                            menuProcess();
                        }
                        break;
                        case 2: {
                            menuProcess();
                        }
                        break;
                    }
                }
                break;

                case 3: {
                    List<Process> processList = processDAO.getAllProcesses();
                    if (processList.size() > 0) {
                        for (Process process : processList) {
                            String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(process);
                            System.out.println(json);
                        }
                        menuProcess();
                    } else {
                        System.out.println("List of products is empty.");
                        menuProcess();
                    }
                }
                break;
                case 4: {
                    System.out.println("Variation of search:");
                    System.out.println("1 - Id");
                    System.out.println("2 - Name");
                    switch (in.nextInt()) {
                        case 1: {
                            System.out.println("Id of process:");
                            int id = in.nextInt();
                            List<Process> processList = processDAO.searchProcessById(id);
                            if (processList.size() > 0) {
                                for (Process item : processList) {
                                    String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                    System.out.println(json);
                                }
                                menuProcess();
                            } else {
                                System.out.println("No results.");
                                menuProcess();
                            }
                        }
                        break;
                        case 2: {
                            System.out.println("Name of the product:");
                            in.nextLine();
                            String name = in.nextLine();
                            List<Process> processList = processDAO.searchProcessByName(name);
                            if (processList.size() > 0) {
                                for (Process item : processList) {
                                    String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                    System.out.println(json);
                                }
                                menuProcess();
                            } else {
                                System.out.println("No results.");
                                menuProcess();
                            }
                        }
                        break;
                    }
                }
                break;
                case 5: {
                    menu();
                }
                break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong parameter");
        }
    }

    public void menuRate() throws JsonProcessingException, ParseException {
        System.out.println("Choose 1 of actions below:");
        System.out.println("1 - Add rate");
        System.out.println("2 - Delete rate");
        System.out.println("3 - Look at the list of rates");
        System.out.println("4 - Search rate");
        System.out.println("5 - Back to main menu");
        try {
            switch (in.nextInt()) {
                case 1: {
                    System.out.println("Rate of the dish:");
                    int rate = in.nextInt();
                    rateDAO.addRate(new Rate(rate));
                    menuRate();
                }
                break;
                case 2: {
                    System.out.println("ID of group of rate:");
                    int id = in.nextInt();
                    System.out.println("Do you really want to delete rate?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    switch (in.nextInt()) {
                        case 1: {
                            if (rateDAO.searchRateById(id).size() > 0) {
                                rateDAO.deleteRate(id);
                            } else {
                                System.out.println("Rate with that id does not exist");
                            }
                            menuRate();
                        }
                        break;
                        case 2: {
                            menuRate();
                        }
                        break;
                    }
                }
                break;

                case 3: {
                    List<Rate> rateList = rateDAO.getAllRates();
                    if (rateList.size() > 0) {
                        for (Rate rate : rateList) {
                            String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(rate);
                            System.out.println(json);
                        }
                        menuRate();
                    } else {
                        System.out.println("List of rates is empty.");
                        menuRate();
                    }
                }
                break;
                case 4: {
                    System.out.println("Variation of search:");
                    System.out.println("1 - Id");
                    System.out.println("2 - Name");
                    switch (in.nextInt()) {
                        case 1: {
                            System.out.println("Id of rate:");
                            int id = in.nextInt();
                            List<Rate> rateList = rateDAO.searchRateById(id);
                            if (rateList.size() > 0) {
                                for (Rate item : rateList) {
                                    String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                    System.out.println(json);
                                }
                                menuRate();
                            } else {
                                System.out.println("No results.");
                                menuRate();
                            }
                        }
                        break;
                        case 2: {
                            System.out.println("Rate:");
                            int rate = in.nextInt();
                            List<Rate> rateList = rateDAO.searchRateByRate(rate);
                            if (rateList.size() > 0) {
                                for (Rate item :rateList) {
                                    String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                    System.out.println(json);
                                }
                                menuRate();
                            } else {
                                System.out.println("No results.");
                                menuRate();
                            }
                        }
                        break;
                    }
                }
                break;
                case 5:{
                    menu();
                }
                break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong parameter");
        }
    }
    public void menuHighRate() throws ParseException, JsonProcessingException {
        System.out.println("Choose 1 of actions below:");
        System.out.println("1 - Add high rate");
        System.out.println("2 - Delete high rate");
        System.out.println("3 - Look at the list of high rates");
        System.out.println("4 - Search high rate");
        System.out.println("5 - Back to main menu");
       try{
           switch (in.nextInt()) {
            case 1: {

                System.out.println("Rate of the dish:");
                int rate = in.nextInt();
                System.out.println("Name of critic:");
                String name = in.nextLine();
                highRateDAO.addHighRate(new HighRates(rate, name));
                menuHighRate();
            }
            break;
            case 2: {
                System.out.println("ID of high rate:");
                int id = in.nextInt();
                System.out.println("Do you really want to delete high rate?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                switch (in.nextInt()) {
                    case 1: {
                        if (highRateDAO.searchHighRateId(id).size() > 0) {
                            highRateDAO.deleteHighRate(id);
                        } else {
                            System.out.println("High rate with that id does not exist");
                        }
                        menuHighRate();
                    }
                    break;
                    case 2: {
                        menuHighRate();
                    }
                    break;
                }
            }
            break;

            case 3: {
                List<HighRates> highRateList = highRateDAO.getAllHighRate();
                if (highRateList.size() > 0) {
                    for (HighRates highRate : highRateList) {
                        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(highRate);
                        System.out.println(json);
                    }
                    menuHighRate();
                } else {
                    System.out.println("List of products is empty.");
                    menuHighRate();
                }
            }
            break;
            case 4: {
                System.out.println("Variation of search:");
                System.out.println("1 - Id");
                System.out.println("2 - Name");
                switch (in.nextInt()) {
                    case 1: {
                        System.out.println("Id of high rate:");
                        int id = in.nextInt();
                        List<HighRates> highRateList = highRateDAO.searchHighRateId(id);
                        if (highRateList.size() > 0) {
                            for (HighRates item : highRateList) {
                                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                System.out.println(json);
                            }
                            menuHighRate();
                        } else {
                            System.out.println("No results.");
                            menuHighRate();
                        }
                    }
                    break;
                    case 2: {
                        System.out.println("Name of critic:");
                        in.nextLine();
                        String name = in.nextLine();
                        List<HighRates> highRateList = highRateDAO.searchHighRateByName(name);
                        if (highRateList.size() > 0) {
                            for (HighRates item : highRateList) {
                                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                                System.out.println(json);
                            }
                            menuHighRate();
                        }

                        else {
                            System.out.println("No results.");
                            menuHighRate();
                        }
                        menuHighRate();
                    }
                    break;
                }
            }
            break;
            case 5: {
                menu();
            }
            break;
        }
    }
         catch (InputMismatchException e) {
            System.out.println("Wrong parameter");
        }
    }

    public void menu() throws JsonProcessingException, ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose your fighter!:");
        System.out.println("1 - Dish");
        System.out.println("2 - Countries");
        System.out.println("3 - Group Of Dishes");
        System.out.println("4 - Products");
        System.out.println("5 - Process");
        System.out.println("6 - Rate");
        System.out.println("7 - High Rate");
        System.out.println("8 - Back to begin");
     try{   switch (in.nextInt()) {
         case 1: {
             menuDish();
         }
         break;
         case 2: {
             menuCountries();
         }
         break;
         case 3: {
             menuGroupOfDishes();
         }
         break;
         case 4: {
             menuProducts();
         }
         break;
         case 5: {
             menuProcess();
         }
         break;
         case 6: {
             menuRate();
         }
         break;
         case 7: {
             menuHighRate();
         }
         break;
         case 8: {
             start();
         }
         break;
        }
     }
     catch (InputMismatchException e) {
                System.out.println("Wrong parameter");
            }
    }
}
