package com.example.administrator.xiangou.mine.setting.manageraddress.model;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 附带保存CheckBox状态的list
 * @email 18482195579@163.com
 * @Date 2017-05-25 18:58
 */
public class AddressMergeBean {
    List<UserAddressBean.DataBean> dataBeanList;
    List<Boolean> isCheckList;

    public AddressMergeBean(List<UserAddressBean.DataBean> dataBeanList, List<Boolean> isCheckList) {
        this.dataBeanList = dataBeanList;
        this.isCheckList = isCheckList;
    }

    public List<UserAddressBean.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<UserAddressBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    public List<Boolean> getIsCheckList() {
        return isCheckList;
    }

    public void setIsCheckList(List<Boolean> isCheckList) {
        this.isCheckList = isCheckList;
    }

    @Override
    public String toString() {
        return "AddressMergeBean{" +
                "dataBeanList=" + dataBeanList +
                ", isCheckList=" + isCheckList +
                '}';
    }
}
