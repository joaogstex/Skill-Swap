package br.com.teamss.skillswap.skill_swap.enums;

public enum Values {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;

    Values(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
