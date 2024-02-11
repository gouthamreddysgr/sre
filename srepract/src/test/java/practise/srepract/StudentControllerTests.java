package practise.srepract;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(StudentController.class)
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testGetAllStudents() throws Exception {
        Student student1 = new Student("John", 20, "john@example.com");
        Student student2 = new Student("Alice", 22, "alice@example.com");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Alice"));
    }
}
