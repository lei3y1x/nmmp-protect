package xhu.spike.fusion.encrypt.string;

import xhu.spike.fusion.*;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.utils.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringEncryption extends Action {
    private static final Pattern p = Pattern.compile("(\"\"|\"[\\s\\S]*?((?!\\\\).)\")");
    private final Algorithm algorithm;

    public static void testPattern() {
        String target = " Object j = invokeConstructor(Class.forName(\"dalvik.system.DexClassLoader\"),path.toString(),\"\",\"\",\"\\\"1\\\"\",o);" +
                "\"UEsDBBQACAgIAE2KOFQAAAAAAAAAAAAAAAAUAAQATUVUQS1JTkYvTUFOSUZFU1QuTUb+ygAA803My0xLLS7RDUstKs7Mz7NSMNQz4OVySa3Q9clPTiwBCyXnJBYXpxbrpaRW8HI5F6UmlqSm6DpVWimkVACVG5rxcvFyAQBQSwcI8N6zmEcAAABJAAAAUEsDBBQACAgIAE2KOFQAAAAAAAAAAAAAAAAJAAAATUVUQS1JTkYvAwBQSwcIAAAAAAIAAAAAAAAAUEsDBBQACAgIAE2KOFQAAAAAAAAAAAAAAAALAAAAY2xhc3Nlcy5kZXh1mH9sW9UVx899/v0jtp9/5IebpG6S0tLSOEkLS0kamiYpNThpiEOg7VD7Yr8kJs6z6/ecpmuBFtgoCI1qAzT+2DS2wTohULUfEmJoQ9s/1TSNamJTN6EJaWzilzS2CY0NRPe99z3HDg22Pu/ce+6595177znXzy+nrnh7dt5Im/904ckn3JdK794w9Mlv3mm9/O7rb3quPNnx8JlGohIRrczsCpP1uRgjeohM/WZwWiJqhwzZiJzc1k60C/ICJFR0AMorQdQh+/1E+8BtYAYcAveAYyAL5sEiKIH7wGPgKfAi+Cv4DLQ0EO0EdwMDPApeAr8D7wFvgCgObgLj4BhYBk+A58Er4DJ4G3wMovCtD+wDXwZ5UAYVcBLcDx4Gj4Lz4DvgB+CH4AXwS/AGeBd8BFiIqAHEQRfoBpOgAp4CPwYvg9fAH8Cb4O/gA/AR+Ax4ZaIwaAVbwDbQB3aD28BhMA9OgG+Ab4OL4DXwOngLfAA2YNc6wXaQBHwXB8GtYAocBrNgAZwC58H3wM/A78HfwMfAHYE/oBNcD8aBAubAV8BD4DHwDPg++BF4Cfwc/ApcAq+DP4I/g79EzNhwAC/wAWwtYfsIW0JBHk8AUyAeeDCnKEDoEUKSmkAzaAFxsAG0khmHG0ECbAIdoBN0WbF6HdgCtoE+sJPMWL0R3ESmT9WP3ZIv+2rl13ymz8yqu4AHXILebZW5vdOaF/84rXlUyxGr7LbmVLWPWXqPNb/Llr6lTs/necXSt1v6bdac+We7VX4LNt1W+R1fTX+hrvxhnc3HdXq+EdWy21+zCdXpL9bZc/+TVrkZNjuschfKN1jlHr+53gnLTxtmf0qscQtJltwqZIIOW/UKmXFxPZAQEUxImeaEDNG8JRcsmbfa77Xqi5YsiPtFRH8bxtHF+EFrj81x7bD7kpB+6hcySrst/d1WfUXICN1PPAYYjQjZSmURB2E6LvbUS3cI6aIpIZ00I6Sf7hIyRifEXtppUMSIg6b52iKKmZAeUkS8NIm6TG00IaSfJoW00TFLnyOeG43CLox5jFryoJAb6AERa5I4p6PIrDuJ506QiiK+zHnHkUWmDNKyiC8f3UM8l4J0qyVTQropQ2aOHQJt2B8mZICWxDqb+dpkxVgP5Ark/lh1v2vtF+vaC1Y7zy9mtfMPbz+H9vvQPhwzx98O2Rsz7XYLyUT57YjZvxTimRDAvcwsfi9i2gaEf5LQ/Wsd3X8/pxO5HTXHPI6gZ3VjuqPX9g+to2uKXjtme9TM2yOs5qsdLbx1c9Q886Z7cU9xCvql44kAzrAAalqI58DWT8y18fB5s0Zv9Qzqi5pnUpyOIAfs4p7VtemPrl2bqn7P6vyClt78jEbNvZpFq2z5zmeUqtonQtjHgIh/bjcF/TCPt8gx6CKsiXaFBuDJXtISYfTwIovi+N5SVw+jvqeu7kV9aLXejxgM7+3EiR1mcWkPjWN54myIxnHD8Ka4tFdowr1x6RZR0noiZLfxOTfDUwe+Cnz6lPvkCts73DIZrBFx75fj4V5EsUxuFqZSTztkh7e+9b41rV6mhTbCH6+8W3ZgBzpQ5hpkuNwv+1HehLIv3Ii79crh6KB8iDpCbdCFMEYTlRJ9KBlsJ65+mY+8EdnjcmmhbpwNWqgXV68cCV8SZ5aEu+4M9UPfA73f3+GAF4lO+iqFGyIy2blNp3MTReSrV7XEFpxp3gau67sch84897S915F6ees/GnyuaR4xPuq3c8+7sK79Ll5KYuwA5Gaskmu6wxNCOcHPJplLL/nDEeSCwWJ8RaLV1XBLEXLbolTKtJJnU1weE+ukJdoQif5AXH6QImEexWH80naQGz/XfJRW9B+PhiPVUbSerRRg/iD3tmbfuWo/3lhvu4OCrN7756+a3m99tkFeb1Zb5AbZNb313w2+rQ/xXLkZvvHfiRji9H5x/jjpcSEZnYecRLvtgcbT/2H0Pnug8X/sfWblInI3Zv6GuXo3Mhd1SDhBQ3yFtUQU+aiFmjCKH9HRzKWkJTZA66cYBtAScey2lmhBSweLo68Nu+jGL18psV30vUGcJuaz8HofyZLc5qdO88zkkuen0zojmy3ZYslNVHseEf5jVSXR3iH0DkvvIL+ou6262zrDPVZ/2dLL0NtFf/6NiTzfVDc+f5ZyD2YLeS1vDJFz0JTekfRwJnN0fDg1QSxFUipN7DZiaZLSqKXTZEvj4kvfpRayxSW1q3egrtI3QJ5qZYAa04qWKxfzuaRSKiWHs0Z+OW+cHKBda/WlUiGfVYx8Ueuq2qTzc2r2ZLagjiiFwqySXdQHqOWLetU3ZYuaoWpGcoTLFWOAmq9pSgkxQJ3XtJSWkpO4lzKvjisarmWcgKtGRT25r5Iv5LpmxqYyqYMTAxRZ26blCiofNacUlvOLSUXTiobwLzmmZQtFPa/Nj6vGQjE3QO3rGKU0TS2PFBRd53e9tn16oVw8odf11U/qhrqUHFVXRK90Uclxjw\"";
        Tools.log("StringEncryption:testPattern");
        Tools.log(target);
        Tools.log(p.toString());
        Tools.log("--------------------");
        Matcher m = p.matcher(target);
        while (m.find()) {
            Tools.log(m.group(0));
        }
    }

    public StringEncryption(JavaLoader javaLoader) {
        super(javaLoader);
        algorithm = random.pick(
                new A001(random),
                new A002(random),
                new A003(random),
                new A004(random),
                new A005(random),
                new A006(random)
        );
//        algorithm = new A005(random);
    }

    @Override
    public void doAction() {
        loader.getClazz().addMember(StaticJavaParser.parseMethodDeclaration(algorithm.genMethod()));
        String[] lines = loader.getRoot().toString().split(LINE_SEPARATOR);
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(handleLine(line)).append('\n');
        }
        loader.refresh(sb.toString());
    }

    private String handleLine(String line) {
        Matcher m = p.matcher(line);
        while (m.find()) {
            String str = m.group(0);
            if (str.length() <= 2) {
                continue;
            }
            String content = str.substring(1, str.length() - 1);
            String unescaped = StringEscapeUtils.unescapeJava(content);
            String encrypted = algorithm.encrypt(unescaped);
            String escaped = StringEscapeUtils.escapeJava(encrypted);
            line = line.replace(str, algorithm.recode(escaped));
        }
        return line;
    }
}
