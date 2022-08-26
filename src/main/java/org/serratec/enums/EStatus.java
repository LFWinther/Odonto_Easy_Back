package org.serratec.enums;

public enum EStatus {

	CANCELADO(0, "Cancelado"),
    ABERTO(1, "Aberto"),
	FINALIZADO(2, "Finalizado");

    private Integer codigo;
    private String status;

    private EStatus(Integer codigo, String status) {
        this.codigo = codigo;
        this.status = status;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
