package lee.demo.po;

import lee.demo.po.base.BasePo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="sys_user")
public class User extends BasePo {
    @Id
    private String id;
    @Column
    private String organizationId;
    @Column
    private String name;
    @Column
    private Date birthday;
    @Column
    private String address;
    @Column
    private String educationBackground;
    @Column
    private String idNumber;
    @Column
    private String mobile;

    public  static enum DP {
        id("id"),organizationId("organizationId"),name("name"),birthday("birthday"),
        address("address"),educationBackground("educationBackground"),idNumber("idNumber"),
        mobile("mobile"),updateTime("updateTime"),createTime("createTime");
        private String fieldname;
        private DP(String fieldname) {
            this.fieldname = fieldname;
        }
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                "organizationId='" + organizationId + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthday +
                ", address='" + address + '\'' +
                ", educationBackgroud='" + educationBackground + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime='" + getCreateTime() + '\'' +
                ", updateTime='" + getUpdateTime() + '\'' +
                '}';
    }
}
