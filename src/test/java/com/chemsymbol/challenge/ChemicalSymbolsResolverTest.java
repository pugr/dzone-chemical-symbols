package com.chemsymbol.challenge;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test suite for {@link ChemicalSymbolsResolverImpl}
 *
 * Created by Jan Koren on 8/8/2016.
 */
public class ChemicalSymbolsResolverTest {

    public static final String ELEMENT_NULL_CHECK_FAILED = "Element null check failed.";
    private ChemicalSymbolsResolverImpl resolver = new ChemicalSymbolsResolverImpl();

    @Test
    public void elementNull() {
        final String element = null;
        final String symbol = "Ag";

        try {
            resolver.isValidSymbolOf(element, symbol);
        } catch (NullPointerException e) {
            return;
        }
        fail(ELEMENT_NULL_CHECK_FAILED);
    }

    @Test
    public void symbolNull() {
        final String element = "Silver";
        final String symbol = null;

        try {
            resolver.isValidSymbolOf(element, symbol);
        } catch (NullPointerException e) {
            return;
        }
        fail(ELEMENT_NULL_CHECK_FAILED);
    }

    @Test
    public void elementOfOneLetterSymbolOfOneLetter() {
        final String element = "B";
        final String symbol = "B";
        try {
            resolver.isValidSymbolOf(element, symbol);
        } catch (IllegalArgumentException e) {
            assertEquals(String.format("Symbol %s is not 2 characters long", symbol), e.getMessage());
            return;
        }
        fail("expected IllegalArgumentException for symbol: " + element);
    }

    @Test
    public void elementOfOneLetterSymbolOfTwoLetters() {
        final String element = "B";
        final String symbol = "Be";
        try {
            resolver.isValidSymbolOf(element, symbol);
        } catch (IllegalArgumentException e) {
            assertEquals(String.format("Element %s must be at least 2 characters long", element), e.getMessage());
            return;
        }
        fail("expected IllegalArgumentException for symbol: " + element);
    }

    @Test
    public void symbolOfOneLetter() {
        final String element = "Boron";
        final String symbol = "B";

        try {
            resolver.isValidSymbolOf(element, symbol);
        } catch (IllegalArgumentException e) {
            assertEquals("Symbol B is not 2 characters long", e.getMessage());
            return;
        }
        fail("expected IllegalArgumentException for element: " + element);
    }

    @Test
    public void symbolOfThreeLetters() {
        final String element = "Boron";
        final String symbol = "Bor";

        try {
            resolver.isValidSymbolOf(element, symbol);
        } catch (IllegalArgumentException e) {
            assertEquals("Symbol Bor is not 2 characters long", e.getMessage());
            return;
        }
        fail("expected IllegalArgumentException for element: " + element);
    }

    @Test
    public void allSymbolLettersNotInElementName() {
        final String element = "Boron";
        final String symbol = "Xy";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertFalse(isValidSymbol);
    }

    @Test
    public void firstSymbolLetterNotInElementName() {
        final String element = "Boron";
        final String symbol = "Xo";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertFalse(isValidSymbol);
    }

    @Test
    public void secondSymbolLetterNotInElementName() {
        final String element = "Boron";
        final String symbol = "Ox";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertFalse(isValidSymbol);
    }

    @Test
    public void symbolLettersNotInElementOrder() {
        final String element = "Magnesium";
        final String symbol = "Ui";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertFalse(isValidSymbol);
    }

    @Test
    public void symbolLettersInElementOrder() {
        final String element = "Magnesium";
        final String symbol = "Ma";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertTrue(isValidSymbol);
    }

    @Test
    public void symbolLettersInElementOrderReverse() {
        final String element = "Magnesium";
        final String symbol = "Am";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertTrue(isValidSymbol);
    }

    @Test
    public void sameSymbolLettersInElement() {
        final String element = "Xenon";
        final String symbol = "Nn";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertTrue(isValidSymbol);
    }

    @Test
    public void sameSymbolLettersNotInElement() {
        final String element = "Xenon";
        final String symbol = "Xx";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertFalse(isValidSymbol);
    }

    @Test
    public void validSymbolForElementWithMirroredCorrectSymbols() {
        assertTrue(resolver.isValidSymbolOf("Zuulun", "Lu"));
    }

