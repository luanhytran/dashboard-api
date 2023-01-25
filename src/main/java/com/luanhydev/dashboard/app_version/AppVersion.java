package com.luanhydev.dashboard.app_version;

public class AppVersion {
    private Integer id;
    private String name;
    private String version;

    public AppVersion() {
    }

    public AppVersion(Integer id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public AppVersion(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
