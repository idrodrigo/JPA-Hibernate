package com.rho.store.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryId implements Serializable{
    private static final long serialVersionUID = 4198020985304539350L;

    private String name;
    private String password;
    
	public CategoryId() {}
	public CategoryId(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryId other = (CategoryId) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}
    
    
}
