 interface Connectable {
    void turnOn();
    void turnOff();
    boolean isOn();
}

 class Lamp implements Connectable {
    private boolean state;
   
    public void turnOn() {
        state = true;
    }

    public void turnOff() {
        state = false;
    }

    public boolean isOn() {
        return state;
    }
}

class Computer implements Connectable {
    private boolean state;

    public void turnOn() {
        state = true;
    }

    public void turnOff() {
        state = false;
    }

    public boolean isOn() {
        return state;
    }
}

class CoffeeMaker{
    private boolean state;
    
    public void on() {
            state = true;
    }
    
    public void off() {
            state = false;
    }
    
    public boolean isOff() {
            return !state;
    }
    
    
}

class CoffeeMakerAdapter implements Connectable{
    private CoffeeMaker c;
    CoffeeMakerAdapter(CoffeeMaker c){
        this.c=c;
    }
    public void turnOn(){
        c.on();
    }
    public void turnOff(){
        c.off();
    }
    public boolean isOn(){
        return !c.isOff();
    }

}

public class TurnOnDevices {
    public static void main(String[] args) {
        Connectable lamp = new Lamp();
        Connectable computer = new Computer();
        Connectable coffeeMaker = new CoffeeMakerAdapter(new CoffeeMaker());

        lamp.turnOn();
        computer.turnOn();
        coffeeMaker.turnOn();

        System.out.println("Lamp is on: " + lamp.isOn());
        System.out.println("Computer is on: " + computer.isOn());
        System.out.println("Coffee maker is on: " + coffeeMaker.isOn());
    }

    public static void turnOnDevice(Connectable device) {
        device.turnOn();
    }
}



