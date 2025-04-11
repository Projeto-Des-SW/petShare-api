package br.com.ufape.petshare.model.enums;

public enum DonationStatus {
    DISPONIVEL("Disponível"),
    EM_INTERESSE("Em interesse"),
    APROVADO("Aprovado"),
    RECUSADO("Recusado"),
    ADOTADO("Adotado");

    private final String label;

    DonationStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}