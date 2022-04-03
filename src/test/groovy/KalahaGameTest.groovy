import implementation.KalahaStateImpl
import interfaces.GameStateObserver
import interfaces.KalahPlayer
import interfaces.KalahaState
import spock.lang.Specification

class KalahaGameTest extends Specification {

    def underTest = new KalahaGame()

    def "test game"() {
        given:
            def observer = Mock(GameStateObserver)
            def p1 = Mock(KalahPlayer)
            def p2 = Mock(KalahPlayer)
        when:
            underTest.setVariant(6,4)
            underTest.registerPlayer(p1)
            underTest.registerPlayer(p2)
            underTest.addObserver(observer)
            underTest.startGame()
        then:
            1 * observer.currentState( { KalahaState it ->
                it.pitsState == [
                        4,4,4,4,4,4,0,
                        4,4,4,4,4,4,0
                ]
            } )

            1 * p1.yourMove([
                    4,4,4,4,4,4,0,
                    4,4,4,4,4,4,0
            ]) >> 2

            1 * observer.currentState( { KalahaState it ->
                it.pitsState == [
                        4,4,0,5,5,5,1,
                        4,4,4,4,4,4,0
                ]
            } )
    }

}
