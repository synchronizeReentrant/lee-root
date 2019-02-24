package lee.demo.po;

import lee.demo.po.base.BasePo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sys_account_role")
public class AccountRoleRelated extends BasePo{
   @Column
   private String id;
   @Id
   private String roleId;
   @Id
   private String accountId;
   @Column
   private Boolean available = new Boolean(true);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }


}
