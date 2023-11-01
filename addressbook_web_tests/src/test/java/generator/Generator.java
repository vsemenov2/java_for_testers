package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactDate;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static test.TestBase.randomFile;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;


    public static void main(String[] args) throws IOException {
       var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
       generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)){
            return  generateGroups();
        } else if ("contacts".equals(type)) {
            return  generateContacts();
        } else {
           throw  new IllegalArgumentException("Неизвестный тип данных" + type);
        }

    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }

        return result;
    }

    private Object generateContacts() {
        var result = new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++) {
            result.add(new ContactDate()
                    .withFirstname(CommonFunctions.randomString(i * 10 ))
                    .withMiddlename(CommonFunctions.randomString(i * 10 ))
                    .withLastname(CommonFunctions.randomString(i * 10 ))
                    .withAddress(CommonFunctions.randomString(i * 10 ))
                    .withPhoto(randomFile("src/test/resources/images")));

        }
        return result;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);

            try (var write = new FileWriter(output)) {
                write.write(json);
            }


        } else if ("yaml".equals(format))  {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format))  {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных" + format);
        }

    }
}