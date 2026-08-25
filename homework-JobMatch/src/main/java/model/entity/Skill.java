package model.entity;

import helper.ValidationHelper;

public record Skill(String name, int level) {
    public Skill {
        if (!ValidationHelper.string(name)) {
            throw new IllegalArgumentException("Can't create Skill - invalid name!");
        }

        if (!ValidationHelper.skillLevel(level)) {
            throw new IllegalArgumentException("Can't create Skill - invalid skillLevel!");
        }
    }
}
