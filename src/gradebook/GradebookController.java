package gradebook;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GradebookController {
    private static final Pattern ID = Pattern.compile("S\\d{4}");
    private final GradebookService service;

    public GradebookController(GradebookService service) {
        this.service = service;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Map<String, Object> body) {
        Object id = body.get("studentId");
        Object name = body.get("name");
        if (!(id instanceof String sid) || !ID.matcher(sid).matches())
            throw new ApiException(HttpStatus.BAD_REQUEST, "studentId must be S followed by 4 digits");
        if (!(name instanceof String n) || n.isBlank())
            throw new ApiException(HttpStatus.BAD_REQUEST, "name is required");
        return service.addStudent(new Student(sid, n));
    }

    @GetMapping("/students")
    public List<Student> allStudents() {
        return service.allStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable String studentId) {
        return service.getStudent(studentId);
    }

    @PostMapping("/students/{studentId}/scores")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addScore(@PathVariable String studentId,
                                        @RequestBody Map<String, Object> body) {
        Object raw = body.get("score");
        if (!(raw instanceof Integer score))
            throw new ApiException(HttpStatus.BAD_REQUEST, "score is required and must be an integer");
        Student s = service.recordScore(studentId, score);
        return Map.of("studentId", s.getStudentId(), "score", score, "average", s.getAverage());
    }

    @GetMapping("/students/{studentId}/grade")
    public Map<String, Object> getGrade(@PathVariable String studentId) {
        Student s = service.getStudent(studentId);
        if (s.getScores().isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "no scores recorded");
        }
        String letter = GradebookService.letterGrade(s.getAverage());
        return Map.of("studentId", s.getStudentId(), "average", s.getAverage(), "letter", letter);
    }

    @GetMapping("/grade-scale")
    public Map<String, Object> gradeScale(@RequestParam int score) {
        try {
            String letter = GradebookService.letterGrade(score);
            return Map.of("score", score, "letter", letter);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}