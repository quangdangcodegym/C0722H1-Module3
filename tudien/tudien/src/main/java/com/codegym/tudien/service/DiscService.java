package com.codegym.tudien.service;

import java.util.HashMap;
import java.util.Map;

public class DiscService {
    private Map<String, String> disc;

    public DiscService() {
        disc = new HashMap<>();
        disc.put("hello", "Xin chao");
        disc.put("luyn", "Nhot");
        disc.put("computer", "May tinh");
    }

    public Map<String, String> getDisc() {
        return disc;
    }

    public void setDisc(Map<String, String> disc) {
        this.disc = disc;
    }
}
