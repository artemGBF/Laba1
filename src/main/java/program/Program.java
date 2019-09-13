package program;

import org.apache.commons.cli.AlreadySelectedException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import util.PrintHelper;
import util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            CommandLine commandLine = Util.start(args).getKey();
            if (commandLine.hasOption("i")) {
                if (commandLine.hasOption("f")) {
                    String f = commandLine.getOptionValue("f");
                    while (true) {
                        boolean b = Util.interactiveMode(f);
                        if (!b) {
                            System.out.println("finished\n");
                            break;
                        }
                        f = scanner.nextLine();
                    }
                }
            }
            if (commandLine.hasOption("sha256")) {
                Util.sha256Mode(commandLine);
            }
            if (commandLine.hasOption("md5")) {
                Util.md5Mode(commandLine);
            }
            if(commandLine.hasOption("help")){
                new PrintHelper().printHelp(
                        Util.start(args).getValue(), // опции по которым составляем help
                        80, // ширина строки вывода
                        "Options", // строка предшествующая выводу
                        "-- HELP --", // строка следующая за выводом
                        3, // число пробелов перед выводом опции
                        5, // число пробелов перед выводом опцисания опции
                        true, // выводить ли в строке usage список команд
                        System.out // куда производить вывод
                );
            }
        } catch (ParseException | IOException e) {
            System.out.println("Usage -h");
        }

    }
}
