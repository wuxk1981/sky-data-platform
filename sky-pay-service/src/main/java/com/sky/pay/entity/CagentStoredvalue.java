package com.sky.pay.entity;

public class CagentStoredvalue {
    private Integer id;

    private Integer cid;

    private Double totalvalue;

    private Double usedvaue;

    private Double remainvalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Double getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(Double totalvalue) {
        this.totalvalue = totalvalue;
    }

    public Double getUsedvaue() {
        return usedvaue;
    }

    public void setUsedvaue(Double usedvaue) {
        this.usedvaue = usedvaue;
    }

    public Double getRemainvalue() {
        return remainvalue;
    }

    public void setRemainvalue(Double remainvalue) {
        this.remainvalue = remainvalue;
    }
}