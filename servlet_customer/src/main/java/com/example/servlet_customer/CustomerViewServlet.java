package com.example.servlet_customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CustomerViewServlet extends HttpServlet {
    private List<Customer> customers
            = Arrays.asList(new Customer("Roman", "Adamenko", "0998970090", "Ukraine", "Kyiv", "02000", "romanjfkf@prog.academy.ua", "27.07.1992", "rmn444", "Cat", "No", "Male", "Engaged", "romanadmnk", "Football"),
            new Customer("Oxana", "Oliynyk", "0979999299", "USA", "Dallas", "040054", "ooliynyk@gmail.ua", "21.11.1990", "oks2711", "Dog", "Yes", "Female", "Married", "oxaoliynyk", "TV"),
            new Customer("Semen", "Kotovskyy", "0500000010", "Germany", "Berlin", "05645", "semych@ukr.ua", "20.06.2010", "syomochka", "Parrot", "Yes", "Male", "Not married", "Semenovych", "Student"),
            new Customer("Oleksandr", "Chernetskiy", "0684048834", "Italy", "Rome", "09789", "cherik@pfl.ua", "20.06.1995", "alexcher", "No", "No", "Male", "Not married", "alexandercher", "Referee"),
            new Customer("Yuriy", "Sapon", "0914576482", "Great Britain", "Liverpool", "01231", "yusapon@meta.ua", "29.04.1990", "saponchik", "Cat", "Yes", "Male", "Married", "YurchikSapon", "Office"),
            new Customer("Tetyana", "Sevaseva", "0502388238", "Poland", "Warsaw", "045654", "sevtan@crimea.ua", "11.07.1967", "tanyasev", "Dog", "No", "Female", "Married", "TanyaSevas", "Clerk"),
            new Customer("Simba", "Gavkovych", "0674412114", "Sweden", "Geteborg", "009870", "simbusya@meow.ua", "01.05.2005", "simbuskin", "Dog", "Yes", "Male", "Not married", "Simba777", "Eat"),
            new Customer("Serhii", "Ponedilko", "0977689068", "Norway", "Oslo", "0232432", "monday@ndgroup.ua", "30.11.1992", "nedelya", "Spider", "No", "Male", "Not married", "sergnedelko", "Tennis player"),
            new Customer("Karolina", "Kavovnyuk", "0939799977", "Turkey", "Ankara", "05435", "karacoffee@mynet.ua", "04.03.1993", "karanebo", "Snake", "Yes", "Female", "Not married", "Karolinkaaa", "Coffee"),
            new Customer("Inetik", "Budreika", "0506995654", "Kazakhstan", "Nursultan", "05423", "inetikbudya@uaf.ua", "26.07.1992", "inetochka", "Cat", "No", "Female", "Engaged", "InetaBudreika", "QA"));


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customersList", customers);
        request.getRequestDispatcher("/WEB-INF/jspfiles/custable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}


