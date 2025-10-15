package abstractFactory.patternApproach;

import abstractFactory.model.Button;
import abstractFactory.model.Checkbox;

public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}