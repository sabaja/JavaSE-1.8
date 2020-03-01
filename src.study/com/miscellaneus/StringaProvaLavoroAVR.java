package com.miscellaneus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

enum Section {
    COMP("COMP"), SUB("SUB"), AVR("AVR"), MAT("MAT");

    private String section;

    Section(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }

}

public class StringaProvaLavoroAVR {

    private static String[] tempArr;
    private static Map<String, String> AVRSection;

    static {
        AVRSection = new HashMap<>();
        AVRSection.put("Prova bando gara", "N");
        AVRSection.put("Reso noto", "N");
        AVRSection.put("Intero restituito", "N");
        AVRSection.put("Valore assente", "N");
        AVRSection.put("Prima e dopo", "N");
    }

    public static void main(String[] args) {
//		String str1 = "Prova bando gara";
//		String strComp = "Prova bando gara;Prima e dopo";
        String strSub = "Prova bando gara;Valore assente;Intero restituito";
//		String strAvr = "Valore assente;Prima e dopo";
//		String strMat = "Prova bando gara;Reso noto;Intero restituito;Valore assente;Prima e dopo";
        String temp = replaceChoiche(strSub, "Intero restituito");
        System.out.println(temp);


        // mapStr.entrySet().stream().forEach( (k, v) ->
        // System.out.println(""));

    }


    private static String replaceChoiche(final String strSoa, final String choice) {
        int lenSOA = strSoa.length();
        int lenChoiche = choice.length();
        int start = strSoa.indexOf(choice);
        String strToReturn = strSoa;
        // La stringa da togliere è all'inizio
        if (0 == start) {
            // Sono uguali e non si dovrebbe mandare niente
            if (lenChoiche == lenSOA && choice.equals(strSoa)) {
                return strToReturn.replace(choice, "");
            }
            // Dopo la stringa da togliere c'è un'altra stringa
            else if (lenSOA > lenChoiche) {
                return strToReturn.replace(choice + ";", "");

            }
        }
        //La stringa da togliere non è all'inizio
        if (start > 0) {
            //La stringa da togliere è in mezzo o alla fine
//			if((lenChoiche + start) == lenSOA){
            return strToReturn.replace(";" + choice, "");

//			}
            //La stringa da togliere è in mezzo
//			else
//				return strToReturn.replace(";" + choice + ";", "");

        }
        //La stringa da togliere è in mezzo
        return "niente da togliere o errore";
    }

    private static void printMap() {
        AVRSection.forEach((key, value) -> {
            System.out.println("Key: " + key + "\tvalue:" + value);
        });
    }

    private static String manageAVRSection(final String operatorChoice) {
        return null;
    }

    private static void popolateAVRSection(final String choiche) {
        if (Objects.nonNull(choiche)) {
            tempArr = choiche.split(";");
            // List<String> strList = Arrays.asList(tempArr);
            for (String s : tempArr) {
                switch (s) {
                    case "Prova bando gara":
                        AVRSection.put("Prova bando gara", "Y");
                        break;

                    case "Reso noto":
                        AVRSection.put("Reso noto", "Y");
                        break;

                    case "Intero restituito":
                        AVRSection.put("Intero restituito", "Y");
                        break;

                    case "Valore assente":
                        AVRSection.put("Valore assente", "Y");
                        break;

                    case "Prima e dopo":
                        AVRSection.put("Prima e dopo", "Y");
                        break;

                }
            }
        }
    }

    /*
     * private String converterUpload(Value value) { return Value.values(); }
     */

}

/*
 * enum Value { YES("YES"), NO("NO");
 *
 * private String value;
 *
 * private Value(String value) { this.value = value; }
 *
 * public String getValue() { return value; }
 *
 * }
 */
