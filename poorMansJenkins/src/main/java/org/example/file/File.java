package org.example.file;

/**
 * A simple in-memory representation of a file containing textual content.
 */
public class File {
    private String content;
    /**
     * Creates a new File with the given content.
     *
     * @param content the initial content of the file
     * @throws IllegalArgumentException if content is null
     */
    public File(String content) {
        if (content == null) {
            throw new IllegalArgumentException("File content is null");
        }

        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        if (content == null) {
            throw new IllegalArgumentException("File content cannot be null");
        }

        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof File other)) {
            return false;
        }

        return content.equals(other.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
