package saracoglu.fatih.covid19tracker.model;

public class CountryStatistics {

    private String country;
    private String province;
    private String lastUpdate;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;

    public CountryStatistics() {
        
    }

    public CountryStatistics(String country, String province, String lastUpdate, Integer confirmed, Integer deaths, Integer recovered) {
        this.country = country;
        this.province = province;
        this.lastUpdate = lastUpdate;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "CountryStatistics{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }
}
