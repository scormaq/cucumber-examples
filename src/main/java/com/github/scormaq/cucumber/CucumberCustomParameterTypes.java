package com.github.scormaq.cucumber;


import com.github.scormaq.utils.FileUtils;
import io.cucumber.java.ParameterType;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.SneakyThrows;

public class CucumberCustomParameterTypes {

    @SneakyThrows
    @ParameterType("\"([^\"]+)\"")
    public XMLGregorianCalendar xmlgrcal(String val) {
        DateTimeFormatter expectedCalendarFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        LocalDate localDate = LocalDate.parse(val, expectedCalendarFormat);
        return  DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
    }

    @ParameterType(".*?")
    public File file(String path) {
        return FileUtils.getTestResource(path);
    }
}
