package program;

import javafx.util.Pair;
import org.apache.commons.cli.*;
import util.PrintHelper;
import util.Util;

import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        try {
            Pair<CommandLine, Options> start = Util.start(args);
            Scanner scanner = new Scanner(System.in);
            CommandLine commandLine = start.getKey();
            if (commandLine.hasOption("i")) {
                while (true) {
                    boolean b = Util.interactiveMode(commandLine);
                    if (!b) {
                        System.out.println("\nfinished");
                        break;
                    }
                    CommandLineParser commandLineParser = new DefaultParser();
                    String[] g = scanner.nextLine().split(" ");
                    commandLine = commandLineParser.parse(start.getValue(), g);
                }
            }
            if (commandLine.hasOption("sha256")) {
                Util.sha256Mode(commandLine);
            }
            if (commandLine.hasOption("md5")) {
                Util.md5Mode(commandLine);
            }
            if (commandLine.hasOption("help")) {
                new PrintHelper().printHelp(
                        start.getValue(), // опции по которым составляем help
                        80, // ширина строки вывода
                        "Options", // строка предшествующая выводу
                        "-- HELP --", // строка следующая за выводом
                        3, // число пробелов перед выводом опции
                        5, // число пробелов перед выводом опцисания опции
                        true, // выводить ли в строке usage список команд
                        System.out // куда производить вывод
                );
            }
        } catch (IOException e) {
            System.out.println("Usage -h");
        }

    }
}
