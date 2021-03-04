package com.Lesson8;

public class Main {

    public static void main(String[] args) {
        HashTableImpl myGoods=createLedger();
        System.out.println("------------------------");
        System.out.println("New hash map:");
        myGoods.display();

        System.out.println("------------------------");
        myGoods.remove("Orange");
        myGoods.remove("Cherry");
        System.out.println("After removing Orange and Cherry:");
        myGoods.display();

        System.out.println("-------------------------");
        myGoods.remove("Cucumber");
        myGoods.remove("Pineapple");
        System.out.println("After removing Cucumber and Pineapple:");
        myGoods.display();


        System.out.println("-------------------------");
        System.out.println("Returned value when i'm trying to delete an uncontained item " +
                "\n(Orange has been already deleted): "+myGoods.remove("Orange"));
    }

    public static HashTableImpl<String, Integer> createLedger(){
        HashTableImpl<String, Integer> myLedger= new HashTableImpl<>(4);
        myLedger.put("Apple",200);
        myLedger.put("Orange",240);
        myLedger.put("Cherry",320);
        myLedger.put("Cucumber",180);
        myLedger.put("Potato",30);
        myLedger.put("Onion",40);
        myLedger.put("Pineapple",450);
        return myLedger;
        }
}
