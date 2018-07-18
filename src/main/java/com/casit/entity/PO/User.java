package com.casit.entity.PO;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 13339999L;
    /**
     * id
     * @mbg.generated * 表字段 : t_user.id
     */
    private Integer id;

    /**
     * 年龄
     * @mbg.generated * 表字段 : t_user.age
     */
    private Integer userage;

    /**
     * 用户名
     * @mbg.generated * 表字段 : t_user.username
     */
    private String username;

    /**
     * 密码
     * @mbg.generated * 表字段 : t_user.password
     */
    private String password;

    /**
     * @mbg.generated * 获取 id 字段:t_user.id
     *
     * @return t_user.id, id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @mbg.generated * 设置 id 字段:t_user.id
     *
     * @param id the value for t_user.id, id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @mbg.generated * 获取 年龄 字段:t_user.age
     *
     * @return t_user.age, 年龄
     */
    public Integer getUserage() {
        return userage;
    }

    /**
     * @mbg.generated * 设置 年龄 字段:t_user.age
     *
     * @param userage the value for t_user.age, 年龄
     */
    public void setUserage(Integer userage) {
        this.userage = userage;
    }

    /**
     * @mbg.generated * 获取 用户名 字段:t_user.username
     *
     * @return t_user.username, 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * @mbg.generated * 设置 用户名 字段:t_user.username
     *
     * @param username the value for t_user.username, 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @mbg.generated * 获取 密码 字段:t_user.password
     *
     * @return t_user.password, 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @mbg.generated * 设置 密码 字段:t_user.password
     *
     * @param password the value for t_user.password, 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    
    private List<ShipAddress> adds;

	public List<ShipAddress> getAdds() {
		return adds;
	}

	public void setAdds(List<ShipAddress> adds) {
		this.adds = adds;
	}
	
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String toJSON(){
		Gson gson  = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(this);
	}
    
}