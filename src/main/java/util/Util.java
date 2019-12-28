package util;


import javafx.util.Pair;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    public static Pair<CommandLine, Options> start(String[] mass) throws ParseException {
        Option option1 = new Option("f", "input", true, "input data");
        option1.setArgs(1);
        option1.setOptionalArg(false);
        option1.setArgName("input data");

        Option option2 = new Option("sha256", "type1", false, "type of action1");
        option2.setRequired(false);
        option2.setArgName("type of");

        Option option3 = new Option("md5", "type2", false, "type of action2");
        option3.setRequired(false);
        option3.setArgName("type of");

        Option option4 = new Option("i", "type0", false, "type of work");
        option4.setRequired(false);
        option4.setArgName("type of");

        Option option5 = new Option("help", "getHelp", false, "getHelp");
        option5.setRequired(false);
        option5.setArgName("getHelp");

        Option option6 = new Option("exit", "exit", false, "close program");
        option6.setRequired(false);
        option6.setArgName("exit");

        Options options = new Options();
        options.addOption(option1);
        OptionGroup group = new OptionGroup();
        group.addOption(option2);
        group.addOption(option3);
        group.addOption(option4);

        options.addOptionGroup(group);

        options.addOption(option5);
        options.addOption(option6);
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, mass);
        return new Pair<>(commandLine, options);
    }

    public static boolean interactiveMode(CommandLine commandLine) throws IOException {
        if (commandLine.hasOption("exit"))
            return false;
        if (commandLine.hasOption("f")) {
            String f = commandLine.getOptionValue("f");
            String[] s = f.split(";");
            for (int i = 0; i < s.length; i++) {
                try (InputStream is = Files.newInputStream(Paths.get(s[i]))) {
                    String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
                    String sha256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(is);
                    System.out.println(md5 + "...It is md5 for " + Paths.get(s[i]));
                    System.out.println(sha256 + "...It is sha256 for " + Paths.get(s[i]) + "\n");
                }
            }
        }
        return true;
    }

    public static void md5Mode(CommandLine commandLine) throws IOException {
        if (commandLine.hasOption("f")) {
            String f = commandLine.getOptionValue("f");
            String[] s = f.split(";");
            for (int i = 0; i < s.length; i++) {
                try (InputStream is = Files.newInputStream(Paths.get(s[i]))) {
                    String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
                    System.out.println(md5 + "...It is md5"+"\n");
                }
            }
        }
    }


}

