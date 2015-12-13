package com.protocal;

/**
 * Created by JM on 2015-12-12.
 */
public enum ProtocalEnum {

    TYPE(1,0xabef),MAINVER(2,0x01),SUBVER(3,0x02);

    private int value;
    private int index;

    private ProtocalEnum(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public static Integer getValue(Integer index){
        if(null == index)
            return null;
        for(ProtocalEnum p:ProtocalEnum.values()){
            if(p.getIndex() == index){
                return p.getValue();
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
