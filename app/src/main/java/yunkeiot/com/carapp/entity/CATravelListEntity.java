package yunkeiot.com.carapp.entity;

import java.util.List;

import yunkeiot.com.carapp.entity.Data.BaseEntity;
import yunkeiot.com.carapp.entity.Data.BaseGpsLocation;

public class CATravelListEntity extends BaseEntity {

        private int page;
        private int pageSize;
        private int totalRows;
        private int pageNum;
        private List<DataBean> data;
        public int getPageNum() {
        return pageNum;
    }

        public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }
        public static class DataBean{
            private String plateNumber;
            private String deviceId;
            private int tvavelId;
            private long travelStartTime;
            private long travelEndTime;
            private float mileage;
            private float fuel;
            private float driverTime;
            private float avgSpeed;
            private float maxSpeed;
            private float avgFuel;
            private BaseGpsLocation startGpsLocation;
            private BaseGpsLocation endGpsLocation;

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

            public int getTravelId() {
                return tvavelId;
            }

            public void setTravelId(int travelId) {
                this.tvavelId = travelId;
            }

            public long getTravelStartTime() {
                return travelStartTime;
            }

            public void setTravelStartTime(long travelStartTime) {
                this.travelStartTime = travelStartTime;
            }

            public long getTravelEndTime() {
                return travelEndTime;
            }

            public void setTravelEndTime(long travelEndTime) {
                this.travelEndTime = travelEndTime;
            }

            public float getMileage() {
                return mileage;
            }

            public void setMileage(float mileage) {
                this.mileage = mileage;
            }

            public float getFuel() {
                return fuel;
            }

            public void setFuel(float fuel) {
                this.fuel = fuel;
            }

            public float getDriverTime() {
                return driverTime;
            }

            public void setDriverTime(float driverTime) {
                this.driverTime = driverTime;
            }

            public float getAvgSpeed() {
                return avgSpeed;
            }

            public void setAvgSpeed(float avgSpeed) {
                this.avgSpeed = avgSpeed;
            }

            public float getMaxSpeed() {
                return maxSpeed;
            }

            public void setMaxSpeed(float maxSpeed) {
                this.maxSpeed = maxSpeed;
            }

            public float getAvgFuel() {
                return avgFuel;
            }

            public void setAvgFuel(float avgFuel) {
                this.avgFuel = avgFuel;
            }

            public BaseGpsLocation getStartGpsLocation() {
                return startGpsLocation;
            }

            public void setStartGpsLocation(BaseGpsLocation startGpsLocation) {
                this.startGpsLocation = startGpsLocation;
            }

            public BaseGpsLocation getEndGpsLocation() {
                return endGpsLocation;
            }

            public void setEndGpsLocation(BaseGpsLocation endGpsLocation) {
                this.endGpsLocation = endGpsLocation;
            }

        }
    }
