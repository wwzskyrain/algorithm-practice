/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : ToMap.java, v 0.1 2021年11月23日 3:14 下午 yueyi Exp $
 */
public class ToMap {

    public static class Person {
        private String name;
        private String address;

        public Person(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static void main(String[] args) {

        List<Person> personList = Arrays.asList(
                new Person("n1", "a1"),
                new Person("n2", "a2"),
                new Person("n3", "a3"),
                new Person("n3", "a4")
        );

        Map<String, String> map = personList.stream().collect(Collectors.toMap(
                Person::getName,
                Person::getAddress,
                (value1, value2) -> value1 + value2
        ));
        map.forEach((name, address) -> System.out.printf("[%s,%s]", name, address));

    }
}