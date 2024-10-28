package racingcar.domain;

import static racingcar.exception.ExceptionMessage.*;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;
import java.util.stream.Collectors;

public class RacingGame {
    private final List<Car> cars;

    public RacingGame(List<String> carNames) {
        validateDuplicateNames(carNames);
        this.cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    void validateDuplicateNames(List<String> carNames) {
        Set<String> uniqueNames = new HashSet<>(carNames);
        if (uniqueNames.size() < carNames.size()) {
            throw new IllegalArgumentException(DUPLICATE_CAR_NAME_ERROR);
        }
    }


    public List<Car> move() {
        for (Car car : cars) {
            int randomNum = Randoms.pickNumberInRange(0, 9);
            car.move(randomNum);
        }
        return cars;
    }

    public int getMaxMoveCount() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    public List<String> getWinners(int maxMoveCount) {
        return cars.stream()
                .filter(car -> car.getPosition() == maxMoveCount)
                .map(Car::getName)
                .toList();
    }

    public List<Car> getCars() {
        return cars;
    }

}
