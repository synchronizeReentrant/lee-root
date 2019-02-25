package lee.demo.po;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lee.demo.po.base.BasePo;

public class Role extends BasePo {
   private String id;

   private String role;

   private String description;

   private Boolean available = new Boolean(true);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
