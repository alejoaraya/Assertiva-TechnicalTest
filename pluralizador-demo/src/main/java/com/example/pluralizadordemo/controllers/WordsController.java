package com.example.pluralizadordemo.controllers;

import com.example.pluralizadordemo.entities.PluralNounsList;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/words")
public class WordsController {

    @PostMapping("/pluralize")
    public PluralNounsList pluralizeNouns(@RequestBody List<String> nouns) {
        List<String> invalidNouns = new ArrayList<>();
        List<String> prevNouns = nouns;

        nouns = removeInvalidCharacters(nouns, invalidNouns);
        List<String> modifiedNouns = convertToLowerCase(nouns);
        List<Integer> ruleCounts = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        List<String> rules = new ArrayList<>(Arrays.asList(
                "Regla 1- Si el sustantivo termina en vocal (a, e, i, o, u) se agrega s.",
                "Regla 2- Si el sustantivo termina en s o x, queda igual en plural.",
                "Regla 3- Si el sustantivo termina en z, se cambia la z por ces.",
                "Regla 4- Si el sustantivo termina en otra consonante se agrega es."));

        pluralize(modifiedNouns, ruleCounts);

        PluralNounsList result = new PluralNounsList(modifiedNouns, invalidNouns,rules, ruleCounts);

        printResults(ruleCounts, modifiedNouns, invalidNouns, prevNouns, rules);

        return result;
    }

    private void printResults(List<Integer> ruleCounts, List<String> modifiedNouns, List<String> invalidNouns, List<String> prevNouns, List<String> rules) {
        System.out.println("PLURALIZADOR");
        System.out.println("------------------------------------------");
        System.out.println("REGLAS:");
rules.forEach(rule -> System.out.println(rule));
        System.out.println("------------------------------------------");
        System.out.println("Listado de sustantivos a pluralizar:");
        System.out.println(prevNouns);
        System.out.println("Listado pluralizado:");
        System.out.println(modifiedNouns);
        if (!invalidNouns.isEmpty()) {
            System.out.println("------------------------------------------");
            System.out.println("Se encontraron errores, por favor volver a ingresar estos sustantivos sin tildes, Ñ o algun signo");
            System.out.println(invalidNouns);
            System.out.println("------------------------------------------");
        }
        System.out.println("Cantidad de sustantivos que utilizaron cada regla");
        for (int i = 0; i < ruleCounts.size(); i++) {
            System.out.println(ruleCounts.get(i) + " sustantivos fueron modificados utilizando la regla " + (i + 1));
        }
    }

    private List<String> removeInvalidCharacters(List<String> nouns, List<String> invalidNouns) {
        final String REGEX_NOT_ACCEPTED = "[ñÑáéíóúÁÉÍÓÚüÜ{}/.!?@#$%&*()-=+|;:<>\\d]";
        List<String> validNouns = new ArrayList<>();
        Matcher matcherNotAccepted = Pattern.compile(REGEX_NOT_ACCEPTED).matcher("");

        for (String noun : nouns) {
            matcherNotAccepted.reset(noun);
            if (!matcherNotAccepted.find()) {
                validNouns.add(noun);
            } else {
                invalidNouns.add(noun);
            }
        }
        return validNouns;
    }

    private void pluralize(List<String> modifiedNouns, List<Integer> ruleCounts) {
        for (int i = 0; i < modifiedNouns.size(); i++) {
            String nouns = modifiedNouns.get(i);
            String ending = nouns.substring(nouns.length() - 1);
            String pluralNoun = "";

            if (hasVowel(ending)) {
                pluralNoun = nouns + "s";
                ruleCounts.set(0, ruleCounts.get(0) + 1);
            } else if (ending.contains("s") || ending.contains("x")) {
                pluralNoun = nouns;
                ruleCounts.set(1, ruleCounts.get(1) + 1);
            } else if (ending.contains("z")) {
                pluralNoun = nouns.substring(0, nouns.length() - 1) + "ces";
                ruleCounts.set(2, ruleCounts.get(2) + 1);
            } else if (hasConsonant(ending)) {
                pluralNoun = nouns + "es";
                ruleCounts.set(3, ruleCounts.get(3) + 1);
            }
            modifiedNouns.set(i, pluralNoun);
        }
    }

    private boolean hasVowel(String ending) {
        final String REGEX_VOWEL = "[aeiouAEIOU]";
        Matcher matcherVowel = Pattern.compile(REGEX_VOWEL).matcher(ending);
        return matcherVowel.find();
    }

    private boolean hasConsonant(String ending) {
        final String REGEX_CONSONANT = "[^aeiouAEIOU]";
        Matcher matcherConsonant = Pattern.compile(REGEX_CONSONANT).matcher(ending);
        return matcherConsonant.find();
    }


    private List<String> convertToLowerCase(List<String> nouns) {
        List<String> modifiedNouns = new ArrayList<>();
        nouns.forEach(noun -> modifiedNouns.add(noun.toLowerCase()));
        return modifiedNouns;
    }


}
