package org.serratec.enums;

public enum ECargos {

	CLIENTE(0, "Usuario"),
	RECEPCIONISTA(1, "Recepcionista"),
	DENTISTA(2, "Dentista"),
	ADMINISTRADOR(3, "Administrador");

    private Integer codigo;
    private String status;

    private ECargos(Integer codigo, String status) {
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
