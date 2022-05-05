import interfaces.GameStateObserver
import interfaces.KalahPlayer
import spock.lang.Specification

class KalahaGameTest extends Specification {

    def underTest = new KalahaGame()

    def "test game1"() {
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
            p1.yourMove(_) >>> [5, 4, 3, 2, 0, 1, 5, 0, 0, 2, 3, 0, 4, 1, 5, 4, 3, 5, 4, 5, 1]
            p2.yourMove(_) >>> [0, 0, 0, 5, 0, 0, 1, 5, 2, 3, 2, 1, 2, 4, 5, 3, 5, 1, 4]

    }

}
