package aston.java.intensive.module5;

import aston.java.intensive.module5.domain.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    private static final List<User> users = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Программа сортировки пользователей ===");
        
        boolean running = true;
        
        while (running) {
            printMenu();
            System.out.print("Выберите действие: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        createUsers();
                        break;
                    case 2:
                        sortUsers();
                        break;
                    case 3:
                        displayUsers();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Выход из программы...");
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 4");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    private static void printMenu() {
        System.out.println("МЕНЮ:");
        System.out.println("1. Создать пользователей");
        System.out.println("2. Отсортировать пользователей");
        System.out.println("3. Показать пользователей");
        System.out.println("4. Выход");
    }
    
    private static void createUsers() {
        System.out.print("Введите количество пользователей: ");
        
        try {
            int count = Integer.parseInt(scanner.nextLine());
            
            if (count <= 0) {
                System.out.println("Количество должно быть положительным");
                return;
            }
            
            users.clear();
            for (int i = 0; i < count; i++) {
                users.add(createTestUser(i));
            }
            
            System.out.println("Создано " + count + " пользователей");
            
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите число");
        }
    }
    
    private static User createTestUser(int index) {
        try {
            return new User.Builder()
                .setName("User" + (index + 1))
                .setPassword("pass" + (index + 1) + "word")
                .setEmail("user" + (index + 1) + "@example.com")
                .build();
        } catch (Exception e) {
            try {
                return new User.Builder()
                    .setName("DefaultUser")
                    .setPassword("password123")
                    .setEmail("default@example.com")
                    .build();
            } catch (Exception ex) {
                throw new RuntimeException("Ошибка создания пользователя", ex);
            }
        }
    }
    
    private static void sortUsers() {
        if (users.isEmpty()) {
            System.out.println("Сначала создайте пользователей (пункт 1)");
            return;
        }
        
        Collections.sort(users);
        System.out.println("Пользователи отсортированы по имени → email → паролю");
    }
    
    private static void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("Нет пользователей для отображения");
            return;
        }
        
        System.out.println("Список пользователей (" + users.size() + "):");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }
}
