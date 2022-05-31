import interfaces.GameStateObserver
import interfaces.KalahPlayer
import interfaces.KalahaState
import spock.lang.Specification

class KalahaGameTest extends Specification {

    def underTest = new KalahaGame()

    def "test game1"() {
        given:
        def observer = Mock(GameStateObserver)
        def p1 = Mock(KalahPlayer)
        def p2 = Mock(KalahPlayer)
        when:
        underTest.setVariant(6, 4)
        underTest.registerPlayer(p1)
        underTest.registerPlayer(p2)
        underTest.addObserver(observer)
        underTest.startGame()
        then:
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.INITAL_STATE &&
                    it.getPitsState() == [
                    4, 4, 4, 4, 4, 4, 0,
                    4, 4, 4, 4, 4, 4, 0
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
         1 * observer.currentState({ KalahaState it ->
             it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                     it.getPitsState() == [
                     4, 4, 4, 4, 4, 0, 1,
                     5, 5, 5, 4, 4, 4, 0
             ] &&
                     it.getGameResult() == KalahaState.GameResults.UNKNOWN
         })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    4, 4, 4, 4, 4, 0, 1,
                    0, 6, 6, 5, 5, 5, 0
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    4, 4, 4, 4, 0, 1, 2,
                    1, 7, 6, 5, 5, 5, 0
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    4, 4, 4, 4, 0, 1, 2,
                    0, 8, 6, 5, 5, 5, 0
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    4, 4, 4, 0, 1, 2, 3,
                    1, 8, 6, 5, 5, 5, 0
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    4, 4, 4, 0, 1, 2, 3,
                    0, 9, 6, 5, 5, 5, 0
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    4, 4, 0, 1, 2, 3, 4,
                    0, 9, 6, 5, 5, 5, 0
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    0, 5, 1, 2, 3, 3, 4,
                    0, 9, 6, 5, 5, 5, 0
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    1, 6, 2, 3, 3, 3, 4,
                    0, 9, 6, 5, 5, 0, 1
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    1, 0, 3, 4, 4, 4, 5,
                    1, 9, 6, 5, 5, 0, 1
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    1, 0, 3, 4, 4, 4, 5,
                    0, 10, 6, 5, 5, 0, 1
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    1, 0, 3, 4, 4, 0, 6,
                    1, 11, 7, 5, 5, 0, 1
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    1, 0, 3, 4, 4, 0, 6,
                    0, 12, 7, 5, 5, 0, 1
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    0, 0, 3, 4, 4, 0, 12,
                    0, 12, 7, 5, 0, 0, 1
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    1, 1, 4, 5, 5, 0, 12,
                    0, 0, 8, 6, 1, 1, 4
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    0, 2, 4, 5, 5, 0, 12,
                    0, 0, 8, 6, 1, 1, 4
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    0, 2, 4, 5, 5, 0, 12,
                    0, 0, 8, 6, 1, 0, 5
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    1, 3, 5, 6, 5, 0, 12,
                    0, 0, 0, 7, 2, 1, 6
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    1, 3, 0, 7, 6, 1, 13,
                    1, 0, 0, 7, 2, 1, 6
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    2, 4, 1, 8, 6, 1, 13,
                    1, 0, 0, 0, 3, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    2, 4, 1, 0, 7, 2, 14,
                    2, 1, 1, 1, 4, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    2, 4, 1, 0, 7, 2, 14,
                    2, 1, 0, 2, 4, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    0, 5, 2, 0, 7, 2, 14,
                    2, 1, 0, 2, 4, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    0, 5, 2, 0, 7, 2, 14,
                    2, 0, 1, 2, 4, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    0, 5, 2, 0, 0, 3, 15,
                    3, 1, 2, 3, 5, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    0, 5, 2, 0, 0, 3, 15,
                    3, 1, 0, 4, 6, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    0, 0, 3, 1, 1, 4, 16,
                    3, 1, 0, 4, 6, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    0, 0, 3, 1, 1, 0, 17,
                    4, 2, 1, 4, 6, 2, 7
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    1, 1, 4, 2, 1, 0, 17,
                    4, 2, 1, 4, 0, 3, 8
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    1, 1, 4, 2, 0, 0, 22,
                    0, 2, 1, 4, 0, 3, 8
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    2, 2, 4, 2, 0, 0, 22,
                    0, 2, 1, 4, 0, 0, 9
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    2, 2, 4, 0, 1, 1, 22,
                    0, 2, 1, 4, 0, 0, 9
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    3, 2, 4, 0, 1, 1, 22,
                    0, 2, 1, 0, 1, 1, 10
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    3, 2, 4, 0, 1, 0, 23,
                    0, 2, 1, 0, 1, 1, 10
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    3, 2, 4, 0, 0, 1, 23,
                    0, 2, 1, 0, 1, 1, 10
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    3, 2, 4, 0, 0, 1, 23,
                    0, 2, 1, 0, 1, 0, 11
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    3, 2, 0, 0, 0, 1, 23,
                    0, 0, 2, 0, 1, 0, 16
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    3, 2, 0, 0, 0, 0, 24,
                    0, 0, 2, 0, 1, 0, 16
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER1_TURN &&
                    it.getPitsState() == [
                    3, 0, 1, 0, 0, 0, 27,
                    0, 0, 0, 0, 1, 0, 16
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.AFTER_PLAYER2_TURN &&
                    it.getPitsState() == [
                    0, 0, 1, 0, 0, 0, 27,
                    0, 0, 0, 0, 0, 0, 20
            ] &&
                    it.getGameResult() == KalahaState.GameResults.UNKNOWN
        })
        1 * observer.currentState({ KalahaState it ->
            it.getGameState() == KalahaState.GameStates.END_OF_GAME &&
                    it.getPitsState() == [
                    0, 0, 0, 0, 0, 0, 28,
                    0, 0, 0, 0, 0, 0, 20
            ] &&
                    it.getGameResult() == KalahaState.GameResults.PLAYER1_WON
        })

        p1.yourMove(_) >>> [5, 4, 3, 2, 0, 1, 5, 0, 0, 2, 3, 0, 4, 1, 5, 4, 3, 5, 4, 5, 1]
        p2.yourMove(_) >>> [0, 0, 0, 5, 0, 0, 1, 5, 2, 3, 2, 1, 2, 4, 5, 3, 5, 1, 4]

    }

}
