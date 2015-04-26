package org.anaxivis;

import java.io.File;

public interface IApplication {

    public void setDPI(int dpi);

    public void setResolution(int width, int height);

    public void setSVGFile(File file);
}
