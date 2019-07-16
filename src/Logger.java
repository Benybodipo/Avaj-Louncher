import  java.io.*;

public class Logger {
    private static Writer writer = null;

    public static void createOutputFile() throws IOException
    {

        if (Logger.writer != null)
                Logger.writer.close();
        Logger.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("simulator.txt"), "UTF-8"));

    }

    public  static void log(String msj)
    {
        try {
            Logger.writer.write(msj + "\n");
            writer.flush();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] " + e.getMessage());
            System.exit(0);
        }
    }
}
