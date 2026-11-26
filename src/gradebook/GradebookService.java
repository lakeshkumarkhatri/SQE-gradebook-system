package gradebook;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GradebookService {
    public static final int MAX_SCORES = 5;
    private final Map<String, Student> students = new LinkedHashMap<>();

    public synchronized Student addStudent(Student student) {
        if (students.containsKey(student.getStudentId()))
            throw new ApiException(HttpStatus.CONFLICT, "studentId already exists");
        students.put(student.getStudentId(), student);
        return student;
    }

    public synchronized List<Student> allStudents() {
        return new ArrayList<>(students.values());
    }

    public synchronized Student getStudent(String studentId) {
        Student s = students.get(studentId);
        if (s == null)
            throw new ApiException(HttpStatus.NOT_FOUND, "Unknown student: " + studentId);
        return s;
    }

    public synchronized Student recordScore(String studentId, int score) {
        Student s = getStudent(studentId);
        if (score < 0 || score > 100)
            throw new ApiException(HttpStatus.BAD_REQUEST, "score must be between 0 and 100");
        if (s.getScores().size() >= MAX_SCORES)
            throw new ApiException(HttpStatus.BAD_REQUEST, "limit of 5 scores reached");
        s.addScore(score);
        return s;
    }

    public static String letterGrade(int score) {
        if (score < 0 || score > 100)
            throw new IllegalArgumentException("score must be between 0 and 100");
        if (score >= 90) return "A";
        if (score > 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    synchronized void voidReset() {
        students.clear();
    }
    
    public synchronized void reset() {
        students.clear();
    }
}