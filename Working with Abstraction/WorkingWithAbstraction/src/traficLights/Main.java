package traficLights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Color[] colors = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Color::valueOf).toArray(Color[]::new);
        int n = Integer.parseInt(scanner.nextLine());

        List<TrafficLight> trafficLightList = new ArrayList<>();
        for (Color color:colors){
            TrafficLight trafficLight = new TrafficLight(color);
            trafficLightList.add(trafficLight);
        }

        for (int i = 1; i <=n ; i++) {
            //change color of every traficlight;
            for (TrafficLight trafficLight :trafficLightList){
                trafficLight.changeColor();
                System.out.print(trafficLight.getColor()+" ");
            }
            System.out.println();
        }
    }
}
