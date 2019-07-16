

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower = null;
    private String identifier, message;

    public JetPlane(String name, Coordinates coordinates)
    {
        super(name, coordinates);
        this.identifier = "JetPlain#" + this.name + "(" +String.valueOf(this.id) + ")";

    }

    public void updateConditions()
    {

        String weather = this.weatherTower.getWeather(this.coordinates).toLowerCase();
        int height = this.coordinates.getHeight();
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();

        if (weather.equals("sun"))
        {
            this.coordinates = new Coordinates(longitude, (latitude + 10), (height + 2));
            this.message = this.identifier + " SUN - let\'s go to the beach";
        }
        else if (weather.equals("rain"))
        {
            this.coordinates = new Coordinates(longitude, (latitude + 5), height);
            this.message = this.identifier + " RAIN - one day less to day 0";
        }
        else if (weather.equals("fog"))
        {
            this.coordinates = new Coordinates(longitude, (latitude + 1), height);
            this.message = this.identifier + " FOG -  for real?";
        }
        else if (weather.equals("snow"))
        {
            this.coordinates = new Coordinates(longitude, latitude , (height + 7));
            this.message = this.identifier + " SNOW - This is for the watch (GOT)";
        }
        else
            return;

        if (this.coordinates.getHeight() > 100)
            this.coordinates = new Coordinates(longitude, latitude , 100);
        Logger.log(this.message);

        if (this.coordinates.getHeight() <= 0)
        {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), 0);
            weatherTower.unregister(this);
            Logger.log(this.identifier + "has landed!");
            Logger.log("Tower says: " + this.identifier + " unregistered to Weather Tower");
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Logger.log("Tower says: " + this.identifier + " just registered to Weather Tower");

    }

}
