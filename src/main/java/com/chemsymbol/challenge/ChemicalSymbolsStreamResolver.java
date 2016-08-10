package com.chemsymbol.challenge;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Jan Koren on 8/10/2016.
 */
public class ChemicalSymbolsStreamResolver implements ChemicalSymbolsResolver {

    private static final byte SYMBOL_LENGTH = 2;

    @Override
    public boolean isValidSymbolOf(String element, String symbol) {
        Preconditions.checkNotNull(symbol);
        Preconditions.checkArgument(symbol.length() == 2, String.format("Symbol %s is not %d characters long", symbol, SYMBOL_LENGTH));

        return getPossibleSymbolCombinations(element).contains(normalizeSymbol(symbol));
    }

    /**
     * Given an element name, finds the valid symbol for that name that's first in alphabetical order.
     *
     * @param element The element name.
     * @return The first symbol in alphabetical order.
     */
    @Override
    public String getFirstSymbol(String element) {
        return getPossibleSymbolCombinations(element).stream()
                .sorted(Comparator.<String>naturalOrder())
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }

    @Override
    public int getNumberOfValidSymbols(String element) {
        return getPossibleSymbolCombinations(element).size();
    }

    private Set<String> getPossibleSymbolCombinations(String element) {
        Preconditions.checkNotNull(element);
        Preconditions.checkArgument(element.length() >= SYMBOL_LENGTH, String.format("Element %s must be at least %d characters long", element, SYMBOL_LENGTH));

        return element.chars()
                .collect(HashSet::new,
                         (combinations, firstLetter) -> {
                             final String rightSubstring = element.substring(element.indexOf(firstLetter) + 1);
                             combinations.addAll(createSymbolSubset((char) firstLetter, rightSubstring));
                         },
                         HashSet::addAll);
    }

    private Set<String> createSymbolSubset(char firstLetter, String rightSubstring) {
        return rightSubstring.chars()
                .mapToObj(secondLetter -> new StringBuilder()
                        .append(firstLetter)
                        .append((char) secondLetter)
                        .toString())
                .map(ChemicalSymbolsStreamResolver::normalizeSymbol)
                .collect(toSet());
    }

    private static String normalizeSymbol(String symbol) {
        return StringUtils.capitalize(symbol.toLowerCase());
    }
}
