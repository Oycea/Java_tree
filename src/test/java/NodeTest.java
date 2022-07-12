import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import ru.ac.uniyar.mf.summer.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {
    @Test
    void createNode(){
        Node node = new Node("Корень");
        assertEquals("Корень", node.getName());
    }

    @Test
    void addNode() {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.addNode(child);
        assertEquals(1, root.getChilds().size());
        assertEquals("Лист", root.getChilds().get(0).getName());
    }

    @Test
    void findChild(){
        Node root = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root.addNode(child1);
        root.addNode(child2);
        root.addNode(child3);
        assertEquals(true,root.findChild("Лист1"));
    }

    @Test
    void deleteChild(){
        Node root = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root.addNode(child1);
        root.addNode(child2);
        root.addNode(child3);
        root.deleteChild(child1.getID());
        assertEquals(false,root.findChild("Лист1"));
    }

    @Test
    void deleteAllChilds(){
        Node root = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root.addNode(child1);
        root.addNode(child2);
        root.addNode(child3);
        root.deleteAllChilds();
        assertEquals(0,root.getChilds().size());
    }

    @Test
    void changeChild(){
        Node root = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root.addNode(child1);
        root.addNode(child2);
        root.addNode(child3);
        root.changeChild(child1.getID(),"neeeew");
        assertEquals(true,root.findChild(("neeeew")));
    }

    @Test
    void checkPrint(){
        Node root = new Node("Корень");
        Node child = new Node("Лист1");
        Node child1 = new Node("Лист2");
        Node child2 = new Node("Лист3");
        Node child3 = new Node("Лист4");
        root.addNode(child);
        root.addNode(child1);
        root.addNode(child2);
        child1.addNode(child3);
        String s=root.indentedPrintNode(root,0);
        assertEquals("Корень\n Лист1\n Лист2\n  Лист4\n Лист3\n",s);
    }

    @Test
    void checkPrintFile() throws IOException {
        Node root=new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        Node child4 = new Node("Лист4");
        root.addNode(child1);
        root.addNode(child2);
        root.addNode(child3);
        child1.addNode(child4);
        root.printFile();
        String s=root.indentedPrintNode(root,0);
        String path = "file.txt";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String content = new String (bytes);
        assertEquals(content,s);
    }

    @Test
    void printToJson() throws JsonProcessingException {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.addNode(child);
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String actual = objectMapper.writeValueAsString(root);
        System.out.println(actual);
    }

    @Test
    void ReadFileJSON() throws IOException {
        String path = "C:\\Users\\Admin\\IdeaProjects\\tree\\target\\input.json";

        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String jsonString = new String(bytes);

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        Node actual = objectMapper.readValue(jsonString, Node.class);

        assertEquals("Корень", actual.getName());
        assertEquals(1, actual.getChilds().size());
        assertEquals("Лист", actual.getChilds().get(0).getName());
    }

}
