package com.chemsymbol.challenge;

/**
 * Created by Jan Koren on 8/8/2016.
 */
public interface ChemicalSymbolsResolver {

    boolean isValidSymbolOf(String element, String symbol);

    String getFirstSymbol(String element);

    int getNumberOfValidSymbols(String element);
}
