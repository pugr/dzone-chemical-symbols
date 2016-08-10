package com.chemsymbol.challenge;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Jan on Koren 8/8/2016.
 */
public class ChemicalSymbolsResolverTest {

    private ChemicalSymbolsStreamResolver resolver = new ChemicalSymbolsStreamResolver();
//    private ChemicalSymbolsResolverImpl resolver = new ChemicalSymbolsResolverImpl();

    //All chemical symbols must be exactly two letters, so B is not a valid symbol for Boron.
    @Test
    public void elementOfOneLetter() {
        final String element = "B";
        final String symbol = "B";
        try {
            resolver.isValidSymbolOf(element, symbol);
        } catch (IllegalArgumentException e) {
            assertEquals("Element B must be at least 2 characters long", e.getMessage());
            return;
        }
        fail("expected IllegalArgumentException for element: " + element);
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

    //    Both letters in the symbol must appear in the element name, but the first letter of the element name does not necessarily need to appear in the symbol. So Hg is not valid for Mercury, but Cy is.
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

    //    The two letters must appear in order in the element name. So Vr is valid for Silver, but Rv is not. To be clear, both Ma and Am are valid for Magnesium, because there is both an a that appears after an m, and an m that appears after an a.
    @Test
    public void symbolLettersNotInElementOrder() {
        final String element = "Boron";
        final String symbol = "Nr";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertFalse(isValidSymbol);
    }

    @Test
    public void symbolLettersInElementOrder() {
        final String element = "Boron";
        final String symbol = "Bn";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertTrue(isValidSymbol);
    }

    //    If the two letters in the symbol are the same, it must appear twice in the element name. So Nn is valid for Xenon, but Xx and Oo are not.
    @Test
    public void sameSymbolLettersNotInElement() {
        final String element = "Boron";
        final String symbol = "Rr";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertFalse(isValidSymbol);
    }

    @Test
    public void sameSymbolLettersInElement() {
        final String element = "Boron";
        final String symbol = "Oo";

        final boolean isValidSymbol = resolver.isValidSymbolOf(element, symbol);

        assertTrue(isValidSymbol);
    }

    @Test
    public void variousElementsGoodSymbols() {
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
        )
        ;
    }

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
        )
        ;
    }

    @Test
    public void symbolMercury() {
        assertTrue(resolver.isValidSymbolOf("Mercury", "Eu"));
    }

    @Test
     public void symbolZuulun() {
        assertTrue(resolver.isValidSymbolOf("Zuulun", "Lu"));
    }

    @Test
    public void getNumberOfValidSymbolsZuulun() {
        final int numberOfValidSymbols = resolver.getNumberOfValidSymbols("Zuulon");
        assertEquals(11, numberOfValidSymbols);
    }

    @Test
    public void getFirstSymbolWutrubanibaum() {
        final String firstSymbol = resolver.getFirstSymbol("Wutrubanibaum");
        assertEquals("Aa", firstSymbol);
    }

}
