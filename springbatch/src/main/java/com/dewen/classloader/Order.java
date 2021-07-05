//package com.dewen.classloader;
//
//import org.springframework.cglib.beans.BeanCopier;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author wxw
// * @version 2019/1/21
// */
//public class Order {
//    private String code;
//    private List<String> offers;
//    private Map<String, Object> features;
//
//    public static Order.Builder builder() {
//        return new Builder();
//    }
//
//    //省略getter setter
//    public static class Builder {
//
//        private OrderState orderState = new OrderState();
//        private static final BeanCopier orderCopier = BeanCopier.create(OrderState.class, Order.class, false);
//
//        private class OrderState {
//            private String code;
//            private Map<String, Object> features;
//            private List<String> offers;
//            //省略getter setter
//        }
//
//        public Builder code(String code) {
//            orderState.code = code;
//            return this;
//        }
//
//        public Builder features(Map<String, Object> features) {
//            orderState.features = features;
//            return this;
//        }
//
//        public <T> Builder feature(String key, T obj) {
//            if (orderState.features == null) {
//                orderState.features = new HashMap<>();
//            }
//            orderState.features.put(key, obj);
//            return this;
//        }
//
//        public Builder offers(List<String> offers) {
//            orderState.offers = offers;
//            return this;
//        }
//
//        public Builder offer(String offer) {
//            if (orderState.offers == null) {
//                orderState.offers = new ArrayList<>();
//            }
//            orderState.offers.add(offer);
//            return this;
//        }
//
//        public Order build() {
//            Order order = new Order();
//            orderCopier.copy(orderState, order, null);
//            orderState = null;
//            return order;
//        }
//    }
//}