package com.web.app.model;

import lombok.*;

import java.time.DayOfWeek;
import java.util.Map;

/**
 * A wrapper class for saving new agenda.
 * <p>
 * A JSON object will be mapped to this class automatically,
 * after adding the {@link org.springframework.web.bind.annotation.RequestBody} annotation, particularly,
 * by writing {@code @RequestBody SaveNewAgendaRequestDTO saveNewAgendaRequest} (see this here:
 * {@link com.web.app.rest.AgendaManagementController#saveNewAgenda(SaveNewAgendaRequestDTO)}).
 * But the requirement is that fields from POST request and the DTO class are equal.
 * <p>
 * Also, we can get all POST request's params by writing {@code @RequestBody Map<String, String> json},
 * and get needed fields like: {@code String neededFieldValue = json.get("neededField")},
 * like I did it here: {@link com.web.app.rest.AgendaManagementController#deleteAgendaByItsId(Map)}.
 * It is convenient, when we have a few fields to read, otherwise, it's smarter to create
 * a DTO wrapper class.
 * <p>
 * Moreover, in a DTO class there may be some necessary casts from a
 * {@link String} to a needed class. For example, I cast to {@link Long} - the {@link #id} field,
 * {@link DayOfWeek} - the {@link #day} field, and {@code boolean} - the {@link #accessible} field.
 * <p>
 * {@link NoArgsConstructor} generates an empty constructor. It is used to deserialize a JSON.
 * <p>
 * {@link AllArgsConstructor} generates a constructor for all fields of a class. Its just convenient.
 * <p>
 * I use {@link Getter} and {@link Setter} in most POJO classes this annotations generates
 * getters and setters for all annotated class's fields respectively.
 *
 * @see com.web.app.rest.AgendaManagementController#updateAgendaById(UpdateAgendaByItsIdRequestDTO)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateAgendaByItsIdRequestDTO {

    private Long id;

    private DayOfWeek day;

    private String time;

    private String note;

    private boolean accessible;
}
