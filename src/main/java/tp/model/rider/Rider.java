package tp.model.rider;

import lombok.AllArgsConstructor;
import lombok.Data;
import tp.model.utils.Country;
import tp.model.utils.WithId;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Data
public class Rider implements WithId {

    String id;

    String firstName;
    String lastName;

    Country country;
    String idType;
    String idNumber;

    String email;
    String password;

    Instant registrationDate;

    public Rider (String firstName, String lastName,
                  Country country, String idType, String idNumber,
                  String email, String password) {
        this(UUID.randomUUID().toString(), firstName, lastName,
            country, idType, idNumber, email, password, Instant.now());
    }

    public Rider(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("builder :: ").append(id).append("\n")
                .append(firstName).append(" ").append(lastName).append("\n")
                .append(email);

        return builder.toString();
    }

}
