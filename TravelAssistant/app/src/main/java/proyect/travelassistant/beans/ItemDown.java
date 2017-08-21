package proyect.travelassistant.beans;

/**
 * Created by Pablo on 03/08/2017.
 */

public class ItemDown {
    private String image;
    private String text;

    public ItemDown() {
    }

    public ItemDown(String imageUrl, String text) {
        this.image = imageUrl;
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
