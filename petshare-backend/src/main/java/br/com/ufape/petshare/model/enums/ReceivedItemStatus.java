package br.com.ufape.petshare.model.enums;

public enum ReceivedItemStatus {
    EM_INTERESSE("Em interesse"),
    ESPERANDO_CONFIRMACAO_DOACAO("Em espera de confirmação de doação"),
    ESPERANDO_CONFIRMACAO_RECEBIMENTO("Em espera de confirmação de recebimento"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado"),
    RECUSADO("Recusado");

    private final String label;

    ReceivedItemStatus(String label) {
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
