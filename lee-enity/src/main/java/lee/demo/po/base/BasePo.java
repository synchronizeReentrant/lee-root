package lee.demo.po.base;



import lee.base.entity.base.BaseEntity;
import lee.base.entity.base.Bean;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;


public abstract class BasePo extends BaseEntity implements Bean {

    @Column(name="update_time")
    private Date updateTime;
    @Column(name="create_time")
    private Timestamp creatTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }
}
