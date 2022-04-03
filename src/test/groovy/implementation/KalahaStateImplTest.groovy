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
                    4,4,4,4,4,4,0,
                    4,4,4,4,4,4,0
            ]
            afterMove == [
                    4,4,0,5,5,5,1,
                    4,4,4,4,4,4,0
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
        underTest.makeMove(7,2)
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

    def "full game 1"() {
        given:
        def seeds = 4
        def houses = 6
        when:
        def underTest = new KalahaStateImpl(seeds, houses)


        underTest.makeMove(5, 1)
        underTest.makeMove(7, 2)
        underTest.makeMove(4, 1)
        underTest.makeMove(7,2)
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

}
