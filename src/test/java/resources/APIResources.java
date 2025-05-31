package resources;

public enum APIResources {
    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private final String path;

    APIResources(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
