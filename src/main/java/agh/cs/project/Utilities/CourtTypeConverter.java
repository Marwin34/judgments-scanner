package agh.cs.project.Utilities;

import agh.cs.project.Model.CourtType;

public class CourtTypeConverter {
    public static CourtType convert(String data){

        if(data.contains("Naczelny"))
            return CourtType.SUPER_SUPREME_COURT;

        if(data.contains("Wojew"))
            return CourtType.PROVINCIAL_COURT;

        if(data.contains("Krajowa"))
            return CourtType.NATIONAL_APPEAL_CHAMBER;

        if(data.contains("Najwy"))
            return CourtType.SUPREME;

        return CourtType.DUMB;
    }
}
