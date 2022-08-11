package yunkeiot.com.carapp.entity;

import java.io.Serializable;
import java.util.List;

import yunkeiot.com.carapp.entity.Data.BaseEntity;
import yunkeiot.com.carapp.entity.Data.BaseGpsLocation;

public class CAAlarmEntity extends BaseEntity {
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
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<AlarmItems> data;
    public List<AlarmItems> getData() {
        return data;
    }

    public void setData(List<AlarmItems> data) {
        this.data = data;
    }
    public static class AlarmItems{
        private int dataType;
        private String alarmId;
        private String alarmName;
        private long alarmTime;
        private float alarmDistance;
        private BaseGpsLocation startGpsLocation;
        private BaseGpsLocation endGpsLocation;
        private String plateNumber;
        private int onlineState;
        private String deviceId;
        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }

        public String getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(String alarmId) {
            this.alarmId = alarmId;
        }

        public String getAlarmName() {
            return alarmName;
        }

        public void setAlarmName(String alarmName) {
            this.alarmName = alarmName;
        }

        public long getAlarmTime() {
            return alarmTime;
        }

        public void setAlarmTime(long alarmTime) {
            this.alarmTime = alarmTime;
        }

        public float getAlarmDistance() {
            return alarmDistance;
        }

        public void setAlarmDistance(float alarmDistance) {
            this.alarmDistance = alarmDistance;
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

        public String getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public int getOnlineState() {
            return onlineState;
        }

        public void setOnlineState(int onlineState) {
            this.onlineState = onlineState;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
    }
}
