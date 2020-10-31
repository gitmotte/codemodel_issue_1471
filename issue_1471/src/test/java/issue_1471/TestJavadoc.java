package issue_1471;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JDocComment;
import com.sun.codemodel.writer.OutputStreamCodeWriter;
import org.junit.jupiter.api.Test;

public class TestJavadoc {

    @Test
    public void testJavadoc() throws JClassAlreadyExistsException, IOException {
        JCodeModel model = new JCodeModel();
        String className = "issue_1471.JavadocTest";
        JDefinedClass cls = model._class(className, ClassType.CLASS);
        JDocComment comment = cls.javadoc();
        comment.add("<b>a bold text</b><br>\n");
        comment.add("<p>a text within paraphrases</p>");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        OutputStreamCodeWriter fileCodeWriter = new OutputStreamCodeWriter(os, "UTF-8");
        model.build(fileCodeWriter);

        String generatedClass = os.toString("UTF-8");
        assertEquals("\n"
                + "package issue_1471;\n"
                + "\n"
                + "\n"
                + "\n"
                + "/**\n"
                + " * <b>a bold text</b><br>\n"
                + " * <p>a text within paraphrases</p>\n"
                + " * \n"
                + " */\n"
                + "public class JavadocTest {\n"
                + "\n"
                + "\n"
                + "}\n"
                + "", generatedClass);
    }

}
