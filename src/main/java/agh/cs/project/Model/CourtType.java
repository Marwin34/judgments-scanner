package agh.cs.project.Model;

public enum CourtType {
    COMMON("Sąd Powszechny"),
    CONSTITUTIONAL_TRIBUNAL("Trybunał Konstytucyjny"),
    SUPREME("Sąd Najwyższy"),
    NATIONAL_APPEAL_CHAMBER("Krajowa Izba Odwoławcza"),
    PROVINCIAL_COURT("Sąd Wojewódzki"),
    SUPER_SUPREME_COURT("Naczelny Sąd Administracyjny"),
    DUMB("Nie rozpoznano typu sądu");

    private String value;

    CourtType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
