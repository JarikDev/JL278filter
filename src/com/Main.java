package com;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarF carF1 = new CarF(190, "Green", 4);
        CarF carF2 = new CarF(250, "Red", 2);
        CarF carF3 = new CarF(80, "Black", 6);
        List<CarF> cars = new ArrayList<>();
        cars.add(carF1);
        cars.add(carF2);
        cars.add(carF3);
//        cars=new SpeedFilter().filter(cars);
//        cars=new DoorsFilter().filter(cars);

       /* AndFilter andFilter = new AndFilter(new SpeedFilter(), new DoorsFilter());
        cars = andFilter.filter(cars);
        for (CarF car : cars) {
            System.out.println(car.getMaxSpeed());
        }
*/
        OrFilter andFilter = new OrFilter(new SpeedFilter(), new DoorsFilter());
        cars = andFilter.filter(cars);
        for (CarF car : cars) {
            System.out.println(car.getMaxSpeed());
        }


//        cars=new DoorsFilter().filter(cars);
//        for (CarF car: cars) {
//            System.out.println(car.getDors());
//        }
//
//        cars=new ColorFilter().filter(cars);
//        for (CarF car: cars) {
//            System.out.println(car.getColor());
//        }

    }
}

interface CarFilter {
    List<CarF> filter(List<CarF> cars);
}

class SpeedFilter implements CarFilter {
    @Override
    public List<CarF> filter(List<CarF> cars) {
        List<CarF> list = new ArrayList<>();
        for (CarF car : cars) {
            if (car.getMaxSpeed() > 180) {
                list.add(car);
            }
        }
        return list;
    }
}


class DoorsFilter implements CarFilter {
    @Override
    public List<CarF> filter(List<CarF> cars) {
        List<CarF> list = new ArrayList<>();
        for (CarF car : cars) {
            if (car.getDors() > 2) {
                list.add(car);
            }
        }
        return list;
    }
}


class ColorFilter implements CarFilter {
    @Override
    public List<CarF> filter(List<CarF> cars) {
        List<CarF> list = new ArrayList<>();
        for (CarF car : cars) {
            if (car.getColor().equals("Red")) {
                list.add(car);
            }
        }
        return list;
    }
}

class AndFilter implements CarFilter {
    CarFilter filterOne;
    CarFilter filterTwo;

    public AndFilter(CarFilter filterOne, CarFilter filterTwo) {
        this.filterOne = filterOne;
        this.filterTwo = filterTwo;
    }

    @Override
    public List<CarF> filter(List<CarF> cars) {
        List<CarF> list = filterOne.filter(cars);
        return filterTwo.filter(list);
    }
}


class OrFilter implements CarFilter {
    CarFilter filterOne;
    CarFilter filterTwo;

    public OrFilter(CarFilter filterOne, CarFilter filterTwo) {
        this.filterOne = filterOne;
        this.filterTwo = filterTwo;
    }

    @Override
    public List<CarF> filter(List<CarF> cars) {
        List<CarF> list = filterOne.filter(cars);
        List<CarF> list2 = filterTwo.filter(cars);
        for (CarF car : list2) {
            if (!list.contains(car)) {
                list.add(car);
            }
        }
        return list;
    }
}


class CarF {
    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDors() {
        return dors;
    }

    public void setDors(int dors) {
        this.dors = dors;
    }

    private int maxSpeed;
    private String color;
    private int dors;

    public CarF(int maxSpeed, String color, int dors) {
        this.maxSpeed = maxSpeed;
        this.color = color;
        this.dors = dors;
    }
}