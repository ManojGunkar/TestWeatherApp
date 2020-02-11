package com.manoj.weatherapp.apiconnector.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForcastWeather {

    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current")
    @Expose
    private Current current;
    @SerializedName("forecast")
    @Expose
    private Forecast forecast;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }


    public class Astro {

        @SerializedName("sunrise")
        @Expose
        private String sunrise;
        @SerializedName("sunset")
        @Expose
        private String sunset;
        @SerializedName("moonrise")
        @Expose
        private String moonrise;
        @SerializedName("moonset")
        @Expose
        private String moonset;
        @SerializedName("moon_phase")
        @Expose
        private String moonPhase;
        @SerializedName("moon_illumination")
        @Expose
        private Integer moonIllumination;

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public String getMoonPhase() {
            return moonPhase;
        }

        public void setMoonPhase(String moonPhase) {
            this.moonPhase = moonPhase;
        }

        public Integer getMoonIllumination() {
            return moonIllumination;
        }

        public void setMoonIllumination(Integer moonIllumination) {
            this.moonIllumination = moonIllumination;
        }

    }

    public class Current {

        @SerializedName("observation_time")
        @Expose
        private String observationTime;
        @SerializedName("temperature")
        @Expose
        private Integer temperature;
        @SerializedName("weather_code")
        @Expose
        private Integer weatherCode;
        @SerializedName("weather_icons")
        @Expose
        private List<String> weatherIcons = null;
        @SerializedName("weather_descriptions")
        @Expose
        private List<String> weatherDescriptions = null;
        @SerializedName("wind_speed")
        @Expose
        private Integer windSpeed;
        @SerializedName("wind_degree")
        @Expose
        private Integer windDegree;
        @SerializedName("wind_dir")
        @Expose
        private String windDir;
        @SerializedName("pressure")
        @Expose
        private Integer pressure;
        @SerializedName("precip")
        @Expose
        private Integer precip;
        @SerializedName("humidity")
        @Expose
        private Integer humidity;
        @SerializedName("cloudcover")
        @Expose
        private Integer cloudcover;
        @SerializedName("feelslike")
        @Expose
        private Integer feelslike;
        @SerializedName("uv_index")
        @Expose
        private Integer uvIndex;
        @SerializedName("visibility")
        @Expose
        private Integer visibility;
        @SerializedName("is_day")
        @Expose
        private String isDay;

        public String getObservationTime() {
            return observationTime;
        }

        public void setObservationTime(String observationTime) {
            this.observationTime = observationTime;
        }

        public Integer getTemperature() {
            return temperature;
        }

        public void setTemperature(Integer temperature) {
            this.temperature = temperature;
        }

        public Integer getWeatherCode() {
            return weatherCode;
        }

        public void setWeatherCode(Integer weatherCode) {
            this.weatherCode = weatherCode;
        }

        public List<String> getWeatherIcons() {
            return weatherIcons;
        }

        public void setWeatherIcons(List<String> weatherIcons) {
            this.weatherIcons = weatherIcons;
        }

        public List<String> getWeatherDescriptions() {
            return weatherDescriptions;
        }

        public void setWeatherDescriptions(List<String> weatherDescriptions) {
            this.weatherDescriptions = weatherDescriptions;
        }

        public Integer getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(Integer windSpeed) {
            this.windSpeed = windSpeed;
        }

        public Integer getWindDegree() {
            return windDegree;
        }

        public void setWindDegree(Integer windDegree) {
            this.windDegree = windDegree;
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public Integer getPressure() {
            return pressure;
        }

        public void setPressure(Integer pressure) {
            this.pressure = pressure;
        }

        public Integer getPrecip() {
            return precip;
        }

        public void setPrecip(Integer precip) {
            this.precip = precip;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public void setHumidity(Integer humidity) {
            this.humidity = humidity;
        }

        public Integer getCloudcover() {
            return cloudcover;
        }

        public void setCloudcover(Integer cloudcover) {
            this.cloudcover = cloudcover;
        }

        public Integer getFeelslike() {
            return feelslike;
        }

        public void setFeelslike(Integer feelslike) {
            this.feelslike = feelslike;
        }

        public Integer getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(Integer uvIndex) {
            this.uvIndex = uvIndex;
        }

        public Integer getVisibility() {
            return visibility;
        }

        public void setVisibility(Integer visibility) {
            this.visibility = visibility;
        }

        public String getIsDay() {
            return isDay;
        }

        public void setIsDay(String isDay) {
            this.isDay = isDay;
        }

    }
    public class Forecast {

        @SerializedName("2020-02-10")
        @Expose
        private _20200210 _20200210;

        public _20200210 get20200210() {
            return _20200210;
        }

        public void set20200210(_20200210 _20200210) {
            this._20200210 = _20200210;
        }

    }
    public class Location {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("region")
        @Expose
        private String region;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lon")
        @Expose
        private String lon;
        @SerializedName("timezone_id")
        @Expose
        private String timezoneId;
        @SerializedName("localtime")
        @Expose
        private String localtime;
        @SerializedName("localtime_epoch")
        @Expose
        private Integer localtimeEpoch;
        @SerializedName("utc_offset")
        @Expose
        private String utcOffset;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getTimezoneId() {
            return timezoneId;
        }

        public void setTimezoneId(String timezoneId) {
            this.timezoneId = timezoneId;
        }

        public String getLocaltime() {
            return localtime;
        }

        public void setLocaltime(String localtime) {
            this.localtime = localtime;
        }

        public Integer getLocaltimeEpoch() {
            return localtimeEpoch;
        }

        public void setLocaltimeEpoch(Integer localtimeEpoch) {
            this.localtimeEpoch = localtimeEpoch;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
        }

    }
    public class Request {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("query")
        @Expose
        private String query;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("unit")
        @Expose
        private String unit;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

    }
    public class _20200210 {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("date_epoch")
        @Expose
        private Integer dateEpoch;
        @SerializedName("astro")
        @Expose
        private Astro astro;
        @SerializedName("mintemp")
        @Expose
        private Integer mintemp;
        @SerializedName("maxtemp")
        @Expose
        private Integer maxtemp;
        @SerializedName("avgtemp")
        @Expose
        private Integer avgtemp;
        @SerializedName("totalsnow")
        @Expose
        private Integer totalsnow;
        @SerializedName("sunhour")
        @Expose
        private Double sunhour;
        @SerializedName("uv_index")
        @Expose
        private Integer uvIndex;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getDateEpoch() {
            return dateEpoch;
        }

        public void setDateEpoch(Integer dateEpoch) {
            this.dateEpoch = dateEpoch;
        }

        public Astro getAstro() {
            return astro;
        }

        public void setAstro(Astro astro) {
            this.astro = astro;
        }

        public Integer getMintemp() {
            return mintemp;
        }

        public void setMintemp(Integer mintemp) {
            this.mintemp = mintemp;
        }

        public Integer getMaxtemp() {
            return maxtemp;
        }

        public void setMaxtemp(Integer maxtemp) {
            this.maxtemp = maxtemp;
        }

        public Integer getAvgtemp() {
            return avgtemp;
        }

        public void setAvgtemp(Integer avgtemp) {
            this.avgtemp = avgtemp;
        }

        public Integer getTotalsnow() {
            return totalsnow;
        }

        public void setTotalsnow(Integer totalsnow) {
            this.totalsnow = totalsnow;
        }

        public Double getSunhour() {
            return sunhour;
        }

        public void setSunhour(Double sunhour) {
            this.sunhour = sunhour;
        }

        public Integer getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(Integer uvIndex) {
            this.uvIndex = uvIndex;
        }

    }
}
