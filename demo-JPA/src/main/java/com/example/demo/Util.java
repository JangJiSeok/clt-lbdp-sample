package com.example.demo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.LinkedList;
import java.util.Locale;

public class Util {
       String name="JangJaeOck";

    public static void main(String... args) {
       long lng= Long.valueOf( "20190831083010004");

    }

    public void test() {

        //NumberUtils.

        LinkedList<String> result = new LinkedList<>();
        for (String part : StringUtils.splitByCharacterTypeCamelCase(name)) {
            if (part == null || part.trim().isEmpty()) {
                // skip null and space
                continue;
            }
            //part = applyAbbreviationReplacement( part );
            result.add(part.toLowerCase(Locale.ROOT));
        }
    }
}
