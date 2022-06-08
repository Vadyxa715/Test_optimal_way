package javadir.main;

import javadir.main.util.OptimalPath;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GivenPath {
    public static int size;
    public static ArrayList<String> list;
    public static String[] stringArr;
    public static String[][] stringMass;
    public static String racesType;

    public static void main(String[] args) throws IOException {
        try (FileInputStream fin = new FileInputStream(System.getProperty("user.dir")
                + "\\src\\javadir\\resources\\Path.txt")) {

            int i = -1;
            list = new ArrayList<String>();
            while ((i = fin.read()) != -1) {
                list.add(String.valueOf((char) i));
            }
            stringArr = list.toArray(new String[0]);
            System.out.println("Исходные данные считанные из файла:");
            System.out.println(Arrays.toString(stringArr));
        }
        if (Math.sqrt(stringArr.length) % 1 != 0) {
            System.out.println("Данные, считанные из файла, некорректны.\nНевозможно построить квадратную матрицу.\nПроверьте кол-во символов.");
            return;
        }
        size = (int) Math.sqrt(stringArr.length);
        stringMass = new String[size][size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stringMass[i][j] = stringArr[index];
                index++;
            }
        }
        System.out.println("Создаем карту событий в виде буквенного представления \n" +
                "преобразуем одномерный массив в вдумерный - String[][]");
        Arrays.stream(stringMass)
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
        System.out.println("----------------------------------------------------------------------");
        whoAreYou();
        System.out.println(new OptimalPath().run());
    }

    public static void whoAreYou() {
        System.out.println("Укажите кем вы являетесь.\nHuman - \"H\"; Swamper - \"S\"; Woodman - \"W\".");
        Scanner scanner = new Scanner(System.in);
        racesType = scanner.nextLine();
        switch (racesType.toLowerCase()) {
            case ("h"):
                System.out.println("Численное представление карты событий в зависимости от расы:\n");
                OptimalPath.setRacesMapHuman();
                break;
            case ("s"):
                System.out.println("Численное представление карты событий в зависимости от расы:\n");
                OptimalPath.setRacesMapSwamper();
                break;
            case ("w"):
                System.out.println("Численное представление карты событий в зависимости от расы:\n");
                OptimalPath.setRacesMapWoodman();
                break;
            default:
                System.out.println("Не корректные данные - повторите ввод");
                whoAreYou();
        }
    }
}
