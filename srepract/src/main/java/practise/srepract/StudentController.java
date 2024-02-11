package practise.srepract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create Student (POST)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // Get All Students (GET)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get Student by ID (GET)
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));
    }

    // Update Student (PUT)
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable(value = "id") Long studentId,
                                 @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setEmail(studentDetails.getEmail());

        return studentRepository.save(student);
    }

    // Delete Student (DELETE)
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable(value = "id") Long studentId) {
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        studentRepository.delete(student);
    }
}
