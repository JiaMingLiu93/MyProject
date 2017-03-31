package com.liu.memory.test;

/**
 * @author sx-9129 2016/12/9/0009
 * 报表的周期类型
 */
public enum PeriodType {
    DAY("day","天",0),
    WEEK("week","周",1),
    MONTH("month","月",2),
    YEAR("year","年",3),
    SEASON("season","季度",4);

    private String cnName;
    private String enName;
    private Integer order;

    PeriodType(String enName, String cnName, Integer order){
        this.enName = enName;
        this.cnName = cnName;
        this.order = order;
    }

    public String getCnName() {
        return cnName;
    }

    public String getEnName() {
        return enName;
    }

    public Integer getOrder() {
        return order;
    }

    public static PeriodType getPeriodTypeByOrder(Integer order){
        if(order==null){
            return null;
        }
        PeriodType[] all = PeriodType.values();
        //次序小于0或者大于类型的个数，则返回null
        if(order.intValue()<0||order.intValue()>=all.length){
            return null;
        }
        return all[order];
    }

    public static PeriodType getTypeByName(String name){
        if(name==null||name.trim().equals("")){
            return null;
        }
        PeriodType[] all = PeriodType.values();
        for(PeriodType type: all){
            if(type.getEnName().equals(name) || type.getCnName().equals(name)){
                return type;
            }
        }
        return null;
    }
}
