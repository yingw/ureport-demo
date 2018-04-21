package cn.wilmar.ureport.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Yin Guo Wei 2018/4/21.
 */
enum Gender {
    /**
     * Male
     */
    MALE,
    /**
     * Female
     */
    FEMALE;

    private static final List<Gender> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Gender randomGender() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
