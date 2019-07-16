

public abstract class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
    {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        String _type = type.toLowerCase();


        if  (_type.equals("baloon"))
            return (new Baloon(name, coordinates));
        else if  (_type.equals("helicopter"))
            return (new Helicopter(name, coordinates));
        else if (_type.equals("jetplane"))
            return (new JetPlane(name, coordinates));

        return (null);

    }

}
