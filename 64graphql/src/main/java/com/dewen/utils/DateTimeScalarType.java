// package com.dewen;
//
// import cn.hutool.core.date.DateUtil;
// import graphql.schema.Coercing;
// import graphql.schema.GraphQLScalarType;
// import org.springframework.stereotype.Component;
//
// import java.text.ParseException;
// import java.util.Date;
//
// @Component
// public class DateTimeScalarType extends GraphQLScalarType {
//
//     private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
//
//     public DateTimeScalarType() {
//         super("DateTime", "DateTime value", new Coercing<Date, String>() {
//             @Override
//             public String serialize(Object o) {
//                 Date date = (Date) o;
//                 return DateUtil.format(date, DATE_FORMAT);
//             }//
//             @Override
//             public Date parseValue(Object o) {
//                 String value = String.valueOf(o);
//                 if ("null".equalsIgnoreCase(value) || "".equalsIgnoreCase(value)) {
//                     return null;
//                 }
//                 try {
//                     return DateUtil.parseDate(value, DATE_FORMAT);
//                 } catch (ParseException e) {
//                     e.printStackTrace();
//                     return null;
//                 }
//             }
//
//             @Override
//             public Date parseLiteral(Object o) {
//                 String value = String.valueOf(o);
//                 if ("null".equalsIgnoreCase(value) || "".equalsIgnoreCase(value)) {
//                     return null;
//                 }
//                 try {
//                     return DateUtils.parseDate(value, DATE_FORMAT);
//                 } catch (ParseException e) {
//                     e.printStackTrace();
//                     return null;
//                 }
//             }
//         });
//     }
//
// }