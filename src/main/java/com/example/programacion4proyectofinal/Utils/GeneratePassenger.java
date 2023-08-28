package com.example.programacion4proyectofinal.Utils;

import com.github.javafaker.Faker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneratePassenger {

    public static void main(String[] args) {
        int totalUsers = 10;
        int batchSize = 3;
        int numThreads = Runtime.getRuntime().availableProcessors();

        for (int batchStart = 0; batchStart < totalUsers; batchStart += batchSize) {
            JSONArray usersArray = generateRandomUsersParallel(batchSize, numThreads);

            try (FileWriter file = new FileWriter("random_users_" + batchStart + ".json")) {
                file.write(usersArray.toString(4));
                System.out.println("Archivo random_users_" + batchStart + ".json generado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static JSONArray generateRandomUsersParallel(int numUsers, int numThreads) {
        Faker faker = new Faker();
        AtomicInteger counter = new AtomicInteger(0);

        return new JSONArray(
                java.util.stream.Stream.generate(() -> generateRandomUser(faker))
                        .limit(numUsers)
                        .parallel()
                        .peek(user -> counter.incrementAndGet())
                        .toArray()
        );
    }

    private static JSONObject generateRandomUser(Faker faker) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String idCard = faker.number().digits(7);
        String userType = faker.options().option("Vip", "Frecuente", "Regular");

        JSONObject user = new JSONObject();
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        user.put("idCard", idCard);
        user.put("userType", userType);

        return user;
    }
}

