package abstractFactory.patternApproach;

import abstractFactory.model.Button;
import abstractFactory.model.Checkbox;
import abstractFactory.model.LightButton;
import abstractFactory.model.LightCheckbox;

public class LightThemeFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}