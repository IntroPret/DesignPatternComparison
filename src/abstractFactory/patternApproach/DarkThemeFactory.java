package abstractFactory.patternApproach;

import abstractFactory.model.Button;
import abstractFactory.model.Checkbox;
import abstractFactory.model.DarkButton;
import abstractFactory.model.DarkCheckbox;

public class DarkThemeFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}