package com.yhgc.api.enums;

public enum Type {

    Single("单桩模板",0),Engineering("工程模板",1);

    private String name;
    private Integer status;

   Type(String name, Integer status) {
       this.name=name;
       this.status=status;
   }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }
}
