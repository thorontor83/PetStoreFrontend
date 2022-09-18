package com.evoxon.petStore.jwt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    public String sub;
    public Long customerId;
    public String[] roles;
    public Long exp;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "sub='" + sub + '\'' +
                ", customerId=" + customerId +
                ", roles=" + Arrays.toString(roles) +
                ", exp=" + exp +
                '}';
    }
}
