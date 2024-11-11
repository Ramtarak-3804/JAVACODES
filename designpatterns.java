Let me explain each design pattern category and their use cases:

Creational Patterns:

Handle object creation mechanisms
Make system independent of how objects are created/composed

a) Singleton Pattern

Ensures a class has only one instance
Provides global point of access
Use cases: Database connections, Configuration settings

b) Factory Pattern

Creates objects without exposing creation logic
Uses common interface
Use cases: Database connections for different DBs, UI components creation

c) Builder Pattern

Constructs complex objects step by step
Same construction process can create different representations
Use cases: Creating complex objects like custom computers/cars


Structural Patterns:

Deal with class and object composition
Focus on interface and implementation relationships

a) Adapter Pattern

Allows incompatible interfaces to work together
Wraps object in an adapter to make it compatible
Use cases: Third-party library integration, Legacy system integration

b) Decorator Pattern

Adds new functionality to existing object without altering structure
Provides flexible alternative to subclassing
Use cases: Adding features to UI components, File I/O operations


Behavioral Patterns:

Concerned with communication between objects
Handle responsibilities and algorithms

a) Observer Pattern

Defines one-to-many dependency between objects
When one object changes state, dependents are notified
Use cases: Event handling systems, News feed subscriptions

b) Strategy Pattern

Defines family of algorithms
Makes algorithms interchangeable
Use cases: Payment processing, Sorting algorithms



Key Benefits of Using Design Patterns:

Reusability and maintainability
Established solutions to common problems
Common vocabulary for developers
Promotes loose coupling
Makes code more flexible and extensible

Common Use Cases:

Enterprise Applications
Web Applications
Game Development
Mobile Applications
System Architecture

// 1. CREATIONAL PATTERNS

// Singleton Pattern
class Singleton {
    private static Singleton instance;
    private Singleton() {}
    
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// Factory Pattern
interface Animal {
    void makeSound();
}

class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

class AnimalFactory {
    public Animal createAnimal(String type) {
        if (type.equalsIgnoreCase("dog")) {
            return new Dog();
        } else if (type.equalsIgnoreCase("cat")) {
            return new Cat();
        }
        return null;
    }
}

// Builder Pattern
class Pizza {
    private String dough;
    private String sauce;
    private String topping;
    
    private Pizza() {}
    
    public static class Builder {
        private Pizza pizza;
        
        public Builder() {
            pizza = new Pizza();
        }
        
        public Builder dough(String dough) {
            pizza.dough = dough;
            return this;
        }
        
        public Builder sauce(String sauce) {
            pizza.sauce = sauce;
            return this;
        }
        
        public Builder topping(String topping) {
            pizza.topping = topping;
            return this;
        }
        
        public Pizza build() {
            return pizza;
        }
    }
}

// 2. STRUCTURAL PATTERNS

// Adapter Pattern
interface MediaPlayer {
    void play(String audioType, String fileName);
}

interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }
    
    @Override
    public void playMp4(String fileName) {
        // do nothing
    }
}

class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;
    
    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        }
    }
    
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        }
    }
}

// Decorator Pattern
interface Coffee {
    double getCost();
    String getDescription();
}

class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1;
    }
    
    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    
    public double getCost() {
        return decoratedCoffee.getCost();
    }
    
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + ", milk";
    }
}

// 3. BEHAVIORAL PATTERNS

// Observer Pattern
import java.util.*;

interface Observer {
    void update(String message);
}

class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void notifyObservers(String news) {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
}

class NewsChannel implements Observer {
    private String news;
    
    @Override
    public void update(String news) {
        this.news = news;
        System.out.println("News Channel received news: " + news);
    }
}

// Strategy Pattern
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

// Example Usage
public class DesignPatternDemo {
    public static void main(String[] args) {
        // Singleton
        Singleton singleton = Singleton.getInstance();
        
        // Factory
        AnimalFactory factory = new AnimalFactory();
        Animal dog = factory.createAnimal("dog");
        dog.makeSound();
        
        // Builder
        Pizza pizza = new Pizza.Builder()
                .dough("thin")
                .sauce("tomato")
                .topping("cheese")
                .build();
        
        // Decorator
        Coffee coffee = new SimpleCoffee();
        coffee = new Milk(coffee);
        System.out.println(coffee.getCost());
        System.out.println(coffee.getDescription());
        
        // Observer
        NewsAgency newsAgency = new NewsAgency();
        NewsChannel channel1 = new NewsChannel();
        newsAgency.addObserver(channel1);
        newsAgency.notifyObservers("Breaking News!");
        
        // Strategy
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(100);
        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout(200);
    }
}