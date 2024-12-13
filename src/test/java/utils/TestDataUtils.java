package utils;

import com.github.javafaker.Faker;

public class TestDataUtils {

    private static final Faker faker = new Faker();

    public static String generateName(){
        return "Playlist " + faker.regexify("[a-zA-Z0-9 $^]{10}");
    }

    public static String generateDescription(){
        //return "Description " + faker.regexify("[a-zA-Z0-9 $!%&^]{50}");
        return "Description " + faker.regexify("[a-zA-Z0-9]{50}");
    }
}
