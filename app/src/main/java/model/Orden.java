package model;

public class Orden {
    private String key;
    private boolean isActive;

    public Orden(String key, boolean isActive) {
        this.key = key;
        this.isActive = isActive;
    }

    public Orden() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}