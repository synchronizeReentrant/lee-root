package lee.demo.po;

import lee.demo.po.base.BasePo;

import javax.persistence.Column;
import javax.persistence.Id;

@javax.annotation.Resource
public class Resource extends BasePo {
     @Id
     private String id;
     @Column
     private String name;
     @Column
     private String type;
     @Column
     private String url;
     @Column
     private String parentId;
     @Column
     private String permission;
     @Column
     private Boolean available = new Boolean(true);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
