package factory;

import domain.User;
import net.datafaker.Faker;

public class UserFactory {

    public static User generateUser(){
        Faker faker = new Faker();

        return User.builder().
                firstname(faker.name().firstName()).
                lastname(faker.name().lastName()).
                age(Integer.parseInt(faker.number().digits(2))).
                email(faker.internet().emailAddress()).
                build();
    }

}
