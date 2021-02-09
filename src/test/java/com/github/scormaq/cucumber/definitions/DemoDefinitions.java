package com.github.scormaq.cucumber.definitions;

import static com.github.scormaq.utils.BeanUtils.toEnumMap;

import io.cucumber.java.Transpose;
import io.cucumber.java.en.And;
import java.io.File;
import java.time.Month;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoDefinitions {

    @And("single number {long} is passed to test")
    public void passLong(long num) {
        log.info("number received: {}", num);
    }

    @And("single word, without spaces, {word} is passed to test")
    public void passStringNoSpace(String word) {
        log.info("single word received: {}", word);
    }

    @And("whole sentence {string} is passed to test")
    public void passStringWithSpaces(String sentence) {
        log.info("String received: {}", sentence);
    }

    @And("next multi-line string is passed to test:")
    public void passMultiLineString(String str) {
        log.info("Multi-line string received: {}", str);
    }

    @And("enum value {} is passed to test")
    public void passEnum(Month month) {
        log.info("enum received: {}", month);
    }

    @And("next string/text (also )used in test: {string}")
    @And("some words {string} should pe passed into test")
    public void alterText(String str) {
        log.info("String received: {}", str);
    }

    @And("^old (?:style) ([\\S]+) params also available$")
    public void oldStyle(String arg1) {
        log.info("Arg from regex received: {}", arg1);
    }

    @And("next list is used in test:")
    public void passList(List<String> list) {
        log.info("list received: {}", list);
    }

    @And("next vertical map is used in test:")
    public void passVerticalTableMap(Map<String, String> map) {
        log.info("map received (vertical table): {}", map);
    }

    @And("next horizontal map is used in test:")
    public void passHorizontalTableMap(@Transpose Map<String, String> map) {
        log.info("map received (horizontal table): {}", map);
    }

    @And("next enum-key based map is used in test:")
    public void passEnumBasedMap(Map<String, String> map) {
        log.info("map received (enums in keys): {}", toEnumMap(map, Month.class));
    }

    @And("next DTO is used in test:")
    public void passDtoToTest(@Transpose Address address) {
        log.info("next DTO is received: {}", address);
    }

    @And("next DTOs are used in test:")
    public void passListOfDtosToTest(List<Address> addresses) {
        log.info("next DTOs is received: {}", addresses);
    }

    @And("XML gregorian calendar for {xmlgrcal} is used in test")
    public void passNonTrivialTypeAsParam(XMLGregorianCalendar calendar) {
        log.info("calendar received: {}", calendar);
    }

    @And("next test resource is used in test: {file}")
    public void passFileToTest(File file) {
        log.info("file passed to test: {}, exists: {}", file.getAbsolutePath(), file.exists());
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {

        private String addressLine1;
        private String addressLine2;
        private int id;
        private Zipcode zipCode;
        private List<String> comments;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Zipcode {

        private String code;
    }
}
