package com.finch.god.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssTool {

    public static boolean matchXSS(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and
            // uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            Matcher result = scriptPattern.matcher(value);
            boolean flag = result.find();
            if (flag) {
                return false;
            }
            // Avoid anything in a
            // src="http://www.yihaomen.com/article/java/..." type of
            // e­xpression
            // scriptPattern =
            // Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
            // Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            // value = scriptPattern.matcher(value).replaceAll("");
            // scriptPattern =
            // Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
            // Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            // value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }
            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }


            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }


            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }

            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }

            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }

            // Avoid alert(...) e­xpressions
            scriptPattern = Pattern.compile("alert\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }

            scriptPattern = Pattern.compile("<", Pattern.CASE_INSENSITIVE);
            result = scriptPattern.matcher(value);
            flag = result.find();
            if (flag) {
                return false;
            }

        }
        return true;
    }


}
