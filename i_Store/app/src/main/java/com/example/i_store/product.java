package com.example.i_store;

import android.widget.EditText;

public class product {
    String category, name, model, color, desc, uid, city, brand, storage, price, status, subcategory, trans, fuel, image, ftype, size, cam, wtype, ram, cpu, gpu, generation;

    String id;

    public product(String category, String name, String desc, String uid, String city, String brand, String storage, String price, String status, String image, String size, String ram, String cpu, String gpu, String generation, String id) {
        this.category = category;
        this.name = name;
        this.desc = desc;
        this.uid = uid;
        this.city = city;
        this.brand = brand;
        this.storage = storage;
        this.price = price;
        this.status = status;
        this.image = image;
        this.size = size;
        this.ram = ram;
        this.cpu = cpu;
        this.gpu = gpu;
        this.generation = generation;
        this.id = id;
    }

    public product(String id,String uid,String category, String name, String model, String color, String desc) {
        this.category = category;
        this.name = name;
        this.model = model;
        this.color = color;
        this.desc = desc;
        this.id=id;
        this.uid=uid;
    }
    //constructor for video games


    public product(String category, String name, String color, String desc, String uid, String city, String brand, String storage, String price, String status, String subcategory, String image, String id) {
        this.category = category;
        this.name = name;
        this.color = color;
        this.desc = desc;
        this.uid = uid;
        this.city = city;
        this.brand = brand;
        this.storage = storage;
        this.price = price;
        this.status = status;
        this.subcategory = subcategory;
        this.image = image;
        this.id = id;
    }

    //consructor for phones

    public product(String category, String name, String model, String color, String desc, String uid, String city, String brand, String storage, String price, String status, String subcategory, String image, String size, String cam, String id,String ram) {
        this.category = category;
        this.name = name;
        this.model = model;
        this.color = color;
        this.desc = desc;
        this.uid = uid;
        this.city = city;
        this.brand = brand;
        this.storage = storage;
        this.price = price;
        this.status = status;
        this.subcategory = subcategory;
        this.image = image;
        this.size = size;
        this.cam = cam;
        this.id = id;
        this.ram=ram;
    }


//consructor for car

    public product(String category, String name, String model, String color, String desc,  String city,String uid, String brand, String price, String status, String subcategory, String trans, String fuel, String image, String id) {
        this.category = category;
        this.name = name;
        this.model = model;
        this.color = color;
        this.desc = desc;
        this.uid = uid;
        this.city = city;
        this.brand = brand;
        this.price = price;
        this.status = status;
        this.subcategory = subcategory;
        this.trans = trans;
        this.fuel = fuel;
        this.image = image;
        this.id = id;
    }


    public product(String category, String name, String color, String desc, String uid, String city, String brand, String price, String status, String image, String wtype, String id) {
        this.category = category;
        this.name = name;
        this.color = color;
        this.desc = desc;
        this.uid = uid;
        this.city = city;
        this.brand = brand;
        this.price = price;
        this.status = status;
        this.image = image;
        this.wtype = wtype;
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getBrand() {
        return brand;
    }

    public String getStorage() {
        return storage;
    }

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getTrans() {
        return trans;
    }

    public String getFuel() {
        return fuel;
    }

    public String getImage() {
        return image;
    }

    public String getFtype() {
        return ftype;
    }


    public product() {
    }

    public String getSize() {
        return size;
    }

    public String getCam() {
        return cam;
    }

    public String getWtype() {
        return wtype;
    }

    public String getRam() {
        return ram;
    }

    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public String getGeneration() {
        return generation;
    }

    public String getUid() {
        return uid;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getDesc() {
        return desc;
    }

    public String getId() {
        return id;
    }
}

