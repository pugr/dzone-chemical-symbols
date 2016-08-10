package com.chemsymbol.challenge;

/**
 * The interface for resolving information about symbols for chemical elements on the planet Splurth.
 *
 * Created by Jan Koren on 8/8/2016.
 */
public interface ChemicalSymbolsResolver {

    /**
     * Determines whether the proposed symbol is the correct symbol for the given element. The following rules apply:
     *
     * - All chemical symbols must be exactly two letters
     * - Both letters in the symbol must appear in the element name, but the first letter of the element name does not necessarily need to appear in the symbol.
     * - The two letters must appear in order in the element name.
     * - If the two letters in the symbol are the same, it must appear twice in the element name.
     *
     * @param element The chemical element.
     * @param symbol  The symbol to be validated.
     * @return true if the symbol is valid for the element, otherwise false.
     */
    boolean isValidSymbolOf(String element, String symbol);

    /**
     * Given the element name, finds the valid symbol for that name that's first in alphabetical order.
     *
     * @param element The element name.
     * @return The first symbol in alphabetical order.
     */
    String getFirstSymbol(String element);

    /**
     * Given an element name, find the number of distinct valid symbols for that name.
     *
     * @param element The chemical element.
     * @return The number of distinct valid symbols for the element.
     */
    int getNumberOfValidSymbols(String element);
}
