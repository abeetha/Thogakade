package com.seekerscloud.pos.db;

import com.seekerscloud.pos.modal.Customer;
import com.seekerscloud.pos.modal.Item;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Customer> customerTable = new ArrayList<Customer>();
    public static ArrayList<Item> itemTable = new ArrayList<>();

    static{
        customerTable.add(new Customer("C001","Bandara","Colombo",25000));
        customerTable.add(new Customer("C002","Jayantha","Kalutara",43000));
        customerTable.add(new Customer("C003","Saman","Panadura",23999));
        customerTable.add(new Customer("C004","Kamal","Galle",435666));
        customerTable.add(new Customer("C005","Namal","Matara",239883));

        itemTable.add(new Item("I-001","Description 1",25,25000));
        itemTable.add(new Item("I-002","Description 2",34,43000));
        itemTable.add(new Item("I-003","Description 3",20,23999));
        itemTable.add(new Item("I-004","Description 4",18,435666));
        itemTable.add(new Item("I-005","Description 5",59,239883));
    }
}
