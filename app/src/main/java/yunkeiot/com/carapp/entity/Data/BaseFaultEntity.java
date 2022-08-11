package yunkeiot.com.carapp.entity.Data;

import java.io.Serializable;

public class BaseFaultEntity extends BaseEntity implements Serializable {
    private String data;
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
