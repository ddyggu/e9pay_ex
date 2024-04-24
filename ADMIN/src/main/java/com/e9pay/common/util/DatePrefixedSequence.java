package com.e9pay.common.util;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class DatePrefixedSequence extends SequenceStyleGenerator {

    private final String prefixFormat = "yyMMddhhmmss";
    private final SimpleDateFormat sdf = new SimpleDateFormat(prefixFormat);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        // ����Ÿ�� ��ƼƼ�� ������ ����Ÿ�� ��ƼƼ�� ����� �������� ������
        Class<?> clazz = obj.getClass();
        Class<?> superClazz = clazz.getSuperclass();
        if(superClazz.isAnnotationPresent(Entity.class)) {
            clazz = superClazz;
        }
        // GenericGenerator�� ������ Sequence �̸��� �������� ����
        String sequenceName = ""; boolean parentBreak = false;
        for(Field field : clazz.getDeclaredFields()) {
            for(Annotation a : field.getDeclaredAnnotations()) {
                if (GenericGenerator.class.equals(a.annotationType())) {
                    GenericGenerator g = (GenericGenerator) a;
                    sequenceName = Arrays.stream(g.parameters()).filter(p -> "sequence_name".equals(p.name())).map(Parameter::value).findFirst().orElse("");
                    parentBreak = true;
                    break;
                }
            }
            if(parentBreak) break;
        }

        // ������ Sequence �̸����� ����Ƽ�� ������ ����
        String datePrefix = sdf.format(new Date());
        Long sequenceValue = ((Number) session.createNativeQuery("SELECT nextval(" + sequenceName +")", Long.class).getSingleResult()).longValue();
        return datePrefix + String.format("%08d", sequenceValue);
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
        super.configure(type, params, serviceRegistry);
    }

}