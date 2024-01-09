package com.example.pluralizadordemo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PluralNounsList {

    List<String> validNouns;

    List<String> invalidNouns;

    List<String> rules;

    List<Integer> rulesCounter;

}
