package Tux2.TuxTwoLib;

import java.util.UUID;

public class NMSHeadData {
    UUID id = null;
    String texture = null;

    public NMSHeadData(final UUID id, final String texture) {
        this.id = id;
        this.texture = texture;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTexture() {
        return this.texture;
    }
}
