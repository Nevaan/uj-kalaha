package implementation

import implementation.pit.HousePit
import implementation.pit.KalahPit
import interfaces.KalahaState
import spock.lang.Specification

class KalahaStateImplTest extends Specification {

    def "should intit properly"() {

        given:
        def expectedResult = ([seeds] * houses << 0) * 2
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        def state = underTest.getPitsState()
        then:
        state == expectedResult
        where:
        houses | seeds
        6      | 4
        4      | 15
        1      | 2

    }

    def "should make single move"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        def beforeMove = underTest.getPitsState()
        underTest.makeMove(2, 1)
        def afterMove = underTest.getPitsState()
        then:
        beforeMove == [
                4, 4, 4, 4, 4, 4, 0,
                4, 4, 4, 4, 4, 4, 0
        ]
        afterMove == [
                4, 4, 0, 5, 5, 5, 1,
                4, 4, 4, 4, 4, 4, 0
        ]
    }

    def "should skip other user base"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)

        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)

        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 1, 4, 5, 5, 0, 12,
                0, 0, 8, 6, 1, 1, 4
        ]
    }

    def "game1 move by move #1"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                4, 4, 4, 4, 4, 0, 1,
                5, 5, 5, 4, 4, 4, 0
        ]
    }

    def "game1 move by move #2"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                4, 4, 4, 4, 4, 0, 1,
                0, 6, 6, 5, 5, 5, 0
        ]
    }

    def "game1 move by move #3"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                4, 4, 4, 4, 0, 1, 2,
                1, 7, 6, 5, 5, 5, 0
        ]
    }

    def "game1 move by move #4"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                4, 4, 4, 4, 0, 1, 2,
                0, 8, 6, 5, 5, 5, 0
        ]

    }

    def "game1 move by move #5"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                4, 4, 4, 0, 1, 2, 3,
                1, 8, 6, 5, 5, 5, 0
        ]

    }

    def "game1 move by move #6"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                4, 4, 4, 0, 1, 2, 3,
                0, 9, 6, 5, 5, 5, 0
        ]
    }

    def "game1 move by move #7"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                4, 4, 0, 1, 2, 3, 4,
                0, 9, 6, 5, 5, 5, 0
        ]
    }

    def "game1 move by move #8"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 5, 1, 2, 3, 3, 4,
                0, 9, 6, 5, 5, 5, 0
        ]
    }

    def "game1 move by move #9"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 6, 2, 3, 3, 3, 4,
                0, 9, 6, 5, 5, 0, 1
        ]
    }

    def "game1 move by move #10"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 0, 3, 4, 4, 4, 5,
                1, 9, 6, 5, 5, 0, 1
        ]
    }

    def "game1 move by move #11"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 0, 3, 4, 4, 4, 5,
                0, 10, 6, 5, 5, 0, 1
        ]
    }

    def "game1 move by move #12"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 0, 3, 4, 4, 0, 6,
                1, 11, 7, 5, 5, 0, 1
        ]
    }

    def "game1 move by move #13"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 0, 3, 4, 4, 0, 6,
                0, 12, 7, 5, 5, 0, 1
        ]
    }

    def "game1 move by move #14"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 0, 3, 4, 4, 0, 12,
                0, 12, 7, 5, 0, 0, 1
        ]
    }

    def "game1 move by move #15"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 1, 4, 5, 5, 0, 12,
                0, 0, 8, 6, 1, 1, 4
        ]
    }

    def "game1 move by move #16"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 2, 4, 5, 5, 0, 12,
                0, 0, 8, 6, 1, 1, 4
        ]
    }

    def "game1 move by move #17"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 2, 4, 5, 5, 0, 12,
                0, 0, 8, 6, 1, 0, 5
        ]
    }

    def "game1 move by move #18"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 3, 5, 6, 5, 0, 12,
                0, 0, 0, 7, 2, 1, 6
        ]
    }

    def "game1 move by move #19"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 3, 0, 7, 6, 1, 13,
                1, 0, 0, 7, 2, 1, 6
        ]
    }

    def "game1 move by move #20"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                2, 4, 1, 8, 6, 1, 13,
                1, 0, 0, 0, 3, 2, 7
        ]
    }

    def "game1 move by move #21"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                2, 4, 1, 0, 7, 2, 14,
                2, 1, 1, 1, 4, 2, 7
        ]
    }

    def "game1 move by move #22"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                2, 4, 1, 0, 7, 2, 14,
                2, 1, 0, 2, 4, 2, 7
        ]
    }

    def "game1 move by move #23"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 5, 2, 0, 7, 2, 14,
                2, 1, 0, 2, 4, 2, 7
        ]
    }

    def "game1 move by move #24"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 5, 2, 0, 7, 2, 14,
                2, 0, 1, 2, 4, 2, 7
        ]
    }

    def "game1 move by move #25"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 5, 2, 0, 0, 3, 15,
                3, 1, 2, 3, 5, 2, 7
        ]
    }

    def "game1 move by move #26"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 5, 2, 0, 0, 3, 15,
                3, 1, 0, 4, 6, 2, 7
        ]
    }

    def "game1 move by move #27"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 0, 3, 1, 1, 4, 16,
                3, 1, 0, 4, 6, 2, 7
        ]
    }

    def "game1 move by move #28"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                0, 0, 3, 1, 1, 0, 17,
                4, 2, 1, 4, 6, 2, 7
        ]
    }

    def "game1 move by move #29"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 1, 4, 2, 1, 0, 17,
                4, 2, 1, 4, 0, 3, 8
        ]
    }

    def "game1 move by move #30"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                1, 1, 4, 2, 0, 0, 22,
                0, 2, 1, 4, 0, 3, 8
        ]
    }

    def "game1 move by move #31"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                2, 2, 4, 2, 0, 0, 22,
                0, 2, 1, 4, 0, 0, 9
        ]
    }

    def "game1 move by move #32"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                2, 2, 4, 0, 1, 1, 22,
                0, 2, 1, 4, 0, 0, 9
        ]
    }

    def "game1 move by move #33"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(3, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                3, 2, 4, 0, 1, 1, 22,
                0, 2, 1, 0, 1, 1, 10
        ]
    }

    def "game1 move by move #34"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(5, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                3, 2, 4, 0, 1, 0, 23,
                0, 2, 1, 0, 1, 1, 10
        ]
    }

    def "game1 move by move #35"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                3, 2, 4, 0, 0, 1, 23,
                0, 2, 1, 0, 1, 1, 10
        ]
    }

    def "game1 move by move #36"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                3, 2, 4, 0, 0, 1, 23,
                0, 2, 1, 0, 1, 0, 11
        ]
    }

    def "game1 move by move #37"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 2)
        def state = underTest.getPitsState()
        then:
        state == [
                3, 2, 0, 0, 0, 1, 23,
                0, 0, 2, 0, 1, 0, 16
        ]
    }

    def "game1 move by move #38"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 2)
        underTest.makeMove(5, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                3, 2, 0, 0, 0, 0, 24,
                0, 0, 2, 0, 1, 0, 16
        ]
    }

    def "game1 move by move #39"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(1, 1)
        def state = underTest.getPitsState()
        then:
        state == [
                3, 0, 1, 0, 0, 0, 27,
                0, 0, 0, 0, 1, 0, 16
        ]
    }

    def "full game 1"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)

        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(0, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(2, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(1, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(2, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(3, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(5, 2)
        underTest.makeMove(1, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(1, 1)
        underTest.makeMove(4, 2)

        def state = underTest.getPitsState()
        then:
        state == [
                0, 0, 1, 0, 0, 0, 27,
                0, 0, 0, 0, 0, 0, 20
        ]
    }

    def "checkIfGameIsDone test1"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 6
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 1
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.PLAYER1_WON
    }

    def "checkIfGameIsDone test2"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 1
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 6
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.PLAYER2_WON
    }

    def "checkIfGameIsDone test3"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 6
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 6
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.DRAW
    }

    def "checkIfGameIsDone test4"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 6
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 5
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(1),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.DRAW
    }

    def "checkIfGameIsDone test5"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 5
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 6
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(1),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.DRAW
    }

    def "checkIfGameIsDone test6"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 7
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 5
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(4),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.PLAYER2_WON
    }

    def "checkIfGameIsDone test7"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 5
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 7
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(4),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.PLAYER1_WON
    }

    def "checkIfGameIsDone test8"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 5
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 7
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(4),
                new HousePit(0),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.PLAYER2_WON
    }

    def "checkIfGameIsDone test9"() {
        given:
        def p1Kalah = new KalahPit()
        p1Kalah.stoneAmount = 7
        def p2Kalah = new KalahPit()
        p2Kalah.stoneAmount = 5
        def p1State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(4),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p1Kalah
        ]
        def p2State = [
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                new HousePit(0),
                p2Kalah
        ]
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.player1Pits = p1State
        underTest.player2Pits = p2State
        underTest.checkIfGameIsDone()
        then:
        underTest.currentState == KalahaState.GameStates.END_OF_GAME
        underTest.result == KalahaState.GameResults.PLAYER1_WON
    }
}
