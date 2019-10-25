package com.yj.library.bean;

/**
 * desc:
 * time: 2019/10/24
 *
 * @author 银进
 */
public class TagBean {
    public TagBean(String tagName, boolean isSelect) {
        this.tagName = tagName;
        this.isSelect = isSelect;
    }

    private String tagName;
    private boolean isSelect=false;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
