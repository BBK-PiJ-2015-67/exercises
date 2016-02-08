import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Exercise 4 for day 16 of PiJ
 * @author lmignot
 */
public class FileCp {

    private static final String CWD = Paths.get("").toAbsolutePath().toString() + File.separator + "data";

    public static void main (String[] args) {
        String original;
        String destination;

        switch (args.length) {
            case 0:
                System.out.println("Please specify a source and destination, or a list of files and destination " +
                        "folder");
                break;
            case 1: {
                System.out.println("Please specify source file(s) and destination filename or folder");
                break;
            }
            case 2: {
                original = args[0];
                destination = args[1];

                if (cp(original, destination)) {
                    System.out.println("Copy successful!");
                }
                break;
            }
            default: {
                String destPath = args[args.length - 1];
                File dest = new File(CWD + File.separator + destPath);
                if(!dest.isDirectory()) {
                    System.out.println("Last argument should be a destination folder.");
                } else {
                    for (int i = 0; i < args.length - 1; i++) {
                        String src = args[i];
                        String dst = dest.getName() + File.separator + args[i];

                        if (cp(src, dst)) {
                            System.out.println("Copied " + args[i] + " to " +
                                    dest.getName() + File.separator + args[i]);
                        } else {
                            System.out.println("Failed to copy " + args[i]);
                        }
                    }
                }
                break;
            }
        }
    }

    public static boolean cp (String original, String destination) {
        File origin = new File(CWD + File.separator + original);
        File dest = new File(CWD + File.separator + destination);

        if (!origin.exists()) {
            System.err.println("File " + original + " does not exist, please try again.");
            return false;
        }

        boolean shouldWrite = true;
        boolean didWrite = false;

        if (dest.exists()) {
            System.out.print("A file named " + destination + " exists, do you want to overwrite it? (y/n) ");
            if (new Scanner(System.in).nextLine().equals("n")) {
                System.out.println("Skipping " + destination);
                shouldWrite = false;
            }
        }

        if (shouldWrite) {
            System.out.println("Copying " + origin.getPath() + " to " + dest.getPath());
            String output = readFile(origin);
            if (output != null) {
                didWrite = writeFile(output, dest);
            }
        }

        return didWrite;
    }

    public static boolean writeFile (String contents, File f) {
        try (Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "utf-8"))) {
            w.write(contents);
            return true;
        } catch (IOException ex) {
            System.err.println("Encountered an error while writing to file " + f.getName());
            ex.printStackTrace();
        }
        return false;
    }

    public static String readFile (File f) {
        StringBuilder builder = new StringBuilder();
        String result = null;
        try (BufferedReader in = new BufferedReader(new FileReader(f))) {
            String line;
            while((line = in.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("The file " + f.getName() + " does not exist.");
            ex.printStackTrace();
            return result;
        } catch (IOException ex) {
            System.err.println("Encountered an error while reading file " + f.getName());
            ex.printStackTrace();
            return result;
        }
        if (builder.toString().length() != 0) {
            result = builder.toString();
        }
        return result;
    }

}
