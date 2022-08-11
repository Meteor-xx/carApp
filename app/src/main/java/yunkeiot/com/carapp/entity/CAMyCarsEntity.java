package yunkeiot.com.carapp.entity;

import java.io.Serializable;
import java.util.List;

import yunkeiot.com.carapp.entity.Data.BaseEntity;

public class CAMyCarsEntity extends BaseEntity {
    private int page;
    private int pageSize;
    private int totalRows;
    private int pageNum;
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }



    private List<CarBean> data;
    public List<CarBean> getData() {
        return data;
    }

    public void setData(List<CarBean> data) {
        this.data = data;
    }
    public static class CarBean implements Serializable{
        private String plateNumber;
        private String deviceId;
        private int onlineState;
        private int acc;
        private String vehicleManufacturers;
        private String series;
        private String vehicleMode;
        private String vehicleYear;
        private int powerType;
        private int fuelVolume;
        private String engineType;
        private String vhicleColor;
        private String engineCapacity;
        private String emissionStandards;
        private String dateAdded;
        private String gpsLocation;
        private String vehicleIdentificationNumber;

        public String getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getOnlineState() {
            return onlineState;
        }

        public void setOnlineState(int onlineState) {
            this.onlineState = onlineState;
        }

        public int getAcc() {
            return acc;
        }

        public void setAcc(int acc) {
            this.acc = acc;
        }

        public String getVehicleManufacturers() {
            return vehicleManufacturers;
        }

        public void setVehicleManufacturers(String vehicleManufacturers) {
            this.vehicleManufacturers = vehicleManufacturers;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public String getVehicleMode() {
            return vehicleMode;
        }

        public void setVehicleMode(String vehicleMode) {
            this.vehicleMode = vehicleMode;
        }

        public String getVehicleYear() {
            return vehicleYear;
        }

        public void setVehicleYear(String vehicleYear) {
            this.vehicleYear = vehicleYear;
        }

        public int getPowerType() {
            return powerType;
        }

        public void setPowerType(int powerType) {
            this.powerType = powerType;
        }

        public int getFuelVolume() {
            return fuelVolume;
        }

        public void setFuelVolume(int fuelVolume) {
            this.fuelVolume = fuelVolume;
        }

        public String getEngineType() {
            return engineType;
        }

        public void setEngineType(String engineType) {
            this.engineType = engineType;
        }

        public String getVhicleColor() {
            return vhicleColor;
        }

        public void setVhicleColor(String vhicleColor) {
            this.vhicleColor = vhicleColor;
        }

        public String getEngineCapacity() {
            return engineCapacity;
        }

        public void setEngineCapacity(String engineCapacity) {
            this.engineCapacity = engineCapacity;
        }

        public String getEmissionStandards() {
            return emissionStandards;
        }

        public void setEmissionStandards(String emissionStandards) {
            this.emissionStandards = emissionStandards;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public String getGpsLocation() {
            return gpsLocation;
        }

        public void setGpsLocation(String gpsLocation) {
            this.gpsLocation = gpsLocation;
        }

        public String getVehicleIdentificationNumber() {
            return vehicleIdentificationNumber;
        }

        public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
            this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        }


    }
}
