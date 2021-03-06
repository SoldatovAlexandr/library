package edu.asoldatov.library.dto.mapper;

import edu.asoldatov.library.dto.request.UserDtoRequest;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class UserDtoMapper {

    public static final UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);


    public User toUser(UserDtoRequest userDtoRequest) {
        return new User(
                userDtoRequest.getUsername(),
                userDtoRequest.getFirstName(),
                userDtoRequest.getLastName(),
                userDtoRequest.getPatronymic(),
                userDtoRequest.getYearOfBirth()
        );
    }

    public abstract List<UserDtoResponse> toUserDtoResponses(Iterable<User> users);

    public abstract UserDtoResponse toUserDtoResponse(User user);
}
