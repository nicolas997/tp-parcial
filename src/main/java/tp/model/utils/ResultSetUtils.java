package tp.model.utils;

import java.sql.Timestamp;
import java.time.Instant;

public class ResultSetUtils {

    public static Instant timestampToInstant(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toInstant();
    }

}
