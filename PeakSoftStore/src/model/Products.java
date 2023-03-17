package model;

import java.time.LocalDate;

public class Products {

//    В отделе молочных продукций есть витрины для молока, кефира, йогурта.
//    В отделе пшеничных продукций есть витрины для хлеба, макарон и муки.
//    В отделе мясных продукций есть витрины для колбасы, мяса и рыбы.

    //company for which company made it
    private int id;
    private static int counter;
    private String name;
    private String company;

    //due to date for product
    private LocalDate date;
    private LocalDate dueToDate;

    //enum for department division
    private Enum enu;

    public Products(String name, String company, LocalDate dueToDate, Enum enu) {
        this.name = name;
        this.company = company;
        this.dueToDate = dueToDate;
        this.enu = enu;
        id = counter++;
    }

    public Products() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDueToDate() {
        return dueToDate;
    }

    public void setDueToDate(LocalDate dueToDate) {
        this.dueToDate = dueToDate;
    }

    public Enum getEnu() {
        return enu;
    }

    public void setEnu(Enum enu) {
        this.enu = enu;
    }

    @Override
    public String toString() {
        return "Name = " + getName()
                + ";Company = " + getCompany()
                + "; Due to = " + getDueToDate()
                + "; Department = " + getEnu();
    }
}
