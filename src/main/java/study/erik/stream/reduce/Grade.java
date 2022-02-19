/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.stream.reduce;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : Grade.java, v 0.1 2022年02月18日 11:59 上午 yueyi Exp $
 */
@Data
public class Grade {

    private String  studentNo;
    private String  classNo;
    private Integer score;
    private String  courseName;

    public Grade(String studentNo, String classNo, Integer score, String courseName) {
        this.studentNo = studentNo;
        this.classNo = classNo;
        this.score = score;
        this.courseName = courseName;
    }

    public static void main(String[] args) {

        List<Grade> grades = data();
        HashMap<String, Integer> studentNo2SumScoreMap = grades.stream()
                .reduce(new HashMap<>(),
                        (map, grade) -> {
                            Integer sum = map.getOrDefault(grade.studentNo, 0);
                            sum += grade.getScore();
                            map.put(grade.studentNo, sum);
                            return map;
                        },
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;
                        });

        studentNo2SumScoreMap.forEach((k, v) -> System.out.printf("[k=%s, v=%d]\n", k, v));

        studentNo2SumScoreMap = grades.stream()
                .collect(HashMap::new,
                        (map, grade) -> {
                            Integer sum = map.getOrDefault(grade.studentNo, 0);
                            sum += grade.getScore();
                            map.put(grade.studentNo, sum);
                        },
                        Map::putAll);

        studentNo2SumScoreMap.forEach((k, v) -> System.out.printf("[k=%s, v=%d]\n", k, v));

        Map<String, List<Grade>> studentNo2GradeMap = grades.stream().collect(Collectors.groupingBy(Grade::getStudentNo));

    }

    public static List<Grade> data() {
        List<Grade> grades = new ArrayList<>();
        grades.add(new Grade("student1", "A", 60, "语文"));
        grades.add(new Grade("student1", "A", 70, "数学"));
        grades.add(new Grade("student1", "A", 80, "外语"));
        grades.add(new Grade("student2", "A", 60, "语文"));
        grades.add(new Grade("student2", "A", 50, "数学"));
        grades.add(new Grade("student2", "A", 40, "外语"));

        grades.add(new Grade("student3", "B", 90, "语文"));
        grades.add(new Grade("student3", "B", 95, "数学"));
        grades.add(new Grade("student3", "B", 100, "外语"));

        grades.add(new Grade("student4", "B", 80, "语文"));
        grades.add(new Grade("student4", "B", 85, "数学"));
        grades.add(new Grade("student4", "B", 90, "外语"));
        return grades;
    }

}