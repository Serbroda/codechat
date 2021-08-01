package de.rottstegge.codechat.webapp.model.shared;

public enum Permissions {
    BLOG_ENTRY_CREATE("blog_entry:create");

    private final String name;

    Permissions(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
