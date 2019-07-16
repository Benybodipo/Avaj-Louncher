import  java.io.IOException;
import  java.io.FileNotFoundException;
import  java.io.*;
import  java.lang.Integer;
import  java.util.ArrayList;

public class Simulator {
    private static int itterator;
    private static String info[][];

    public static int getNumberOfLines(String path)
    {
        int lines = 0;
        BufferedReader br;

        try
        {
            br =  new BufferedReader(new FileReader(path));

            while (br.readLine() != null) lines++;
            br.close();
        }
        catch (FileNotFoundException ex){ System.out.println(ex.getMessage()); }
        catch (IOException e){ System.out.printf(e.getMessage()); }

        return (lines);
    }

    public static boolean isInt(String num)
    {
        try
        {
            Integer.parseInt(num);
        }
        catch(NumberFormatException e)
        {
            System.out.println("[ERROR] Invalid field!");
            System.exit(1);
            return false;
        }
        return (true);
    }

    public static void getSimulatorParameters(String filePath)
    {
        String info[][] = null;
        String tmp[] = null;
        BufferedReader br;
        String line;
        int i;

        try {

            br          = new BufferedReader(new FileReader(filePath));
            info        = new String[getNumberOfLines(filePath) - 1][5];
            line        = br.readLine();
            i           = 0;


            while (line != null)
            {
                if (!line.equals("") && !line.equals(" "))
                {
                    if (i == 0 && line.split(" ").length == 1)
                    {
                        if (isInt(line))
                        {
                            if (Integer.parseInt(line) > 0)
                                itterator = Integer.parseInt(line);
                            else
                            {
                                System.out.println("[ERROR] Negative itterator!");
                                System.exit(1);
                            }
                        }
                        else
                        {
                            System.out.println("[ERROR] No itterator supplied!");
                            System.exit(1);
                        }
                    }
                    else
                    if (line.split(" ").length == 5)
                    {
                        tmp = line.split(" ");
                        if (isInt(tmp[2]) && isInt(tmp[3]) && isInt(tmp[4]))
                            info[i - 1] = tmp;
                    }
                    else
                        System.out.println("[ERROR] Missing information on line ");
                    i++;
                }
                line = br.readLine();

            }
            br.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] " + e.getMessage());
        }

        Simulator.info = info;
    }


    public static void main(String[] args)
    {
        try
        {
            Logger.createOutputFile();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] " + e.getMessage());
        }

        WeatherTower tower = new WeatherTower();
        ArrayList<Flyable> flyables = new ArrayList<Flyable>();

        getSimulatorParameters("scenario.txt");

        for (int i = 0; i < info.length; i++)
        {
            int _long = Integer.parseInt(info[i][2]),
            _lat = Integer.parseInt(info[i][3]),
            _h = Integer.parseInt(info[i][4]);

            flyables.add(AircraftFactory.newAircraft(info[i][0], info[i][1], _long, _lat, _h));
        }

        for (Flyable flyable : flyables)
            flyable.registerTower(tower);

        for (int i = 0; i < itterator; i++)
            tower.changeWeather();
        System.out.println("[INFO] Simulation completed!!");
    }
}
