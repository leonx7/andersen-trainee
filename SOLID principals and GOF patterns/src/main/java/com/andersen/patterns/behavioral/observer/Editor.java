package com.andersen.patterns.behavioral.observer;

import java.io.File;
import java.util.Objects;

public class Editor {
    public EventManager events;
    private File file;

    public Editor() {
        this.events = new EventManager("open", "save");
    }

    public void openFile(String filePath) {
        file = new File(filePath);
        events.notify("open", file);
    }

    public void saveFile() throws Exception {
        if (Objects.nonNull(file)) {
            events.notify("save", file);
        } else {
            throw new Exception("Please open a file first");
        }
    }
}
