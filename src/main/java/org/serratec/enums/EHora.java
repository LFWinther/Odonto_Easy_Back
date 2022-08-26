package org.serratec.enums;

public enum EHora {

	HORARIO_08_00(0, "08:00:00"),
	HORARIO_08_30(1, "08:30:00"),
	HORARIO_09_00(2, "09:00:00"),
	HORARIO_09_30(3, "09:30:00"),
	HORARIO_10_00(4, "10:00:00"),
	HORARIO_10_30(5, "10:30:00"),
	HORARIO_11_00(6, "11:00:00"),
	HORARIO_11_30(7, "11:30:00"),
	HORARIO_12_00(8, "12:00:00"),
	HORARIO_12_30(9, "12:30:00"),
	HORARIO_13_00(10, "13:00:00"),
	HORARIO_13_30(11, "13:30:00"),
	HORARIO_14_00(12, "14:00:00"),
	HORARIO_14_30(13, "14:30:00"),
	HORARIO_15_00(14, "15:00:00"),
	HORARIO_15_30(15, "15:30:00"),
	HORARIO_16_00(16, "16:00:00"),
	HORARIO_16_30(17, "16:30:00"),
	HORARIO_17_00(18, "17:00:00"),
	HORARIO_17_30(19, "17:30:00");

    private Integer id;
    private String LocalTime;

    private EHora(Integer id, String LocalTime) {
        this.id = id;
        this.LocalTime = LocalTime;
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocalTime() {
		return LocalTime;
	}
	public void setLocalTime(String localTime) {
		LocalTime = localTime;
	}
}
