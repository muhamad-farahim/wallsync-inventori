package com.mycompany.buat_apk.domains.frames;

public class ComboCategory {
    public Long id;
    public String label;
    
    public ComboCategory(Long id, String label) {
       this.id = id;
       this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
