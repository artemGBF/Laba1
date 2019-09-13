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

public class Program {
    public static void main(String[] args) {
        try {
            CommandLine commandLine = Util.start(args);
            /*if(commandLine.hasOption("help")){
                new PrintHelper().printHelp(
                        commandLine, // опции по которым составляем help
                        80, // ширина строки вывода
                        "Options", // строка предшествующая выводу
                        "-- HELP --", // строка следующая за выводом
                        3, // число пробелов перед выводом опции
                        5, // число пробелов перед выводом опцисания опции
                        true, // выводить ли в строке usage список команд
                        System.out // куда производить вывод
                );
            }*/
            if(commandLine.hasOption("i")){
                while (!commandLine.hasOption("exit")) {
                    if (commandLine.hasOption("f")) {
                        String f = commandLine.getOptionValue("f");
                        String[] s = f.split("//");
                        for (int i = 0; i < s.length; i++) {
                            try (InputStream is = Files.newInputStream(Paths.get(s[i]))) {
                                String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
                                String sha256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(is);
                                System.out.println(md5 + "It is md5");
                                System.out.println(sha256 + "It is sha256");
                            }
                        }
                    }
                }
            }
            if(commandLine.hasOption("sha256")){
                Util.sha256Mode(commandLine);
            }
            if(commandLine.hasOption("md5")){
                Util.md5Mode(commandLine);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