    /**
     * Tests a variety of expected correct symbols for the given element, basically all possible correct combinations (excluding case).
     */
    @Test
    public void variousElementsCorrectSymbols() {
        assertTrue(
                resolver.isValidSymbolOf("Zirconium", "Zi")
                        && resolver.isValidSymbolOf("Zirconium", "ZI")
                        && resolver.isValidSymbolOf("Zirconium", "zi")
                        && resolver.isValidSymbolOf("Zirconium", "Zr")
                        && resolver.isValidSymbolOf("Zirconium", "Zc")
                        && resolver.isValidSymbolOf("Zirconium", "Zo")
                        && resolver.isValidSymbolOf("Zirconium", "Zn")
                        && resolver.isValidSymbolOf("Zirconium", "Zu")
                        && resolver.isValidSymbolOf("Zirconium", "Zm")
                        && resolver.isValidSymbolOf("Zirconium", "Ir")
                        && resolver.isValidSymbolOf("Zirconium", "Ic")
                        && resolver.isValidSymbolOf("Zirconium", "Io")
                        && resolver.isValidSymbolOf("Zirconium", "In")
                        && resolver.isValidSymbolOf("Zirconium", "Ii")
                        && resolver.isValidSymbolOf("Zirconium", "Iu")
                        && resolver.isValidSymbolOf("Zirconium", "Im")
                        && resolver.isValidSymbolOf("Zirconium", "Rc")
                        && resolver.isValidSymbolOf("Zirconium", "Ro")
                        && resolver.isValidSymbolOf("Zirconium", "Rn")
                        && resolver.isValidSymbolOf("Zirconium", "Ri")
                        && resolver.isValidSymbolOf("Zirconium", "Rm")
                        && resolver.isValidSymbolOf("Zirconium", "Co")
                        && resolver.isValidSymbolOf("Zirconium", "Cn")
                        && resolver.isValidSymbolOf("Zirconium", "Ci")
                        && resolver.isValidSymbolOf("Zirconium", "Cu")
                        && resolver.isValidSymbolOf("Zirconium", "Cm")
                        && resolver.isValidSymbolOf("Zirconium", "On")
                        && resolver.isValidSymbolOf("Zirconium", "Oi")
                        && resolver.isValidSymbolOf("Zirconium", "Ou")
                        && resolver.isValidSymbolOf("Zirconium", "Om")
                        && resolver.isValidSymbolOf("Zirconium", "Ni")
                        && resolver.isValidSymbolOf("Zirconium", "Nu")
                        && resolver.isValidSymbolOf("Zirconium", "Nm")
                        && resolver.isValidSymbolOf("Zirconium", "Iu")
                        && resolver.isValidSymbolOf("Zirconium", "Im")
                        && resolver.isValidSymbolOf("Zirconium", "Um")
        );
    }

    /**
     * Tests a variety of expected incorrect symbols for the given element, basically all possible incorrect combinations.
     */
    @Test
    public void variousElementsBadSymbols() {
        assertTrue(
                !resolver.isValidSymbolOf("Zirconium", "Mu")
                        && !resolver.isValidSymbolOf("Zirconium", "MM")
                        && !resolver.isValidSymbolOf("Zirconium", "Mi")
                        && !resolver.isValidSymbolOf("Zirconium", "Mn")
                        && !resolver.isValidSymbolOf("Zirconium", "Mo")
                        && !resolver.isValidSymbolOf("Zirconium", "Mc")
                        && !resolver.isValidSymbolOf("Zirconium", "Mr")
                        && !resolver.isValidSymbolOf("Zirconium", "Mz")
                        && !resolver.isValidSymbolOf("Zirconium", "Uu")
                        && !resolver.isValidSymbolOf("Zirconium", "Ui")
                        && !resolver.isValidSymbolOf("Zirconium", "Un")
                        && !resolver.isValidSymbolOf("Zirconium", "Uo")
                        && !resolver.isValidSymbolOf("Zirconium", "Uc")
                        && !resolver.isValidSymbolOf("Zirconium", "Ur")
                        && !resolver.isValidSymbolOf("Zirconium", "Uz")
                        && !resolver.isValidSymbolOf("Zirconium", "Iz")
                        && !resolver.isValidSymbolOf("Zirconium", "Nn")
                        && !resolver.isValidSymbolOf("Zirconium", "No")
                        && !resolver.isValidSymbolOf("Zirconium", "Nc")
                        && !resolver.isValidSymbolOf("Zirconium", "Nr")
                        && !resolver.isValidSymbolOf("Zirconium", "Nz")
                        && !resolver.isValidSymbolOf("Zirconium", "Oo")
                        && !resolver.isValidSymbolOf("Zirconium", "Oc")
                        && !resolver.isValidSymbolOf("Zirconium", "Or")
                        && !resolver.isValidSymbolOf("Zirconium", "Oz")
                        && !resolver.isValidSymbolOf("Zirconium", "Cc")
                        && !resolver.isValidSymbolOf("Zirconium", "Cr")
                        && !resolver.isValidSymbolOf("Zirconium", "Cz")
                        && !resolver.isValidSymbolOf("Zirconium", "Rr")
                        && !resolver.isValidSymbolOf("Zirconium", "Rz")
                        && !resolver.isValidSymbolOf("Zirconium", "Iz")
                        && !resolver.isValidSymbolOf("Zirconium", "Zz")
        );
    }

    @Test
    public void getNumberOfValidSymbolsElementNull() {
        try {
            resolver.getNumberOfValidSymbols(null);
        } catch (NullPointerException e) {
            return;
        }
        fail(ELEMENT_NULL_CHECK_FAILED);
    }

    @Test
    public void getNumberOfValidSymbols() {
        final int numberOfValidSymbols = resolver.getNumberOfValidSymbols("Zuulon");
        assertEquals(11, numberOfValidSymbols);
    }

    @Test
    public void getNumberOfValidSymbolsExpectOne() {
        final int numberOfValidSymbols = resolver.getNumberOfValidSymbols("Aaaaaaa");
        assertEquals(1, numberOfValidSymbols);
    }

    @Test
    public void getNumberOfValidSymbolsExpectThree() {
        final int numberOfValidSymbols = resolver.getNumberOfValidSymbols("Aaaabaaa");
        assertEquals(3, numberOfValidSymbols);
    }

    @Test
    public void getFirstSymbolElementNull() {
        try {
            resolver.getFirstSymbol(null);
        } catch (NullPointerException e) {
            return;
        }
        fail(ELEMENT_NULL_CHECK_FAILED);
    }

    @Test
    public void getFirstSymbol() {
        final String firstSymbol = resolver.getFirstSymbol("Wutrubanibauum");
        assertEquals("Aa", firstSymbol);
    }

}
