package me.kafeitu.demo.activiti.activiti.form;

import org.activiti.engine.form.AbstractFormType;

/**
 * @author zengqingfa
 * @date 2019/10/17 11:30
 * @description
 * @email zengqingfa_java@163.com
 */
public class DoubleFormType extends AbstractFormType {

    @Override
    public Object convertFormValueToModelValue(String propertyValue) {
        return Double.valueOf(propertyValue);
    }

    @Override
    public String convertModelValueToFormValue(Object modelValue) {
        return String.valueOf(modelValue);
    }

    @Override
    public String getName() {
        return "double";
    }
}
