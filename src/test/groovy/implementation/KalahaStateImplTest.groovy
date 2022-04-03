package implementation

import spock.lang.Ignore
import spock.lang.Specification

class KalahaStateImplTest extends Specification {


    @Ignore
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

    @Ignore
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
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        // // skonczyl na swoim base
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)

        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
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
        underTest.makeMove(7, 2)

        then:
        true == true
    }

    def "game1 move by move #2"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)

        then:
        true == true
    }

    def "game1 move by move #3"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)

        then:
        true == true

    }

    def "game1 move by move #4"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)

        then:
        true == true

    }

    def "game1 move by move #5"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)

        then:
        true == true
    }

    def "game1 move by move #6"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)

        then:
        true == true
    }

    def "game1 move by move #7"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)

        then:
        true == true
    }

    def "game1 move by move #8"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)

        then:
        true == true
    }

    def "game1 move by move #9"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)

        then:
        true == true
    }

    def "game1 move by move #10"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)

        then:
        true == true
    }

    def "game1 move by move #11"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)

        then:
        true == true
    }

    def "game1 move by move #12"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)

        then:
        true == true
    }

    def "game1 move by move #13"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)

        then:
        true == true
    }

    def "game1 move by move #14"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)

        then:
        true == true
    }

    def "game1 move by move #15"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)

        then:
        true == true
    }

    def "game1 move by move #16"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)

        then:
        true == true
    }

    def "game1 move by move #17"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)

        then:
        true == true
    }

    def "game1 move by move #18"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)

        then:
        true == true
    }

    def "game1 move by move #19"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)

        then:
        true == true
    }

    def "game1 move by move #20"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)

        then:
        true == true
    }

    def "game1 move by move #21"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)

        then:
        true == true
    }

    def "game1 move by move #22"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)

        then:
        true == true
    }

    def "game1 move by move #23"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)

        then:
        true == true
    }

    def "game1 move by move #24"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)

        then:
        true == true
    }

    def "game1 move by move #25"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)

        then:
        true == true
    }

    def "game1 move by move #26"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)

        then:
        true == true
    }

    def "game1 move by move #27"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)

        then:
        true == true
    }

    def "game1 move by move #28"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)

        then:
        true == true
    }

    def "game1 move by move #29"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)

        then:
        true == true
    }

    def "game1 move by move #30"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)

        then:
        true == true
    }

    def "game1 move by move #31"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(3, 1)

        then:
        true == true
    }

    def "game1 move by move #32"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(10, 2)

        then:
        true == true
    }

    def "game1 move by move #33"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(5, 1)

        then:
        true == true
    }

    def "game1 move by move #34"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)

        then:
        true == true
    }

    def "game1 move by move #35"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)

        then:
        true == true
    }

    def "game1 move by move #36"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(8, 2)

        then:
        true == true
    }

    def "game1 move by move #37"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(8, 2)
        underTest.makeMove(5, 1)

        then:
        true == true
    }

    def "game1 move by move #38"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(9, 2)
        underTest.makeMove(2, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(0, 1)
        underTest.makeMove(8, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(9, 2)
        underTest.makeMove(1, 1)
        underTest.makeMove(5, 1)
        underTest.makeMove(11, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(3, 1)
        underTest.makeMove(10, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(4, 1)
        underTest.makeMove(12, 2)
        underTest.makeMove(8, 2)
        underTest.makeMove(5, 1)
        underTest.makeMove(1, 1)

        then:
        true == true
    }

    def "full game 1"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)

        1 underTest.makeMove(7, 2)
        2 underTest.makeMove(4, 1)
        3 underTest.makeMove(7, 2)
        4 underTest.makeMove(3, 1)
        5 underTest.makeMove(7, 2)
        6 underTest.makeMove(2, 1)
        7 underTest.makeMove(0, 1)
        8 underTest.makeMove(12, 2)
        9 underTest.makeMove(1, 1)
        10 underTest.makeMove(7, 2)
        11 underTest.makeMove(5, 1)
        12 underTest.makeMove(7, 2)
        13 underTest.makeMove(0, 1)
        14 underTest.makeMove(8, 2)
        15 underTest.makeMove(0, 1)
        16 underTest.makeMove(12, 2)
        17 underTest.makeMove(9, 2)
        18 underTest.makeMove(2, 1)
        19 underTest.makeMove(10, 2)
        20 underTest.makeMove(3, 1)
        21 underTest.makeMove(9, 2)
        22 underTest.makeMove(0, 1)
        23 underTest.makeMove(8, 2)
        24 underTest.makeMove(4, 1)
        25 underTest.makeMove(9, 2)
        26 underTest.makeMove(1, 1)
        27 underTest.makeMove(5, 1)
        28 underTest.makeMove(11, 2) 2
        29 underTest.makeMove(4, 1)
        30 underTest.makeMove(12, 2)
        31 underTest.makeMove(3, 1)
        32 underTest.makeMove(10, 2)
        33 underTest.makeMove(5, 1)
        34 underTest.makeMove(4, 1)
        35 underTest.makeMove(12, 2)
        36 underTest.makeMove(8, 2)
        37 underTest.makeMove(5, 1)
        38 underTest.makeMove(1, 1)
        39 underTest.makeMove(11, 2)

        def state = underTest.getPitsState()
        then:
        state == [
                1, 1, 4, 5, 5, 0, 12,
                0, 0, 8, 6, 1, 1, 4
        ]
    }

}
