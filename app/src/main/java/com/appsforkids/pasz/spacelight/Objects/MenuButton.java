package  com.appsforkids.pasz.spacelight.Objects;

public class MenuButton {


    int color;
    int iconImege;
    int text;
    int button;

    public MenuButton( int iconImege, int text, int button){
        this.iconImege = iconImege;
        this.text = text;
        this.button = button;

    }

    public int getColor() {
        return color;
    }

    public int getIconImege() {
        return iconImege;
    }

    public int getText() {
        return text;
    }

    public int getButton() {
        return button;
    }
}
