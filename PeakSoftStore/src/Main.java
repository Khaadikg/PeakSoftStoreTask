import dao.ProductDao;
import dao.UsersDao;
import excp.MyException;
import model.Products;
import model.Users;
import service.impl.ProductServiceImpl;
import java.time.LocalDate;

import static enums.Enums.*;

public class Main {
    public static void main(String[] args) throws MyException {

        ProductDao.addProductToDB(new Products("Хлеб", "NURA",LocalDate.of(2023,4,12), FLOUR));
        ProductDao.addProductToDB(new Products("Молоко", "MILKA",LocalDate.of(2023,3,17), MILK));
        ProductDao.addProductToDB(new Products("Колбаса", "TOIBOSS",LocalDate.of(2023,2,12), MEAT));
//        Products baguette = new Products("Хлеб", "NUR", LocalDate.of(2023, 3, 14), FLOUR);

        Products baguette = new Products();
        baguette.setName("Хлеб");
        baguette.setCompany("TOIBOSS");
        baguette.setDueToDate(LocalDate.of(2023, 3, 25));
        baguette.setEnu(FLOUR);
        ProductDao.addProductToDB(baguette);


        ProductServiceImpl impl =  new ProductServiceImpl();
        UsersDao.addUserToDB(new Users("admin", "admin"));
//        impl.getAllProduct();
//        impl.checkForDueToProduct();
        impl.checkForDueToWeekProduct();
        impl.getAllProduct();


    }
}