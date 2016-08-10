package com.chemsymbol.challenge;

import com.google.common.base.Preconditions;

/**
 * Created by Jan Koren on 8/8/2016.
 */
public class ChemicalSymbolsResolverImpl implements ChemicalSymbolsResolver {

    private static final byte SYMBOL_LENGTH = 2;

    @Override
    public boolean isValidSymbolOf(final String element, final String symbol) {

        final long begin = System.nanoTime();

        Preconditions.checkArgument(element.length() >= SYMBOL_LENGTH, String.format("Element %s must be at least %d characters long", element, SYMBOL_LENGTH));
        Preconditions.checkArgument(symbol.length() == 2, String.format("Symbol %s is not %d characters long", symbol, SYMBOL_LENGTH));

        final String elementLowerCase = element.toLowerCase();
        final String symbolLowerCase = symbol.toLowerCase();

        final int firstLetterIndex = elementLowerCase.indexOf(symbolLowerCase.charAt(0));
        if (firstLetterIndex == elementLowerCase.length() - 1) {
            return false;
        }
        boolean hasSymbolFirstLetter = firstLetterIndex != -1;
        if (!hasSymbolFirstLetter) {
            return false;
        }

        final int secondLetterIndex = elementLowerCase.indexOf(symbolLowerCase.charAt(1), firstLetterIndex + 1);
        boolean hasSymbolSecondLetter = secondLetterIndex != -1;
        if (!hasSymbolSecondLetter) {
            return false;
        }

        final long end = System.nanoTime();
        System.out.println(end - begin);

        return true;
    }

    @Override
    public String getFirstSymbol(String element) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public int getNumberOfValidSymbols(String element) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
