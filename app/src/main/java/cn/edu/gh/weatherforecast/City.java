package cn.edu.gh.weatherforecast;

public class City {
    private String province;
    private String city;
    private String number;
    private String firstpy;
    private String allpy;
    private String allfristpy;

    public City() {
    }

    public City(String province, String city, String number, String firstpy, String allpy, String allfristpy) {
        this.province = province;
        this.city = city;
        this.number = number;
        this.firstpy = firstpy;
        this.allpy = allpy;
        this.allfristpy = allfristpy;
    }

    @Override
    public String toString() {
        return "City{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", number='" + number + '\'' +
                ", firstpy='" + firstpy + '\'' +
                ", allpy='" + allpy + '\'' +
                ", allfristpy='" + allfristpy + '\'' +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstpy() {
        return firstpy;
    }

    public void setFirstpy(String firstpy) {
        this.firstpy = firstpy;
    }

    public String getAllpy() {
        return allpy;
    }

    public void setAllpy(String allpy) {
        this.allpy = allpy;
    }

    public String getAllfristpy() {
        return allfristpy;
    }

    public void setAllfristpy(String allfristpy) {
        this.allfristpy = allfristpy;
    }
}
