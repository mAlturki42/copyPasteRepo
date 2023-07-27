package com.qa.model;

public abstract class Animal implements Comparable<Animal>{
    private String name;
    private int age;
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Animal o) {
        return age - o.getAge();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    private int getAge() {
        return this.age;
    }


    public String toString(){
        return getName();
    }

    public abstract String sayHello();
    public abstract String move();
}
