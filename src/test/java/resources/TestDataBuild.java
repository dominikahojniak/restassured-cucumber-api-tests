package resources;

import pojo.Location;
import pojo.Maps;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public Maps addPlacePayload(String name, String language, String address){
        Maps maps = new Maps();
        maps.setAccuracy(50);
        maps.setAddress(address);
        maps.setLanguage(language);
        maps.setName(name);
        List<String> types = new ArrayList<>();
        types.add("San Francisco");
        types.add("Belgium");
        maps.setTypes(types);
        maps.setWebsite("https://rahulshettyacademy.com");
        Location location = new Location();
        location.setLat(33.56774);
        location.setLng(123.456789);
        maps.setLocation(location);
        return maps;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }
}
