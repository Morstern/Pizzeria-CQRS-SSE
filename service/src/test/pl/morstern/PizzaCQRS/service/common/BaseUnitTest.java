package pl.morstern.PizzaCQRS.service.common;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.morstern.PizzaCQRS.common.enums.Language;

@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {
    public final Language polishLanguage = Language.PL;
    public final Language englishLanguage = Language.EN;
}
