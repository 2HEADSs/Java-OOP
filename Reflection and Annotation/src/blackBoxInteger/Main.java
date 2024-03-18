package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Class<BlackBoxInt> clazz = BlackBoxInt.class;

        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();
        Field innerValue = clazz.getDeclaredField("innerValue");

        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList());


        while (!input.equals("END")) {
            String command = input.split("_")[0];
            int num = Integer.parseInt(input.split("_")[1]);
            switch (command) {
                case "add":
                    executeCommand( blackBoxInt, innerValue, num, "add", methods);
                    break;
                case "subtract":
                    executeCommand( blackBoxInt, innerValue, num, "subtract", methods);
                    break;
                case "multiply":
                    executeCommand( blackBoxInt, innerValue, num, "multiply", methods);
                    break;
                case "divide":
                    executeCommand( blackBoxInt, innerValue, num, "divide", methods);
                    break;
                case "leftShift":
                    executeCommand( blackBoxInt, innerValue, num, "leftShift", methods);
                    break;
                case "rightShift":
                    executeCommand( blackBoxInt, innerValue, num, "rightShift", methods);
                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static void executeCommand(BlackBoxInt blackBoxInt, Field innerValue, int num, String command, List<Method> methods) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method currentMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(command)) {
                currentMethod = method;
                break;
            }
        }
        currentMethod.setAccessible(true);
        currentMethod.invoke(blackBoxInt, num);
        innerValue.setAccessible(true);
        System.out.println(innerValue.get(blackBoxInt));
    }
}
