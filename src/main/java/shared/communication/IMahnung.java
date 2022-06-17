package shared.communication;

import java.rmi.Remote;
import java.time.LocalDateTime;

public interface IMahnung extends Remote {
    public String getMahnungsID();

    public String getMitgliedsID();

    public String getGrund();

    public LocalDateTime getVerfallsdatum();
}
