

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestClass {

    private String uninitializedVariable;
    @Test
    public void test() throws IOException {
        //given
        File file = new File("pom.xml");

        //when
        String content = Files.readString(file.toPath());


        //then
        Assertions.assertNotNull(content);
    }
}
