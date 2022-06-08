package javadir.main.util;

import javadir.main.GivenPath;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OptimalPath {
    public static int[][] intPath = new int[GivenPath.size][GivenPath.size];
    public static Map<String, Integer> human = new HashMap<String, Integer>() {
        {
            put("S", 5);
            put("W", 2);
            put("T", 3);
            put("P", 1);
        }
    };
    static Map<String, Integer> swamper = new HashMap<String, Integer>() {
        {
            put("S", 2);
            put("W", 2);
            put("T", 5);
            put("P", 2);
        }
    };
    static Map<String, Integer> woodman = new HashMap<String, Integer>() {
        {
            put("S", 3);
            put("W", 3);
            put("T", 2);
            put("P", 2);
        }
    };

    public static void setRacesMapHuman() {
        for (int i = 0; i < GivenPath.size; i++) {
            for (int j = 0; j < GivenPath.size; j++) {
                intPath[i][j] = human.get(GivenPath.stringMass[i][j]);
            }
        }
        Arrays.stream(intPath)
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    public static void setRacesMapSwamper() {
        for (int i = 0; i < GivenPath.size; i++) {
            for (int j = 0; j < GivenPath.size; j++) {
                intPath[i][j] = swamper.get(GivenPath.stringMass[i][j]);
            }
        }
        Arrays.stream(intPath)
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    public static void setRacesMapWoodman() {
        for (int i = 0; i < GivenPath.size; i++) {
            for (int j = 0; j < GivenPath.size; j++) {
                intPath[i][j] = woodman.get(GivenPath.stringMass[i][j]);
            }
        }
        Arrays.stream(intPath)
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    private static final int INFINITY = Integer.MAX_VALUE / 2;


    private int[][] distance;

    public String run() {
        int h = intPath.length;
        int w = intPath[0].length;
        distance = new int[h][w];
        for (int[] row : distance)
            Arrays.fill(row, INFINITY);

        // Bellman–Ford algorithm
        distance[0][0] = intPath[0][0];
        for (int i = 0; i < w * h; i++) {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int temp = INFINITY;
                    temp = Math.min(getDistance(x - 1, y), temp);
                    temp = Math.min(getDistance(x + 1, y), temp);
                    temp = Math.min(getDistance(x, y - 1), temp);
                    temp = Math.min(getDistance(x, y + 1), temp);
                    distance[y][x] = Math.min(intPath[y][x] + temp, distance[y][x]);
                }
            }
        }
        System.out.print("\nВес оптимального пути: ");
        return Integer.toString(distance[h - 1][w - 1] - intPath[0][0]);
    }


    private int getDistance(int x, int y) {
        if (y < 0 || y >= distance.length || x < 0 || x >= distance[y].length)
            return INFINITY;
        else
            return distance[y][x];
    }
}
